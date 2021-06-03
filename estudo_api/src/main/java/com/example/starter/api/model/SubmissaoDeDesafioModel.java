package com.example.starter.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "submissao_de_desafio")
public class SubmissaoDeDesafioModel {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne
	@JoinColumn(name = "codigo_starter")
	private StarterModel starter;

	@OneToOne
	@JoinColumn(name = "codigo_desafio")
	private DesafioModel desafio;
	
	@NotBlank
	@Size(min = 3, max = 250)
	private String enderecoRepositorio;
}
