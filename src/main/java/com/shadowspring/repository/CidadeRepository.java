package com.shadowspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shadowspring.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	List<Cidade> findByEstado(String estado);
	List<Cidade> findByNomeCidade(String nomeCidade);
	Optional<Cidade> findByNomeCidadeAndEstado(String nomeCidade, String estado);

	
	

}
