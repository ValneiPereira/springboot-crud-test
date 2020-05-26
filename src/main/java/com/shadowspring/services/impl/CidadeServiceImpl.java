package com.shadowspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shadowspring.controllers.dto.CidadeDTO;
import com.shadowspring.entity.Cidade;
import com.shadowspring.exceptions.BadRequestException;
import com.shadowspring.exceptions.NotFoundException;
import com.shadowspring.repository.CidadeRepository;
import com.shadowspring.services.CidadeServices;

@Service
public class CidadeServiceImpl implements CidadeServices {

	@Autowired
	CidadeRepository repository;

	@Override
	public Cidade findById(Long id) {
		Optional<Cidade> cidade = repository.findById(id);
		return cidade.orElseThrow(( ) -> new NotFoundException("Cidade não tem na base dados"));

	}
	
	@Override
	public Cidade save(Cidade cidade) {
		Optional<Cidade> buscaNomeEstado = repository.findByNomeCidadeAndEstado(cidade.getNomeCidade(), cidade.getEstado());
		if (buscaNomeEstado.isPresent()) {
			throw new BadRequestException("Já existe uma cidade mesmo nome neste estado.");
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
	public List<Cidade> findByNomeCidade(String nomeCidade) {
		return repository.findByNomeCidade(nomeCidade);
	}

	@Override
	public Cidade fromDTO(CidadeDTO dto) {
		return new Cidade(dto.getId(), dto.getNomeCidade(), dto.getEstado());
	}
	
}
