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

import com.example.starter.api.model.DesafioModel;
import com.example.starter.api.service.DesafioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/desafios")
public class DesafioResource {

	private DesafioService desafioService;

	// List all Challenges
	@GetMapping
	public List<DesafioModel> listar() {
		return desafioService.listarDesafios();
	}

	// Search for a challenge by code
	@GetMapping("/{codigo}")
	public Optional<DesafioModel> buscar(@PathVariable Long codigo) {
		return desafioService.buscarDesafioPeloCodigo(codigo);
	}

	// Register a new challenge
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<DesafioModel> novo(@Validated @RequestBody DesafioModel desafio,
			HttpServletResponse response) {
		return desafioService.cadastrarNovoDesafio(desafio, response);
	}

	// Update a challenge
	@PutMapping("/{codigo}")
	public ResponseEntity<DesafioModel> editar(@PathVariable Long codigo,
			@Validated @RequestBody DesafioModel desafio) {
		DesafioModel desafioSalvo = desafioService.atualizarDesafio(codigo, desafio);
		return ResponseEntity.ok(desafioSalvo);
	}

	// Delete a challenge
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		return desafioService.deletarDesafioPeloCodigo(codigo);
	}
}
