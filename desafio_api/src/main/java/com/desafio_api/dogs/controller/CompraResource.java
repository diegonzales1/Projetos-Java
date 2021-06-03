package com.desafio_api.dogs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_api.dogs.model.Compra;
import com.desafio_api.dogs.service.CompraService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/compra")
public class CompraResource {

	private CompraService service;
	
	// Mostra todos as compras
	@GetMapping
	@PreAuthorize("hasAuthority('Loja')")
	public List<Compra> listar(){
		return service.listarTodasCompra();
	}
	
	// Cadastra nova compra
	@PostMapping
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Compra> nova(@Validated @RequestBody Compra compra, HttpServletResponse response){
		return service.cadastrarNovaCompra(compra, response);
	}
}
