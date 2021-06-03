package com.example.starter.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nota_de_desafio")
public class NotaDeDesafioModel {

	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@OneToOne
	@JoinColumn(name = "codigo_submissao")
	private SubmissaoDeDesafioModel submissao;

	@NotBlank 
	@Column(name = "nota_qualidade_codigo")
	@Range(min = 1, max = 3)
	private String notaQualidadeCodigo;
	
	@NotBlank 
	@Column(name = "nota_quantidade_entrega")
	@Range(min = 1, max = 3)
	private String notaQuantidadeEntrega;
}
