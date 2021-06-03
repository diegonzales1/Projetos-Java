package com.desafio_api.dogs.repository.produto;

import java.util.List;

import com.desafio_api.dogs.model.Produto;
import com.desafio_api.dogs.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {

	public List<Produto> filtrar(ProdutoFilter produtoFilter);
}
