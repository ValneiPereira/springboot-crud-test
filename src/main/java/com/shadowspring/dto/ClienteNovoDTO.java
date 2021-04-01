package com.shadowspring.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shadowspring.enums.Sexo;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNovoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	private Sexo sexo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT-3")
	private LocalDate dataNascimento;
	private Integer idade;
	private BigDecimal vrRentabilidade;
	private Long cidadeId;

	
}
