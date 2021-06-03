package com.desafio_api.dogs.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.desafio_api.dogs.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	private AutenticacaoService autenticacaoService;
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	private static final String AUTENTICACAO = "/auth";

	// Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
				.antMatchers(HttpMethod.GET, "/estoque/**").permitAll().antMatchers(HttpMethod.GET, "/clientes/**")
				.permitAll().antMatchers(HttpMethod.GET, "/fornecedores/*").permitAll()
				.antMatchers(HttpMethod.GET, "/vendas").permitAll().antMatchers(HttpMethod.GET, "/compra").permitAll()
				.antMatchers(HttpMethod.POST, AUTENTICACAO).permitAll().anyRequest().authenticated().and().csrf()
				.disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
