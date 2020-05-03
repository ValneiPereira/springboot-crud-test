package com.shadowspring.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shadowspring.controllers.dto.ClienteDto;
import com.shadowspring.entity.Cliente;
import com.shadowspring.services.ClienteServices;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteServices services;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Valid @RequestBody ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		ClienteDto dto = mapper.map(cliente, ClienteDto.class);
		cliente = services.save(cliente);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/clientes/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<Cliente> clientePage = services.findAll(pageable);
		List<ClienteDto> clienteDtoPage = clientePage.stream().map(i -> new ClienteDto(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDtoPage);

	}

}
