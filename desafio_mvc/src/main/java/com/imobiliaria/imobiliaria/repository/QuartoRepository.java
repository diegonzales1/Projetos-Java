package com.imobiliaria.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.imobiliaria.model.QuartoModel;

public interface QuartoRepository extends JpaRepository<QuartoModel, Long> {

	public List<QuartoModel> findByQuantidadeQuartoContaining(String quantidadeQuarto);
}
