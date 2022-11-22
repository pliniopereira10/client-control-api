package edu.pliniopereira10.clientcontrol.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pliniopereira10.clientcontrol.dtos.ClientDto;
import edu.pliniopereira10.clientcontrol.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping
	public ResponseEntity<List<ClientDto>> getAllClients() {
		List<ClientDto> clientDtos = clientService.findAll();

		return ResponseEntity.ok().body(clientDtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDto> getOneClient(@PathVariable Long id){
		ClientDto clientDto = clientService.finById(id);
		
		return ResponseEntity.ok().body(clientDto);
	}

}
