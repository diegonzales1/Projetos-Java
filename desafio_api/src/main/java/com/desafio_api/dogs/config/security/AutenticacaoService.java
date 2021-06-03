package com.desafio_api.dogs.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafio_api.dogs.model.Usuario;
import com.desafio_api.dogs.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService{

	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(email);
		if(usuario.isPresent())
			return usuario.get();
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}
}
