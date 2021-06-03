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
import com.example.starter.api.model.NotaDeDesafioModel;
import com.example.starter.api.repository.NotaDeDesafioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotaDeDesafioService {

	private NotaDeDesafioRepository notaDeDesafioRepository;
	private ApplicationEventPublisher publisher;

	// List all Challenge Notes
	public List<NotaDeDesafioModel> listarNotasDeDesafios() {
		return notaDeDesafioRepository.findAll();
	}

	// Search for a Challenge Note
	public Optional<NotaDeDesafioModel> buscarNotaPeloCodigo(Long codigo) {
		Optional<NotaDeDesafioModel> nota = notaDeDesafioRepository.findById(codigo);

		if (nota.isEmpty())
			throw new EmptyResultDataAccessException(1);

		return nota;
	}

	// Register a new Challenge Note
	public ResponseEntity<NotaDeDesafioModel> cadastrarNovaNota(NotaDeDesafioModel nota, HttpServletResponse response) {
		NotaDeDesafioModel notaSalva = notaDeDesafioRepository.save(nota);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, notaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(notaSalva);
	}

	// Delete a Challenge Note
	public ResponseEntity<Void> deletarNotaPeloCodigo(Long codigo) {
		if (!notaDeDesafioRepository.existsById(codigo))
			return ResponseEntity.notFound().build();

		notaDeDesafioRepository.deleteById(codigo);

		return ResponseEntity.noContent().build();
	}

	// Update a Challenge Note
	public NotaDeDesafioModel atualizarNota(Long codigo, NotaDeDesafioModel nota) {
		Optional<NotaDeDesafioModel> notaSalva = buscarNotaPeloCodigo(codigo);
		BeanUtils.copyProperties(nota, notaSalva.get(), "codigo");
		return notaDeDesafioRepository.save(notaSalva.get());
	}
}
