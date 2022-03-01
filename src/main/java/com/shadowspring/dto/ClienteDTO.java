package com.shadowspring.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Sexo sexo;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT-3")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private Integer idade;
	private BigDecimal vrRentabilidade;
	private Cidade cidade;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		sexo = cliente.getSexo();
		dataNascimento = cliente.getDataNascimento();
		idade = cliente.getIdade();
		cidade = cliente.getCidade();

	}


}
