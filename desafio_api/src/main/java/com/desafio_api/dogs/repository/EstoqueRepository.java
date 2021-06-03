package com.desafio_api.dogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio_api.dogs.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	boolean existsByProduto_Codigo(Long codigo);
	 Estoque findByProduto_Codigo(Long codigo);
}
