package com.desafio_api.dogs.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.desafio_api.dogs.model.Fornecedor;
import com.desafio_api.dogs.service.FornecedorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/fornecedores")
public class FornecedorResource {

	private FornecedorService service;
	
	// Mostra todos os fornecedores
	@GetMapping
	@PreAuthorize("hasAuthority('Loja')")
	public List<Fornecedor> listar(){
		return service.listarTodosFornecedores();
	}
	
	// Procura um Fornecedor pelo código
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public Optional<Fornecedor> buscar(@PathVariable Long codigo){
		return service.buscarFornecedorPeloCodigo(codigo);
	}

	// Cadastra novo Fornecedor
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Fornecedor> novo(@Validated @RequestBody Fornecedor fornecedor, HttpServletResponse response){
		return service.cadastrarNovoFornecedor(fornecedor, response);
	}
	
	//Deleta um fornecedor
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo){
		return service.deletarFornecedorPeloCodigo(codigo);
	}
	
	// Atualiza um Fornecedor
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Fornecedor> editar(@PathVariable Long codigo, @Validated @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorSalvo = service.atualizarFornecedor(codigo, fornecedor);
		return ResponseEntity.ok(fornecedorSalvo);
	}

}
