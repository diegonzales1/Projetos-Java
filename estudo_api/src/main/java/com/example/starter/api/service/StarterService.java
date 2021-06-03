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
import com.example.starter.api.model.StarterModel;
import com.example.starter.api.repository.StarterRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StarterService {

	private StarterRepository starterRepository;
	private ApplicationEventPublisher publisher;

	// List all Starters
	public List<StarterModel> listarStarters() {
		return starterRepository.findAll();
	}

	// Search for a Starter by code
	public Optional<StarterModel> buscarStarterPeloCodigo(Long codigo) {
		Optional<StarterModel> starter = starterRepository.findById(codigo);

		if (starter.isEmpty())
			throw new EmptyResultDataAccessException(1);

		return starter;
	}

	// Register a new Starter
	public ResponseEntity<StarterModel> cadastrarNovoStarter(StarterModel starter, HttpServletResponse response) {
		StarterModel starterSalvo = starterRepository.save(starter);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, starterSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(starterSalvo);
	}

	// Delete a Starter
	public ResponseEntity<Void> deletarStarterPeloCodigo(Long codigo) {
		if (!starterRepository.existsById(codigo))
			return ResponseEntity.notFound().build();

		starterRepository.deleteById(codigo);

		return ResponseEntity.noContent().build();
	}

	// Update a Starter
	public StarterModel atualizarStarter(Long codigo, StarterModel starter) {
		Optional<StarterModel> starterSalvo = buscarStarterPeloCodigo(codigo);
		BeanUtils.copyProperties(starter, starterSalvo.get(), "codigo");
		return starterRepository.save(starterSalvo.get());
	}

}
