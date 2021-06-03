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

import com.desafio_api.dogs.model.Produto;
import com.desafio_api.dogs.repository.filter.ProdutoFilter;
import com.desafio_api.dogs.service.ProdutoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	private ProdutoService service;

	// Mostra todos os produtos
	@GetMapping
	@PreAuthorize("hasAuthority('Loja')")
	public List<Produto> listar(ProdutoFilter produtoFilter) {
		return service.listarTodosProdutos(produtoFilter);
	}

	// Procura um Produto pelo Código
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public Optional<Produto> buscar(@PathVariable Long codigo) {
		return service.buscarProdutoPeloCodigo(codigo);
	}

	// Cadastra Novo Produto
	@PostMapping
	@PreAuthorize("hasAuthority('Loja')")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Produto> novo(@Validated @RequestBody Produto prod, HttpServletResponse response) {
		return service.cadastraNovoProduto(prod, response);
	}

	// Deleta um Produto
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		return service.deletarProdutoPeloCodigo(codigo);
	}

	// Atualizar um Produto 
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Loja')")
	public ResponseEntity<Produto> editar(@PathVariable Long codigo, @Validated @RequestBody Produto prod) {
		Produto prodSalvo = service.atualizarProduto(codigo, prod);
		return ResponseEntity.ok(prodSalvo);
	}
}
