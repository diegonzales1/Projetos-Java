package com.desafio_api.dogs.repository.Cliente;

import java.util.List;

import com.desafio_api.dogs.model.Cliente;
import com.desafio_api.dogs.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

	public List<Cliente> filtrar(ClienteFilter cliente);
}
