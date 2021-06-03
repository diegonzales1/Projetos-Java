package com.example.starter.api.resource;



import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starter.api.config.security.TokenService;
import com.example.starter.api.resource.dto.TokenDTO;
import com.example.starter.api.resource.form.LoginForm;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AutenticacaoResource {

	private AuthenticationManager authManager;
	
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Validated LoginForm form){
		
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			
		Authentication authentication = authManager.authenticate(dadosLogin);
		
		String token = tokenService.gerarToken(authentication);
		
	
		return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		
		} catch(AuthenticationException ex ) {
			
			return ResponseEntity.badRequest().build();
		}
	}
}
