package com.shadowspring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shadowspring.entity.Cidade;
import com.shadowspring.dto.CidadeDTO;

public interface CidadeServices {

	Cidade findById(Long id);
	Cidade save(Cidade cidade);
	Page<Cidade> findPage(Pageable pageable);
	List<Cidade> findByEstado(String estado);
	List<Cidade> findByNomeCidade(String nome);
	Cidade fromDTO(CidadeDTO dto);
	
	


}
