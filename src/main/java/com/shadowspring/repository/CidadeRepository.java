package com.shadowspring.repository;

import com.shadowspring.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByEstado(String estado);

    List<Cidade> findByNomeCidade(String nomeCidade);

    Optional<Cidade> findByNomeCidadeAndEstado(String nomeCidade, String estado);
}
