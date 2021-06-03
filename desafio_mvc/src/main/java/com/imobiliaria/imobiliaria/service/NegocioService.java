package com.imobiliaria.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.NegocioModel;
import com.imobiliaria.imobiliaria.repository.NegocioRepository;
import com.imobiliaria.imobiliaria.repository.filter.NegocioFilter;

@Service
public class NegocioService {

	@Autowired
	NegocioRepository negocio;

	public void salvar(NegocioModel negocioModel) {
		negocio.save(negocioModel);
	}

	public void excluir(Long codigo) {
		negocio.deleteById(codigo);
	}

	public List<NegocioModel> filtrar(NegocioFilter filtro) {
		String nomeNegocio = filtro.getNomeNegocio() == null ? "" : filtro.getNomeNegocio();
		return negocio.findByNomeNegocioContaining(nomeNegocio);
	}
}
