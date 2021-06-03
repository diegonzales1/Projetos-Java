package com.imobiliaria.imobiliaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MenuPrincipalController {

	@RequestMapping
	public String menuPrincial() {
		return "/Home/MenuPrincipal";
	}
}
