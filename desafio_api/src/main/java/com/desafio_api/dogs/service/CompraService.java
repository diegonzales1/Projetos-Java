package com.desafio_api.dogs.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.desafio_api.dogs.event.RecursoCriadoEvent;
import com.desafio_api.dogs.exceptionhandler.ProdNaoExisteException;
import com.desafio_api.dogs.exceptionhandler.UsuarioNaoTemAcessoException;
import com.desafio_api.dogs.model.Compra;
import com.desafio_api.dogs.model.Estoque;
import com.desafio_api.dogs.model.ItensCompra;
import com.desafio_api.dogs.repository.CompraRepository;
import com.desafio_api.dogs.repository.EstoqueRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompraService {

	private CompraRepository repository;
	private ApplicationEventPublisher publisher;
	private EstoqueRepository estoqueRepository;

	public List<Compra> listarTodasCompra() {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			return repository.findAll();
		}
		throw new UsuarioNaoTemAcessoException();
	}

	public ResponseEntity<Compra> cadastrarNovaCompra(Compra compra, HttpServletResponse response) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			List<ItensCompra> itensCompra = compra.getItensCompra();

			for (ItensCompra item : itensCompra) {
				boolean prodExiste = estoqueRepository.existsByProduto_Codigo(item.getProduto().getCodigo());

				if (prodExiste) {
					Estoque estoque = estoqueRepository.findByProduto_Codigo(item.getProduto().getCodigo());

					estoque.setQuantidade(estoque.getQuantidade() + item.getQuantidade());
					estoque.setValorDeVenda(item.getValor());
					estoqueRepository.save(estoque);
				} else {
					throw new ProdNaoExisteException();
				}
			}

			Compra novaCompra = repository.save(compra);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaCompra.getCodigo()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaCompra);
		}
		throw new UsuarioNaoTemAcessoException();
	}

	private Collection<? extends GrantedAuthority> pegaPerfil() {
		Collection<? extends GrantedAuthority> perfis = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		return perfis;
	}

}
