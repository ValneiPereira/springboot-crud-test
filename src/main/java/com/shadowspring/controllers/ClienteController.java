package com.shadowspring.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadowspring.controllers.dto.ClienteDTO;
import com.shadowspring.entity.Cliente;
import com.shadowspring.services.ClienteServices;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteServices services;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente cliente = services.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<?> findPage(Pageable pageable) {
		Page<Cliente> Clientes = services.findPage(pageable);
		Page<ClienteDTO> ClientesDtoPage = Clientes.map(i -> new ClienteDTO(i));
		return ResponseEntity.ok().body(ClientesDtoPage);
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Cliente> Clientes = services.findAll();
		List<ClienteDTO> ClientesDto = Clientes.stream().map(i -> new ClienteDTO(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ClientesDto);
	}
	
	/*
	 * @PostMapping public ResponseEntity<Cliente> insert(@RequestBody Cliente
	 * Cliente) { Cliente = services.save(Cliente); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}")
	 * .buildAndExpand(Cliente.getId()).toUri(); return
	 * ResponseEntity.created(uri).build(); }
	 */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@RequestBody ClienteDTO dto, @PathVariable Long id) {
		Cliente cliente = services.fromDTO(dto);
		cliente.setId(id);
		cliente = services.update(cliente);
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
}
