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
import com.desafio_api.dogs.model.Fornecedor;
import com.desafio_api.dogs.repository.FornecedorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FornecedorService {

	private FornecedorRepository repository;
	private ApplicationEventPublisher publisher;

	public List<Fornecedor> listarTodosFornecedores() {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			return repository.findAll();
		}
		throw new UsuarioNaoTemAcessoException();
	}

	public Optional<Fornecedor> buscarFornecedorPeloCodigo(Long codigo) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Optional<Fornecedor> fornecedor = repository.findById(codigo);
			if (fornecedor.isEmpty())
				throw new EmptyResultDataAccessException(1);
			return fornecedor;
		}
		throw new UsuarioNaoTemAcessoException();
	}

	public ResponseEntity<Fornecedor> cadastrarNovoFornecedor(Fornecedor fornecedor, HttpServletResponse response) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Fornecedor novoFornecedor = repository.save(fornecedor);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novoFornecedor.getCodigo()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
		}
		throw new UsuarioNaoTemAcessoException();
	}

	public ResponseEntity<Void> deletarFornecedorPeloCodigo(Long codigo) {

		Collection<? extends GrantedAuthority> perfis = pegaPerfil();
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			if (!repository.existsById(codigo))
				return ResponseEntity.notFound().build();

			repository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		throw new UsuarioNaoTemAcessoException();
	}

	public Fornecedor atualizarFornecedor(Long codigo, Fornecedor fornecedor) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Optional<Fornecedor> fornecedorSalvo = buscarFornecedorPeloCodigo(codigo);
			BeanUtils.copyProperties(fornecedor, fornecedorSalvo.get(), "codigo");
			return repository.save(fornecedorSalvo.get());
		}
		throw new UsuarioNaoTemAcessoException();
	}

	private Collection<? extends GrantedAuthority> pegaPerfil() {
		Collection<? extends GrantedAuthority> perfis = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		return perfis;
	}
}
