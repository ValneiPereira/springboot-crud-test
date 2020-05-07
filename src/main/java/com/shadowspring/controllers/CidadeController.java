package com.shadowspring.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping()
	public ResponseEntity<?> findPage(Pageable pageable) {
		Page<Cidade> cidades = services.findPage(pageable);
		Page<CidadeDTO> cidadesDtoPage = cidades.map(i -> new CidadeDTO(i));
		return ResponseEntity.ok().body(cidadesDtoPage);
	}
	
	@PostMapping
	public ResponseEntity<Cidade> insert(@RequestBody CidadeDTO dto) {
		Cidade cidade= services.fromDTO(dto);
		cidade= services.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/nome-cidade/{cidade}")
	public ResponseEntity<?> findByCidade(@PathVariable String cidade) {
		List<Cidade> cidades = services.findByNomeCidade(cidade);
		return ResponseEntity.ok().body(cidades);
	}
	
	@GetMapping(value = "/estados/{estado}")
	public ResponseEntity<?> findByEstado(@RequestBody CidadeDTO cidade, @PathVariable String estado)  {
		cidade.setEstado(cidade.getEstado());
		List<Cidade> cidades = services.findByEstado(estado);
		List<CidadeDTO> cidadesDto = cidades.stream().map(i -> new CidadeDTO(i)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDto);
	}
	
}
