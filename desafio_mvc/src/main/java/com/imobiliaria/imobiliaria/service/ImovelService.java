package com.imobiliaria.imobiliaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.ImovelModel;
import com.imobiliaria.imobiliaria.repository.ImovelRepository;

@Service
public class ImovelService {

	@Autowired
	ImovelRepository imovelRepository;
	
	public void salvar(ImovelModel imovel) {
		imovelRepository.save(imovel);
	}
	
	public void excluir(Long codigo) {
		imovelRepository.deleteById(codigo);
	}
}
