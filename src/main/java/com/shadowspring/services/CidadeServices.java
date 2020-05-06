package com.shadowspring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shadowspring.entity.Cidade;

public interface CidadeServices {

	Cidade findById(Long id);
	Cidade save(Cidade cidade);
	Cidade update(Cidade cidade);
	void delete(Long id);
	List<Cidade> findAll();
	Page<Cidade> findPage(Pageable pageable);
	List<Cidade> findByEstado(String estado);
	List<Cidade> findByNomeCidade(String nome);
	
	


}
