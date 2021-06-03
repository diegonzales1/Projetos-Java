package com.desafio_api.dogs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "venda")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Venda {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<ItensVenda> itensVenda;
	
	@Enumerated(EnumType.STRING)
	private StatusVenda status;
}
