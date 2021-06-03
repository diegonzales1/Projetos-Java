package com.desafio_api.dogs.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_api.dogs.model.Cliente;
import com.desafio_api.dogs.repository.filter.ClienteFilter;
import com.desafio_api.dogs.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	private ClienteService service;
	
	// Mostra todos os clientes
	@GetMapping
	public List<Cliente> listar(ClienteFilter cliente){
		return service.listarTodosClientes(cliente);
	}
	
	// Procurar um Cliente pelo código
	@GetMapping("/{codigo}")
	public Optional<Cliente> buscar(@PathVariable Long codigo){
		return service.buscarClientePeloCodigo(codigo);
	}
	
	//Cadastra novo cliente
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Cliente> novo(@Validated @RequestBody Cliente cliente, HttpServletResponse response){
		return service.cadastrarNovoCliente(cliente, response);
	}
	
	//Deleta um cliente
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo){
		return service.deletarClientePeloCodigo(codigo);
	}
	
	// Atualiza um cliente
	@PutMapping("/{codigo}")
	public ResponseEntity<Cliente> editar(@PathVariable Long codigo, @Validated @RequestBody Cliente cliente){
		Cliente clienteSalvo = service.atualizarCliente(codigo, cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
}
