package com.imobiliaria.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imobiliaria.imobiliaria.model.MunicipioModel;

public interface MunicipioRepository extends JpaRepository<MunicipioModel, Long>{
	public List<MunicipioModel> findByNomeMunicipioContaining(String nomeMunicipio);
	public List<MunicipioModel> findByEstado_Codigo(Long codigo);
}
