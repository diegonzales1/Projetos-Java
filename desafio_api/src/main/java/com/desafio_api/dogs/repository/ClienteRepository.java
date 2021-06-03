package com.desafio_api.dogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio_api.dogs.model.Cliente;
import com.desafio_api.dogs.repository.Cliente.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {

}
