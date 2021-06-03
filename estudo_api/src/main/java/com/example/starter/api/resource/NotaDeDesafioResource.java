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

import com.example.starter.api.model.NotaDeDesafioModel;
import com.example.starter.api.service.NotaDeDesafioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/notas")
public class NotaDeDesafioResource {

	private NotaDeDesafioService notaDeDesafioService;

	// List all Challenge Notes
	@GetMapping
	public List<NotaDeDesafioModel> listar() {
		return notaDeDesafioService.listarNotasDeDesafios();
	}

	// Search for a Challenge Note
	@GetMapping("/{codigo}")
	public Optional<NotaDeDesafioModel> buscar(@PathVariable Long codigo) {
		return notaDeDesafioService.buscarNotaPeloCodigo(codigo);
	}

	// Register a new Challenge Note
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<NotaDeDesafioModel> novo(@Validated @RequestBody NotaDeDesafioModel nota,
			HttpServletResponse response) {
		return notaDeDesafioService.cadastrarNovaNota(nota, response);
	}

	// Update a Challenge Note
	@PutMapping("/{codigo}")
	public ResponseEntity<NotaDeDesafioModel> editar(@PathVariable Long codigo,
			@Validated @RequestBody NotaDeDesafioModel nota) {
		NotaDeDesafioModel notaSalva = notaDeDesafioService.atualizarNota(codigo, nota);
		return ResponseEntity.ok(notaSalva);
	}

	// Delete a Challenge Note
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		return notaDeDesafioService.deletarNotaPeloCodigo(codigo);
	}

}
