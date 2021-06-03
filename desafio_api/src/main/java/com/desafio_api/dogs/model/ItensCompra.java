package com.desafio_api.dogs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItensCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	@NotNull
	private Long quantidade;

	@NotNull
	private float valor;

}
