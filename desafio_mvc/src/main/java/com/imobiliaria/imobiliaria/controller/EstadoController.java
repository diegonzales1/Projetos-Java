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

import com.imobiliaria.imobiliaria.model.EstadoModel;
import com.imobiliaria.imobiliaria.repository.EstadoRepository;
import com.imobiliaria.imobiliaria.repository.filter.EstadoFilter;
import com.imobiliaria.imobiliaria.service.EstadoService;

@Controller
@RequestMapping("/home/estado")
public class EstadoController {

	private static final String CAD_ESTADO = "Estado/CadastroEstado";

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	EstadoService estadoService;

	@RequestMapping("/novoEstado")
	public ModelAndView novoEstado() {
		ModelAndView mv = new ModelAndView(CAD_ESTADO);
		mv.addObject(new EstadoModel());
		return mv;
	}

	// Save
	@RequestMapping(method = RequestMethod.POST)
	public String salvarEstado(@Validated EstadoModel estadoModel, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CAD_ESTADO;
		}

		estadoService.salvar(estadoModel);
		attributes.addFlashAttribute("mensagem", "Estado salvo com Sucesso!");
		return "redirect:/home/estado/novoEstado";
	}

	// Shows and Filters
	@RequestMapping("/listaEstados")
	public ModelAndView listaEstado(@ModelAttribute("filtro") EstadoFilter filtro) {
		List<EstadoModel> todosEstados = estadoService.filtrar(filtro);

		ModelAndView mv = new ModelAndView("Estado/MostraEstado");
		mv.addObject("estados", todosEstados);
		return mv;
	}

	// Edit
	@RequestMapping("/listaEstados/{codigo}")
	public ModelAndView editarEstado(@PathVariable("codigo") Long codigoEstado) {
		EstadoModel estadoModel = estadoRepository.getOne(codigoEstado);
		ModelAndView mv = new ModelAndView(CAD_ESTADO);
		mv.addObject(estadoModel);
		return mv;
	}

	// Delete
	@RequestMapping(value = "/listaEstados/{codigo}", method = RequestMethod.DELETE)
	public String excluirEstado(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			estadoService.excluir(codigo);

			attributes.addFlashAttribute("mensagem", "Estado excluído com sucesso");
			return "redirect:/home/estado/listaEstados";
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Estado está sendo usado ");
			return "redirect:/home/estado/listaEstados";
		}
	}

}
