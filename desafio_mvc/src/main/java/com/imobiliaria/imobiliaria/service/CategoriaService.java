package com.imobiliaria.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imobiliaria.imobiliaria.model.CategoriaModel;
import com.imobiliaria.imobiliaria.repository.CategoriaRepository;
import com.imobiliaria.imobiliaria.repository.filter.CategoriaFilter;


@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	public void salvar(CategoriaModel categoria) {
		categoriaRepository.save(categoria);
	}
	
	public void excluir(Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
	
	public List<CategoriaModel> filtrar(CategoriaFilter filtro){
		String nomeCategoria = filtro.getNomeCategoria() == null ? "" : filtro.getNomeCategoria();
		return categoriaRepository.findByNomeCategoriaContaining(nomeCategoria);
	}
}
