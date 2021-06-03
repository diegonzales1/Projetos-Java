package com.imobiliaria.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.MunicipioModel;
import com.imobiliaria.imobiliaria.repository.MunicipioRepository;
import com.imobiliaria.imobiliaria.repository.filter.MunicipioFilter;

@Service
public class MunicipioService {

	@Autowired
	MunicipioRepository municipioRepository;

	public void salvar(MunicipioModel municipioModel) {
		municipioRepository.save(municipioModel);
	}
	
	public void excluir(Long codigo) {
		municipioRepository.deleteById(codigo);
	}
	
	public List<MunicipioModel> filtrar(MunicipioFilter filtro){
		String nomeMunicipio = filtro.getNomeMunicipio() == null ? "" : filtro.getNomeMunicipio();
		return municipioRepository.findByNomeMunicipioContaining(nomeMunicipio);
	}
}
