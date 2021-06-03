package com.example.starter.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.starter.api.model.SubmissaoDeDesafioModel;
import com.example.starter.api.service.SubmissaoDeDesafioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/submissoes")
public class SubmissaoDeDesafioResource {

	private SubmissaoDeDesafioService submissaoDeDesafioService;

	// List all challenge Submission
	@GetMapping
	public List<SubmissaoDeDesafioModel> listar() {
		return submissaoDeDesafioService.listarSubmissoesDeDesafio();
	}

	// Search for a Submission by code
	@GetMapping("/{codigo}")
	public Optional<SubmissaoDeDesafioModel> buscar(@PathVariable Long codigo) {
		return submissaoDeDesafioService.buscarSubmissaoPeloCodigo(codigo);
	}

	// Register a new Submission
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<SubmissaoDeDesafioModel> novo(@Validated @RequestBody SubmissaoDeDesafioModel submissao,
			HttpServletResponse response) {
		return submissaoDeDesafioService.cadastrarNovoSubmissao(submissao, response);
	}

	// Update a Submission
	@PutMapping("/{codigo}")
	public ResponseEntity<SubmissaoDeDesafioModel> editar(@PathVariable Long codigo,
			@Validated @RequestBody SubmissaoDeDesafioModel submissao) {

		SubmissaoDeDesafioModel submissaoSalvo = submissaoDeDesafioService.atualizarSubmissao(codigo, submissao);
		return ResponseEntity.ok(submissaoSalvo);
	}

	// Delete a Submission
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo){
		return submissaoDeDesafioService.deletarSubmissaoPeloCodigo(codigo);
	}
}
