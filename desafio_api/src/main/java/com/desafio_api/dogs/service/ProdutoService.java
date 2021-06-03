package com.desafio_api.dogs.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.desafio_api.dogs.event.RecursoCriadoEvent;
import com.desafio_api.dogs.exceptionhandler.UsuarioNaoTemAcessoException;
import com.desafio_api.dogs.model.Produto;
import com.desafio_api.dogs.repository.ProdutoRepository;
import com.desafio_api.dogs.repository.filter.ProdutoFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProdutoService {

	private ProdutoRepository repository;
	private ApplicationEventPublisher publisher;

	public List<Produto> listarTodosProdutos(ProdutoFilter produtofilter) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			return repository.filtrar(produtofilter);
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public Optional<Produto> buscarProdutoPeloCodigo(Long codigo) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Optional<Produto> produto = repository.findById(codigo);

			if (produto.isEmpty())
				throw new EmptyResultDataAccessException(1);

			return produto;
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public ResponseEntity<Produto> cadastraNovoProduto(Produto produto, HttpServletResponse response) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Produto novoProduto = repository.save(produto);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novoProduto.getCodigo()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public ResponseEntity<Void> deletarProdutoPeloCodigo(Long codigo) {
		if (!repository.existsById(codigo))
			return ResponseEntity.notFound().build();

		Collection<? extends GrantedAuthority> perfis = pegaPerfil();
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			repository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public Produto atualizarProduto(Long codigo, Produto prod) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Optional<Produto> produtoSalvo = buscarProdutoPeloCodigo(codigo);
			BeanUtils.copyProperties(prod, produtoSalvo.get(), "codigo");
			return repository.save(produtoSalvo.get());
		}
		throw new UsuarioNaoTemAcessoException();
	}

	private Collection<? extends GrantedAuthority> pegaPerfil() {
		Collection<? extends GrantedAuthority> perfis = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		return perfis;
	}
}
