package com.imobiliaria.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.imobiliaria.model.BairroModel;

public interface BairroRepository extends JpaRepository<BairroModel, Long>{
	public List<BairroModel> findByNomeBairroContaining(String nomeBairro);
}
