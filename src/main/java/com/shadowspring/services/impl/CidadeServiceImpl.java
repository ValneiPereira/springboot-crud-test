package com.shadowspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shadowspring.controllers.dto.CidadeDTO;
import com.shadowspring.entity.Cidade;
import com.shadowspring.exceptions.NegocioException;
import com.shadowspring.repository.CidadeRepository;
import com.shadowspring.services.CidadeServices;

@Service
public class CidadeServiceImpl implements CidadeServices {

	@Autowired
	CidadeRepository repository;

	

	@Override
	public Cidade findById(Long id) {
		Optional<Cidade> cidades = repository.findById(id);
		return cidades.orElse(null);
	}

	@Override
	public Cidade save(Cidade cidade) {
		Cidade cidadeExistente = repository.findByNomeCidade(cidade.getNomeCidade());
		if (cidadeExistente != null && !cidadeExistente.equals(cidade)) {
			throw new NegocioException("Já existe uma cidade mesmo nome neste estado.");
		}
		return repository.save(cidade);
	}
	
	
	
	
	
	
	@Override
	public Page<Cidade> findPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public List<Cidade> findByEstado(String estado) {
		return repository.findByEstado(estado);
	}
	
	@Override
	public Cidade findByNomeCidade(String nomeCidade) {
		return repository.findByNomeCidade(nomeCidade);
	}

	@Override
	public Cidade fromDTO(CidadeDTO dto) {
		
		return new Cidade(dto.getId(), dto.getNomeCidade(), dto.getEstado());
	}
	
}
