package com.shadowspring.controllers.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.shadowspring.entity.Cidade;
import com.shadowspring.enums.UF;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nomeCidade;
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF estado;

	public CidadeDTO() {

	}
	
	public CidadeDTO(Cidade cidade) {
		id=cidade.getId();
		nomeCidade=cidade.getNomeCidade();
		estado=cidade.getEstado();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public UF getEstado() {
		return estado;
	}

	public void setEstado(UF estado) {
		this.estado = estado;
	}

}
