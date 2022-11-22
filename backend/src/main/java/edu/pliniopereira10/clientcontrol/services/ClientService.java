package edu.pliniopereira10.clientcontrol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pliniopereira10.clientcontrol.dtos.ClientDto;
import edu.pliniopereira10.clientcontrol.models.ClientModel;
import edu.pliniopereira10.clientcontrol.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public List<ClientDto> findAll() {
		List<ClientModel> clientModels = clientRepository.findAll();

		return clientModels.stream().map(x -> new ClientDto(x)).collect(Collectors.toList());
	}

}
