package com.imobiliaria.imobiliaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imobiliaria.imobiliaria.model.NegocioModel;
import com.imobiliaria.imobiliaria.repository.NegocioRepository;
import com.imobiliaria.imobiliaria.repository.filter.NegocioFilter;
import com.imobiliaria.imobiliaria.service.NegocioService;

@Controller
@RequestMapping("/home/negocio")
public class NegocioController {

	private static final String CAD_NEGOCIO = "Negocio/CadastroNegocio";

	@Autowired
	NegocioRepository negocioRepository;

	@Autowired
	NegocioService negocioService;

	@RequestMapping("/novoNegocio")
	public ModelAndView novoNegocio() {
		ModelAndView mv = new ModelAndView(CAD_NEGOCIO);
		mv.addObject(new NegocioModel());
		return mv;
	}

	// Save Category
	@RequestMapping(method = RequestMethod.POST)
	public String cadastrarNegocio(@Validated NegocioModel negocioModel, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CAD_NEGOCIO;
		}

		negocioService.salvar(negocioModel);
		attributes.addFlashAttribute("mensagem", "Negocio salvo com sucesso");

		return "redirect:/home/negocio/novoNegocio";
	}

	// Shows and Filters Category
	@RequestMapping("/listaNegocios")
	public ModelAndView listaNegocio(@ModelAttribute("filtro") NegocioFilter filtro) {

		List<NegocioModel> todosNegocios = negocioService.filtrar(filtro);

		ModelAndView mv = new ModelAndView("Negocio/MostraNegocio");
		mv.addObject("negocios", todosNegocios);
		return mv;
	}

	// Edit Category
	@RequestMapping("/listaNegocios/{codigo}")
	public ModelAndView excluirNegocio(@PathVariable("codigo") Long codigoNegocio) {
		NegocioModel negocioModel = negocioRepository.getOne(codigoNegocio);
		ModelAndView mv = new ModelAndView(CAD_NEGOCIO);
		mv.addObject(negocioModel);
		return mv;
	}

	// Delete Category
	@RequestMapping(value = "/listaNegocios/{codigo}", method = RequestMethod.DELETE)
	public String excluirNegocio(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			negocioService.excluir(codigo);

			attributes.addFlashAttribute("mensagem", "Tipo Negocio excluído com sucesso");
			return "redirect:/home/negocio/listaNegocios";
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Negócio está sendo usado");
			return "redirect:/home/negocio/listaNegocios";
		}

	}
}
