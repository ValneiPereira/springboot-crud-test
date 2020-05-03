package com.shadowspring.controllers.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;

public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private Sexo sexo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT-3")
	private LocalDate dataNascimento;

	private Integer idade;

	private String cidade;

	public ClienteDto() {

	}

	public ClienteDto(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		sexo = cliente.getSexo();
		dataNascimento = cliente.getDataNascimento();
		idade = cliente.getIdade();
		cidade = cliente.getCidade().getNome();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
