package com.desafio_api.dogs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name = "compra")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Compra {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_fornecedor")
	private Fornecedor fornecedor;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<ItensCompra> itensCompra;

}
