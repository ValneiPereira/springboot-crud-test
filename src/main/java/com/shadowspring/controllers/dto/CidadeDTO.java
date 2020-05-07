package com.shadowspring.controllers.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.shadowspring.entity.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@Column(nullable = false)
	private String nomeCidade;
	@Column(nullable = false, length = 2)
	private String estado;

	public CidadeDTO() {

	}

	public CidadeDTO(Cidade cidade) {
		id = cidade.getId();
		nomeCidade = cidade.getNomeCidade();
		estado = cidade.getEstado();
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
