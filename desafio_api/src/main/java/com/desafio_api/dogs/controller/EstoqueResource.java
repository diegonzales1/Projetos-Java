package com.desafio_api.dogs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_api.dogs.model.Estoque;
import com.desafio_api.dogs.service.EstoqueService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/estoque")
public class EstoqueResource {

	private EstoqueService service;
	
	// Mostra Estoque
	@GetMapping
	@PreAuthorize("hasAuthority('Loja') or hasAuthority('Cliente')")
	public List<Estoque> listar(){
		return service.listarTodosDoEstoque();
	}
	
	// Procura Estoque pelo Código
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public Optional<Estoque> buscar(@PathVariable Long codigo){
		return service.buscarPeloCodigo(codigo);
	}
	
	// Atualiza Estoque
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Estoque> editar(@PathVariable Long codigo, @Validated @RequestBody Estoque estoque){
		Estoque estoqueSalvo = service.atualizarEstoque(codigo, estoque);
		return ResponseEntity.ok(estoqueSalvo);
	}
}
