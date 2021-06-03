package com.example.starter.api.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.starter.api.model.UsuarioModel;
import com.example.starter.api.repository.UsuarioRepository;



public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recupararToken(request);
		System.out.println(token);
		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarClliente(token);
		}

		System.out.println("É valido: " + valido);

		filterChain.doFilter(request, response);
	}

	private void autenticarClliente(String token) {

		Long codigoUsuario = tokenService.getCodigoUsuario(token);
		UsuarioModel usuario = usuarioRepository.findById(codigoUsuario).get();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recupararToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		System.out.println("TOKENNNNNNNNNNNNNNNNNN "+token);

		if (token == null || token.isEmpty() || !token.startsWith("Bearer"))
			return null;

		return token.substring(7, token.length());
	}

}
