package com.desafio_api.dogs.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "fornecedor")
@Entity
public class Fornecedor {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@CNPJ
	@NotBlank
	private String cnpj;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotBlank
	@Size(min = 10 , max = 13)
	private String telefone;
	
	@NotBlank
	@Email
	private String email;
	
	@Embedded
	private Endereco endereco;
	
}