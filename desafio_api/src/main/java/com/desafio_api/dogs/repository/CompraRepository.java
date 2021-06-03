package com.desafio_api.dogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio_api.dogs.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	
}
