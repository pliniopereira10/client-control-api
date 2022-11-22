package edu.pliniopereira10.clientcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pliniopereira10.clientcontrol.dtos.ClientDto;
import edu.pliniopereira10.clientcontrol.models.ClientModel;
import edu.pliniopereira10.clientcontrol.repositories.ClientRepository;
import edu.pliniopereira10.clientcontrol.services.exceptions.ServiceNotFoundExpetion;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public List<ClientDto> findAll() {
		List<ClientModel> clientModels = clientRepository.findAll();

		return clientModels.stream().map(x -> new ClientDto(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ClientDto finById(Long id) {
		Optional<ClientModel> obj = clientRepository.findById(id);
		ClientModel clientModel = obj.orElseThrow(() -> new ServiceNotFoundExpetion("Id não encontrado"));

		return new ClientDto(clientModel);
	}

	@Transactional
	public ClientDto insert(ClientDto clientDto) {
		ClientModel clientModel = new ClientModel();
		clientModel.setName(clientDto.getName());
		clientModel.setCpf(clientDto.getCpf());
		clientModel.setIncome(clientDto.getIncome());
		clientModel.setBirthDate(clientDto.getBirthDate());
		clientModel.setChildren(clientDto.getChildren());

		clientModel = clientRepository.save(clientModel);

		return new ClientDto(clientModel);
	}

}
