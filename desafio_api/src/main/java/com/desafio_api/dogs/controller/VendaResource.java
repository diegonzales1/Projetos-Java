package com.desafio_api.dogs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_api.dogs.model.Venda;
import com.desafio_api.dogs.service.VendaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/vendas")
public class VendaResource {
	
	private VendaService service;
	
	// Mostra todas as vendas
	@GetMapping
	@PreAuthorize("hasAuthority('Loja') or hasAuthority('Cliente')")
	public List<Venda> listar(){
		return service.listarTodasVendas();
	}
	
	// Efetua nova venda
	@PostMapping
	@PreAuthorize("hasAuthority('Cliente')")
	public ResponseEntity<Venda> nova(@Validated @RequestBody Venda venda, HttpServletResponse response){
		return service.cadastrarNovaVenda(venda, response);
	}
	
	// Atualiza venda
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('Cliente')")
	public ResponseEntity<Venda> confirmar(@PathVariable Long codigo, @Validated @RequestBody Venda venda){
		Venda vendaAtualizada = service.atualizarVendas(codigo, venda);
		return ResponseEntity.ok(vendaAtualizada);
	}
	
	// Confirma Recebimento de Produto
	@PutMapping("/confirma/{codigo}")
	@PreAuthorize("hasAuthority('Cliente')")
	public ResponseEntity<Venda> confirmar(@PathVariable Long codigo)
	{
		Venda confirmaRecebimento = service.confirmarRecebimento(codigo);
		
		return ResponseEntity.ok(confirmaRecebimento);
	}

}
