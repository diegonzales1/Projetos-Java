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
import com.example.starter.api.model.SubmissaoDeDesafioModel;
import com.example.starter.api.repository.SubmissaoDeDesafioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SubmissaoDeDesafioService {

	private SubmissaoDeDesafioRepository submissaoDeDesafioRepository;
	private ApplicationEventPublisher publisher;

	// List all challenge submission
	public List<SubmissaoDeDesafioModel> listarSubmissoesDeDesafio() {
		return submissaoDeDesafioRepository.findAll();
	}

	// Search for a Submission by code
	public Optional<SubmissaoDeDesafioModel> buscarSubmissaoPeloCodigo(Long codigo) {
		Optional<SubmissaoDeDesafioModel> submissao = submissaoDeDesafioRepository.findById(codigo);

		if (submissao.isEmpty())
			throw new EmptyResultDataAccessException(1);

		return submissao;
	}

	// Register a new Submission
	public ResponseEntity<SubmissaoDeDesafioModel> cadastrarNovoSubmissao(SubmissaoDeDesafioModel submissao,
			HttpServletResponse response) {
		SubmissaoDeDesafioModel submissaoSalvo = submissaoDeDesafioRepository.save(submissao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, submissaoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(submissaoSalvo);
	}

	// Delete a Submission
	public ResponseEntity<Void> deletarSubmissaoPeloCodigo(Long codigo) {
		if (!submissaoDeDesafioRepository.existsById(codigo))
			return ResponseEntity.notFound().build();

		submissaoDeDesafioRepository.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}

	// Update a Submission
	public SubmissaoDeDesafioModel atualizarSubmissao(Long codigo, SubmissaoDeDesafioModel submissao) {
		Optional<SubmissaoDeDesafioModel> submissaoSalvo = buscarSubmissaoPeloCodigo(codigo);
		BeanUtils.copyProperties(submissao, submissaoSalvo.get(), "codigo");
		return submissaoDeDesafioRepository.save(submissaoSalvo.get());
	}

}
