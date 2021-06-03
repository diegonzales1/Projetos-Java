package com.desafio_api.dogs.repository.Cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import com.desafio_api.dogs.model.Cliente;
import com.desafio_api.dogs.repository.filter.ClienteFilter;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cliente> filtrar(ClienteFilter clienteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);

		// Restrições
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Cliente> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(clienteFilter.getNome())) {
			predicates.add(
					builder.like(builder.lower(root.get("nome")), "%" + clienteFilter.getNome().toLowerCase() + "%"));
		}

		if (!ObjectUtils.isEmpty(clienteFilter.getCpf())) {
			predicates.add(builder.like(builder.lower(root.get("cpf")), "%" + clienteFilter.getCpf() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
