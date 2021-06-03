package com.imobiliaria.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.EstadoModel;
import com.imobiliaria.imobiliaria.repository.EstadoRepository;
import com.imobiliaria.imobiliaria.repository.filter.EstadoFilter;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	
	public void salvar(EstadoModel estadoModel) {
		estadoRepository.save(estadoModel);
	}
	
	public void excluir(Long codigo) {
		estadoRepository.deleteById(codigo);
	}
	
	public List<EstadoModel> filtrar(EstadoFilter filtro){
		String nomeEstado = filtro.getNomeEstado() == null ? "" : filtro.getNomeEstado();
		return estadoRepository.findByNomeEstadoContaining(nomeEstado);
	}
}
