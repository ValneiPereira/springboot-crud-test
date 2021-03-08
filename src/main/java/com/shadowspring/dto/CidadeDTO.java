package com.shadowspring.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.shadowspring.entity.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message="Obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
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
