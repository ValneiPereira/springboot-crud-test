package com.shadowspring.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shadowspring.enums.Sexo;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@Column(columnDefinition = "DATE")
	private LocalDate dataNascimento;
	private BigDecimal vrRentabilidade;
	private Integer idade;

	@ManyToOne()
	private Cidade cidade;

}
