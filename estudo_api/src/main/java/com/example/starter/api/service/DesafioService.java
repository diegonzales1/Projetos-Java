package com.example.starter.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.starter.api.event.RecursoCriadoEvent;
import com.example.starter.api.model.DesafioModel;
import com.example.starter.api.repository.DesafioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DesafioService {

	private DesafioRepository desafioRepository;
	private ApplicationEventPublisher publisher;

	// List all Challenges
	public List<DesafioModel> listarDesafios() {
		return desafioRepository.findAll();
	}

	// Search for a challenge by code
	public Optional<DesafioModel> buscarDesafioPeloCodigo(Long codigo){
		Optional<DesafioModel> desafio = desafioRepository.findById(codigo);
		
		if(desafio.isEmpty())
			throw new EmptyResultDataAccessException(1);
		
		return desafio;
	}

	// Register a new challenge
	public ResponseEntity<DesafioModel> cadastrarNovoDesafio(DesafioModel desafio, HttpServletResponse response) {
		DesafioModel desafioSalvo = desafioRepository.save(desafio);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, desafioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(desafioSalvo);
	}

	// Delete a challenge
	public ResponseEntity<Void> deletarDesafioPeloCodigo(Long codigo) {
		if (!desafioRepository.existsById(codigo))
			return ResponseEntity.notFound().build();

		desafioRepository.deleteById(codigo);

		return ResponseEntity.noContent().build();
	}

	// Update a challenge
	public DesafioModel atualizarDesafio(Long codigo, DesafioModel desafio) {
		Optional<DesafioModel> desafioSalvo = buscarDesafioPeloCodigo(codigo);
		BeanUtils.copyProperties(desafio, desafioSalvo.get(), "codigo");
		return desafioRepository.save(desafioSalvo.get());
	}
}
