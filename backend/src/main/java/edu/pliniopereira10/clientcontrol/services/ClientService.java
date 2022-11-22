package edu.pliniopereira10.clientcontrol.services;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pliniopereira10.clientcontrol.dtos.ClientDto;
import edu.pliniopereira10.clientcontrol.models.ClientModel;
import edu.pliniopereira10.clientcontrol.repositories.ClientRepository;
import edu.pliniopereira10.clientcontrol.services.exceptions.ServiceDataBaseException;
import edu.pliniopereira10.clientcontrol.services.exceptions.ServiceNotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
		Page<ClientModel> clientModels = clientRepository.findAll(pageRequest);

		return clientModels.map(x -> new ClientDto(x));
	}

	@Transactional(readOnly = true)
	public ClientDto finById(Long id) {
		Optional<ClientModel> obj = clientRepository.findById(id);
		ClientModel clientModel = obj.orElseThrow(() -> new ServiceNotFoundException("Id não encontrado"));

		return new ClientDto(clientModel);
	}

	@Transactional
	public ClientDto insert(ClientDto clientDto) {
		ClientModel clientModel = new ClientModel();
		copyDtoToModel(clientDto, clientModel);
		clientModel = clientRepository.save(clientModel);

		return new ClientDto(clientModel);
	}

	@Transactional
	public ClientDto update(Long id, ClientDto clientDto) {
		try {
			ClientModel clientModel = clientRepository.getById(id);
			copyDtoToModel(clientDto, clientModel);

			return new ClientDto(clientModel);

		} catch (EntityNotFoundException e) {
			throw new ServiceNotFoundException("Id " + id + " não encontrado");
		}

	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceNotFoundException("Id " + id + " não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new ServiceDataBaseException("Violação de integridade");
		}

	}

	// Helper Method
	private void copyDtoToModel(ClientDto clientDto, ClientModel clientModel) {
		clientModel.setName(clientDto.getName());
		clientModel.setCpf(clientDto.getCpf());
		clientModel.setIncome(clientDto.getIncome());
		clientModel.setBirthDate(clientDto.getBirthDate());
		clientModel.setChildren(clientDto.getChildren());
	}

}
