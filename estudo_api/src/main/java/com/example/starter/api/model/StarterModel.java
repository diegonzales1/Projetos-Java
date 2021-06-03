package com.example.starter.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "starter")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StarterModel {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;

	@Email
	@NotBlank
	@Size(min = 3, max = 255)
	private String email;

	@Column(name = "quatro_letras")
	@NotBlank
	@Size(min = 4, max = 4)
	private String quatroletras;

	@NotBlank
	@Size(min = 11, max = 12)
	private String telefone;

	@NotBlank
	@Size(min = 3, max = 50)
	private String endereco;

	@NotBlank
	@Size(min = 3, max = 15)
	private String linguagem;

}
