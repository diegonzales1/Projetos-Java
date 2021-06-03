package com.desafio_api.dogs.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio_api.dogs.event.RecursoCriadoEvent;
import com.desafio_api.dogs.model.Cliente;
import com.desafio_api.dogs.repository.ClienteRepository;
import com.desafio_api.dogs.repository.filter.ClienteFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository repository;
	private ApplicationEventPublisher publisher;

	public List<Cliente> listarTodosClientes(ClienteFilter cliente) {	
		return repository.filtrar(cliente);
	}

	public Optional<Cliente> buscarClientePeloCodigo(Long codigo) {
		Optional<Cliente> cliente = repository.findById(codigo);
		if (cliente.isEmpty())
			throw new EmptyResultDataAccessException(1);
		return cliente;
	}

	public ResponseEntity<Cliente> cadastrarNovoCliente(Cliente cliente, HttpServletResponse response) {
		Cliente novoCliente = repository.save(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, novoCliente.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
	}

	public ResponseEntity<Void> deletarClientePeloCodigo(Long codigo) {
		repository.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}
	
	public Cliente atualizarCliente(Long codigo, Cliente cliente) {
		Optional<Cliente> clienteSalvo = buscarClientePeloCodigo(codigo);
		BeanUtils.copyProperties(cliente, clienteSalvo.get(), "codigo");
		return repository.save(clienteSalvo.get());
	}
}
