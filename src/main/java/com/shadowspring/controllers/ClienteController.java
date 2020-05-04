package com.shadowspring.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Cliente cliente = services.findById(id);
		ClienteDto clienteDto = mapper.map(cliente, ClienteDto.class);
		return cliente != null ? ResponseEntity.ok(clienteDto) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Cliente> clientes = services.findAll();
		List<ClienteDto> clienteDtos = clientes.stream().map(i -> new ClienteDto(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDtos);

	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDto>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, //facil de organizr layout 1 e 1, 2 e 2
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = services.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDto> listDto = list.map(i -> new ClienteDto(i));  
		return ResponseEntity.ok().body(listDto);
	}
	
}
