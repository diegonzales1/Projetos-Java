package com.desafio_api.dogs.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.desafio_api.dogs.model.Usuario;
import com.desafio_api.dogs.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.usuarioRepository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		System.out.println(valido);

		if (valido) {
			autenticarClliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}

	private void autenticarClliente(String token) {

		Long codigoUsuario = tokenService.getCodigoUsuario(token);
		Usuario usuario = usuarioRepository.findById(codigoUsuario).get();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
