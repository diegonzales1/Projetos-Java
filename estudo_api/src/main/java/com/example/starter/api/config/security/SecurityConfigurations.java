package com.example.starter.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.starter.api.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private AutenticacaoService autenticacaoService;
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	//$2a$10$yNRR9XjRFlb4N.e1qWvStu78NKI7xgvETgTzcvlVjthMaBUch0bqq
	
	// Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/desafios").permitAll()
				.antMatchers(HttpMethod.GET, "/desafios").permitAll().antMatchers(HttpMethod.GET, "/desafios/*")
				.permitAll().antMatchers(HttpMethod.GET, "/starter").permitAll()
				.antMatchers(HttpMethod.GET, "/starter/*").permitAll().antMatchers(HttpMethod.GET, "/submissoes")
				.permitAll().antMatchers(HttpMethod.GET, "/submissoes/*").permitAll()
				.antMatchers(HttpMethod.GET, "notas").permitAll().antMatchers(HttpMethod.GET, "notas/*").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll().anyRequest().authenticated().and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
						UsernamePasswordAuthenticationFilter.class);
	}

	// Configuração de recursos estaticos(js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
