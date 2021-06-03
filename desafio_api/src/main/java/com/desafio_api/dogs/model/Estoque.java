package com.desafio_api.dogs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "estoque")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Produto produto;
	
	@NotNull
	private Long quantidade = 0L;

	@NotNull
	private float valorDeVenda ;
}