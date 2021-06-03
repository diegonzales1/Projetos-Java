package com.desafio_api.dogs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
@Entity
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String descricao;

	@NotBlank
	@Size(min = 2, max = 15)
	private String unidade;
}