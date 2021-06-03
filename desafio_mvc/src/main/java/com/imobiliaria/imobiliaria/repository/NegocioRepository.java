package com.imobiliaria.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.imobiliaria.model.NegocioModel;

public interface NegocioRepository extends JpaRepository<NegocioModel, Long>{
	public List<NegocioModel> findByNomeNegocioContaining(String nomeNegocio);
}
