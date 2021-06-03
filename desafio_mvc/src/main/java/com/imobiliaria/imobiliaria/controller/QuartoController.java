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

import com.imobiliaria.imobiliaria.model.QuartoModel;
import com.imobiliaria.imobiliaria.repository.QuartoRepository;
import com.imobiliaria.imobiliaria.repository.filter.QuartoFilter;
import com.imobiliaria.imobiliaria.service.QuartoService;

@Controller
@RequestMapping("/home/quarto")
public class QuartoController {

	private static final String CAD_QUARTO = "Quarto/CadastroQuarto";

	@Autowired
	QuartoService quartoService;

	@Autowired
	QuartoRepository quartoRepository;

	@RequestMapping("/novoQuarto")
	public ModelAndView novoQuarto() {
		ModelAndView mv = new ModelAndView(CAD_QUARTO);
		mv.addObject(new QuartoModel());
		return mv;
	}

	// Save Category
	@RequestMapping(method = RequestMethod.POST)
	public String salvarQuarto(@Validated QuartoModel quartoModel, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CAD_QUARTO;
		}

		quartoService.salvar(quartoModel);
		attributes.addFlashAttribute("mensagem", "Quarto salvo com sucesso");

		return "redirect:/home/quarto/novoQuarto";
	}

	// Shows and Filters Category
	@RequestMapping("/listaQuartos")
	public ModelAndView listaQuarto(@ModelAttribute("filtro") QuartoFilter filtro) {

		List<QuartoModel> todosQuartos = quartoService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("Quarto/MostraQuarto");
		mv.addObject("quartos", todosQuartos);
		return mv;
	}

	// Edit Category
	@RequestMapping("/listaQuartos/{codigo}")
	public ModelAndView editaQuarto(@PathVariable("codigo") Long codigoQuarto) {
		QuartoModel quartoModel = quartoRepository.getOne(codigoQuarto);
		ModelAndView mv = new ModelAndView(CAD_QUARTO);
		mv.addObject(quartoModel);
		return mv;
	}

	// Delete Category
	@RequestMapping(value = "/listaQuartos/{codigo}", method = RequestMethod.DELETE)
	public String excluirQuarto(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			quartoService.excluir(codigo);

			attributes.addFlashAttribute("mensagem", "Quarto excluído com sucesso");
			return "redirect:/home/quarto/listaQuartos";
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Quarto está sendo usado ");
			return "redirect:/home/quarto/listaQuartos";
		}

	}

}
