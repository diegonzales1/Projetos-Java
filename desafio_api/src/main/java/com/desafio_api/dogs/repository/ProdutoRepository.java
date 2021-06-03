package com.desafio_api.dogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio_api.dogs.model.Produto;
import com.desafio_api.dogs.repository.produto.ProdutoRepositoryQuery;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

}
