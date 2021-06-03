package com.desafio_api.dogs.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.desafio_api.dogs.exceptionhandler.UsuarioNaoTemAcessoException;
import com.desafio_api.dogs.model.Estoque;
import com.desafio_api.dogs.repository.EstoqueRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EstoqueService {

	private EstoqueRepository repository;

	public List<Estoque> listarTodosDoEstoque() {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			return repository.findAll();
		}
		
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Cliente"))) {
			List<Estoque> estoque =  repository.findAll();
			
			List<Estoque> newEstoque = new ArrayList<>();
			for(Estoque item : estoque) {
				if(item.getQuantidade() > 0 && item.getValorDeVenda() > 0.0) {
					newEstoque.add(item);
				}
			}
			
			return newEstoque;
			
		}
		
		throw new UsuarioNaoTemAcessoException();
	}

	public Optional<Estoque> buscarPeloCodigo(Long codigo) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Optional<Estoque> estoque = repository.findById(codigo);
			if (estoque.isEmpty())
				throw new EmptyResultDataAccessException(1);

			return estoque;
		}
		throw new UsuarioNaoTemAcessoException();
	}

	public Estoque atualizarEstoque(Long codigo, Estoque estoque) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))) {
			Optional<Estoque> estoqueSalvo = buscarPeloCodigo(codigo);
			BeanUtils.copyProperties(estoque, estoqueSalvo.get(), "codigo");
			return repository.save(estoqueSalvo.get());
		}
		throw new UsuarioNaoTemAcessoException();
	}

	private Collection<? extends GrantedAuthority> pegaPerfil() {
		Collection<? extends GrantedAuthority> perfis = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		return perfis;
	}
}
