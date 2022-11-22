package edu.pliniopereira10.clientcontrol.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<ClientDto> getOneClient(@PathVariable Long id) {
		ClientDto clientDto = clientService.finById(id);

		return ResponseEntity.ok().body(clientDto);
	}

	@PostMapping
	public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto) {
		clientDto = clientService.insert(clientDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientDto.getId()).toUri();

		return ResponseEntity.created(uri).body(clientDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
		clientDto = clientService.update(id, clientDto);
		
		return ResponseEntity.ok().body(clientDto);
	}

}
