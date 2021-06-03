package com.imobiliaria.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.imobiliaria.model.EstadoModel;

public interface EstadoRepository extends JpaRepository<EstadoModel, Long>{
	public List<EstadoModel> findByNomeEstadoContaining(String nomeEstado);
	public List<EstadoModel> findByCodigo(Long codigo);
}
