package com.shadowspring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shadowspring.controllers.dto.CidadeDTO;
import com.shadowspring.entity.Cidade;

public interface CidadeServices {

	Cidade findById(Long id);
	Cidade save(Cidade cidade);
	Page<Cidade> findPage(Pageable pageable);
	List<Cidade> findByEstado(String estado);
	Cidade findByNomeCidade(String nome);
	Cidade fromDTO(CidadeDTO dto);
	
	


}
