package com.shadowspring.controllers;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.shadowspring.util.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shadowspring.entity.Cliente;
import com.shadowspring.dto.ClienteDTO;
import com.shadowspring.dto.ClienteNovoDTO;
import com.shadowspring.services.ClienteServices;

import javax.servlet.http.HttpServletResponse;

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
	
	@GetMapping()
	public ResponseEntity<?> findPage(Pageable pageable) {
		Page<Cliente> clientes = services.findPage(pageable);
		Page<ClienteDTO> clientesDtoPage = clientes.map(ClienteDTO::new);
		return ResponseEntity.ok().body(clientesDtoPage);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody ClienteNovoDTO dto) {
		Cliente cliente= services.fromDTO(dto);
		cliente= services.save(cliente);
		//Dispolibiliza link
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
		//Dispolibiliza corpo
		//return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		
	}
	
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
	
	@GetMapping(value = "/nome-cliente/{cliente}")
	public ResponseEntity<?> findByCliente(@PathVariable String cliente) {
		List<Cliente> clientes = services.findByNomeCliente(cliente);
		return ResponseEntity.ok().body(clientes);
	}

	@GetMapping("/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Clientes_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Cliente> listClientes = services.listAll();

		ExcelExporter excelExporter = new ExcelExporter(listClientes);

		excelExporter.export(response);
	}


}
