package com.imobiliaria.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.BairroModel;
import com.imobiliaria.imobiliaria.repository.BairroRepository;
import com.imobiliaria.imobiliaria.repository.filter.BairroFilter;

@Service
public class BairroService {

	@Autowired
	BairroRepository bairroRepository;
	
	public void salvar(BairroModel bairro) {
		bairroRepository.save(bairro);
	}
	
	public void excluir(Long codigo) {
		bairroRepository.deleteById(codigo);
	}

	public List<BairroModel> filtrarBairro(BairroFilter filtro){
		String nomeBairro = filtro.getNomeBairro() == null ? "" : filtro.getNomeBairro();
		return bairroRepository.findByNomeBairroContaining(nomeBairro);
	}


}
