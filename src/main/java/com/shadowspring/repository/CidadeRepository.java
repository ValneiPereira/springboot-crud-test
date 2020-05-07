package com.shadowspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shadowspring.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	List<Cidade> findByEstado(String estado);
	Cidade findByNomeCidade(String nomeCidade);

	
	

}
