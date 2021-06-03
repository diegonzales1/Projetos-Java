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
import com.desafio_api.dogs.exceptionhandler.ProdNaoExisteException;
import com.desafio_api.dogs.exceptionhandler.QuantidadeMenorException;
import com.desafio_api.dogs.exceptionhandler.UsuarioNaoTemAcessoException;
import com.desafio_api.dogs.model.Estoque;
import com.desafio_api.dogs.model.ItensVenda;
import com.desafio_api.dogs.model.StatusVenda;
import com.desafio_api.dogs.model.Venda;
import com.desafio_api.dogs.repository.EstoqueRepository;
import com.desafio_api.dogs.repository.VendaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VendaService {

	private VendaRepository repository;
	private ApplicationEventPublisher publisher;
	private EstoqueRepository estoqueRepository;

	public List<Venda> listarTodasVendas() {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();
		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Loja"))
				|| perfis.stream().anyMatch(p -> p.getAuthority().equals("Cliente"))) {
			return repository.findAll();
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public ResponseEntity<Venda> cadastrarNovaVenda(Venda venda, HttpServletResponse response) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Cliente"))) {
			List<ItensVenda> itensVenda = venda.getItensVenda();

			for (ItensVenda item : itensVenda) {
				boolean prodExiste = estoqueRepository.existsByProduto_Codigo(item.getProduto().getCodigo());

				if (prodExiste) {
					Estoque estoque = estoqueRepository.findByProduto_Codigo(item.getProduto().getCodigo());

					venda.setStatus(StatusVenda.PENDENTE);

					if (estoque.getQuantidade() < item.getQuantidade())
						throw new QuantidadeMenorException();

					estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());

					item.setValor(
							(item.getQuantidade() * estoque.getValorDeVenda()) + (estoque.getValorDeVenda() * 0.25f));
					estoqueRepository.save(estoque);
				} else {
					throw new ProdNaoExisteException();
				}
			}

			Venda novaVenda = repository.save(venda);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, novaVenda.getCodigo()));
			return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
		}

		throw new UsuarioNaoTemAcessoException();

	}

	public Venda atualizarVendas(Long codigo, Venda venda) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Cliente"))) {
			Optional<Venda> vendaSalva = buscarPeloCodigo(codigo);
			BeanUtils.copyProperties(venda, vendaSalva.get(), "codigo");
			return repository.save(vendaSalva.get());
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public Optional<Venda> buscarPeloCodigo(Long codigo) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Cliente"))) {
			Optional<Venda> venda = repository.findById(codigo);
			if (venda.isEmpty())
				throw new EmptyResultDataAccessException(1);

			return venda;
		}

		throw new UsuarioNaoTemAcessoException();
	}

	public Venda confirmarRecebimento(Long codigo) {
		Collection<? extends GrantedAuthority> perfis = pegaPerfil();

		if (perfis.stream().anyMatch(p -> p.getAuthority().equals("Cliente"))) {
			Optional<Venda> venda = repository.findById(codigo);
			if (venda.isEmpty())
				throw new EmptyResultDataAccessException(1);
			
			 venda.get().setStatus(StatusVenda.RECEBIDO);
			 
			 return venda.get();
		}
		
		throw new UsuarioNaoTemAcessoException();
	}

	private Collection<? extends GrantedAuthority> pegaPerfil() {
		Collection<? extends GrantedAuthority> perfis = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		return perfis;
	}
}
