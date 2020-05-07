package com.shadowspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shadowspring.controllers.dto.CidadeDTO;
import com.shadowspring.entity.Cidade;
import com.shadowspring.repository.CidadeRepository;
import com.shadowspring.services.CidadeServices;

@Service
public class CidadeServiceImpl implements CidadeServices {

	@Autowired
	CidadeRepository repository;

	@Autowired
	CidadeRepository cidaderepository;

	@Override
	public Cidade findById(Long id) {
		Optional<Cidade> cidades = repository.findById(id);
		return cidades.orElse(null);
	}

	@Override
	public Cidade save(Cidade cidade) {
		return repository.save(cidade);
	}
	
	@Override
	public Page<Cidade> findPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public List<Cidade> findByEstado(String estado) {
		return cidaderepository.findByEstado(estado);
	}
	
	@Override
	public List<Cidade> findByNomeCidade(String nomeCidade) {
		return cidaderepository.findByNomeCidade(nomeCidade);
	}

	@Override
	public Cidade fromDTO(CidadeDTO dto) {
		
		return new Cidade(dto.getId(), dto.getNomeCidade(), dto.getEstado());
	}
	
}
