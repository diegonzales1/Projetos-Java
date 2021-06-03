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

import com.example.starter.api.model.StarterModel;
import com.example.starter.api.service.StarterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/starter")
public class StarterResource {

	private StarterService starterService;

	// List all Starters
	@GetMapping
	public List<StarterModel> listar() {
		return starterService.listarStarters();
	}

	// Search for a Starter by code
	@GetMapping("/{codigo}")
	public Optional<StarterModel> buscar(@PathVariable Long codigo) {
		return starterService.buscarStarterPeloCodigo(codigo);
	}

	// Register a new Starter
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<StarterModel> novo(@Validated @RequestBody StarterModel starter,
			HttpServletResponse response) {
		return starterService.cadastrarNovoStarter(starter, response);
	}

	// Update a Starter
	@PutMapping("/{codigo}")
	public ResponseEntity<StarterModel> editar(@PathVariable Long codigo,
			@Validated @RequestBody StarterModel starter) {
		StarterModel starterSalvo = starterService.atualizarStarter(codigo, starter);
		return ResponseEntity.ok(starterSalvo);
	}

	// Delete a Starter
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		return starterService.deletarStarterPeloCodigo(codigo);
	}

}
