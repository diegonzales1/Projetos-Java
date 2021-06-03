package com.desafio_api.dogs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio_api.dogs.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
}
