package com.shadowspring.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	/*
	 * @Autowired private ModelMapper mapper;
	 */

	/*
	 * @PostMapping
	 * 
	 * @Transactional public ResponseEntity<?> save(@Valid @RequestBody ClienteDto
	 * clienteDto) { Cliente cliente = new Cliente(); ClienteDto dto =
	 * mapper.map(cliente, ClienteDto.class); cliente = services.save(cliente);
	 * 
	 * URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/clientes/{id}").
	 * buildAndExpand(dto.getId()) .toUri(); return
	 * ResponseEntity.created(uri).build();
	 * 
	 * }
	 */

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		List<Cliente> clientePage = services.findAll();
		List<ClienteDto> clienteDtoPage = clientePage.stream().map(i -> new ClienteDto(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDtoPage);

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
