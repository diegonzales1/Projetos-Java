package com.imobiliaria.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.imobiliaria.model.ImovelModel;

public interface ImovelRepository extends JpaRepository<ImovelModel, Long>{
	
}
