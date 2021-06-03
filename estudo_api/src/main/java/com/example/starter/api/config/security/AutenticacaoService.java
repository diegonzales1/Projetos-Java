package com.example.starter.api.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.starter.api.model.UsuarioModel;
import com.example.starter.api.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService{

	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(username);
		
		if(usuario.isPresent())
			return usuario.get();
		
		throw new UsernameNotFoundException("Dados inválidos");
	}

}
