package com.shadowspring.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.shadowspring.controllers.dto.CidadeDTO;
import com.shadowspring.entity.Cidade;
import com.shadowspring.services.CidadeServices;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeServices services;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Long id) {
		Cidade cidade = services.findById(id);
		return ResponseEntity.ok().body(cidade);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<?> findPage(Pageable pageable) {
		Page<Cidade> cidades = services.findPage(pageable);
		Page<CidadeDTO> cidadesDtoPage = cidades.map(i -> new CidadeDTO(i));
		return ResponseEntity.ok().body(cidadesDtoPage);
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Cidade> cidades = services.findAll();
		List<CidadeDTO> cidadesDto = cidades.stream().map(i -> new CidadeDTO(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDto);
	}
	
	@PostMapping
	public ResponseEntity<Cidade> insert(@RequestBody Cidade cidade) {
		cidade = services.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cidade> update(@RequestBody Cidade cidade, @PathVariable Long id) {
		
		cidade.setId(id);
		cidade = services.update(cidade);
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cidade> delete(@PathVariable Long id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/nome-cidade/{cidade}")
	public ResponseEntity<?> findByCidade(@PathVariable String cidade) {
		List<Cidade> cidadeDto = services.findByNomeCidade(cidade);
		return ResponseEntity.ok().body(cidadeDto);
	}
	
	@GetMapping(value = "/estado/{estado}")
	public ResponseEntity<?> findByEstado(@PathVariable String estado) {
		List<Cidade> cidades = services.findByEstado(estado);
		List<CidadeDTO> cidadesDto = cidades.stream().map(i -> new CidadeDTO(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDto);
	}
	
}
