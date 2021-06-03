package com.imobiliaria.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.QuartoModel;
import com.imobiliaria.imobiliaria.repository.QuartoRepository;
import com.imobiliaria.imobiliaria.repository.filter.QuartoFilter;

@Service
public class QuartoService {

	@Autowired
	QuartoRepository quarto;

	public void salvar(QuartoModel quartoModel) {
		quarto.save(quartoModel);
	}

	public void excluir(Long codigo) {
		quarto.deleteById(codigo);
	}

	public List<QuartoModel> filtrar(QuartoFilter filtro) {
		String quantidadeQuarto = filtro.getQuantidadeQuarto() == null ? "" : filtro.getQuantidadeQuarto();
		return quarto.findByQuantidadeQuartoContaining(quantidadeQuarto);
	}

}
