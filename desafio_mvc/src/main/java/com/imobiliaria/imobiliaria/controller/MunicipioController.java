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
import com.imobiliaria.imobiliaria.model.MunicipioModel;
import com.imobiliaria.imobiliaria.repository.EstadoRepository;
import com.imobiliaria.imobiliaria.repository.MunicipioRepository;
import com.imobiliaria.imobiliaria.repository.filter.MunicipioFilter;
import com.imobiliaria.imobiliaria.service.EstadoService;
import com.imobiliaria.imobiliaria.service.MunicipioService;

@Controller
@RequestMapping("/home/municipio")
public class MunicipioController {

	private static final String CAD_MUNICIPIO = "Municipio/CadastroMunicipio";

	@Autowired
	MunicipioRepository municipioRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	EstadoService estadoService;

	@Autowired
	MunicipioService municipioService;

	@RequestMapping("/novoMunicipio")
	public ModelAndView novoMunicipio() {
		ModelAndView mv = new ModelAndView(CAD_MUNICIPIO);

		mv.addObject(new MunicipioModel());
		mv.addObject(todosEstados());

		return mv;
	}

	// Save
	@RequestMapping(method = RequestMethod.POST)
	public String salvarEstado(@Validated MunicipioModel municipioModel, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CAD_MUNICIPIO;
		}

		municipioService.salvar(municipioModel);
		attributes.addFlashAttribute("mensagem", "Municipio salvo com Sucesso!");
		return "redirect:/home/municipio/novoMunicipio";
	}

	// Shows and Filters
	@RequestMapping("/listaMunicipios")
	public ModelAndView listaMunicipio(@ModelAttribute("filtro") MunicipioFilter filtro) {
		List<MunicipioModel> todosMunicipios = municipioService.filtrar(filtro);

		ModelAndView mv = new ModelAndView("Municipio/MostraMunicipio");
		mv.addObject("municipios", todosMunicipios);
		return mv;
	}

	// Edit
	@RequestMapping("/listaMunicipios/{codigo}")
	public ModelAndView editarMunicipio(@PathVariable("codigo") Long codigoMunicipio) {
		ModelAndView mv = new ModelAndView(CAD_MUNICIPIO);

		MunicipioModel municipioModel = municipioRepository.getOne(codigoMunicipio);
		mv.addObject(municipioModel);

		List<EstadoModel> todosEstados = estadoRepository.findAll();
		mv.addObject("estados", todosEstados);

		return mv;
	}

	// Delete
	@RequestMapping(value = "/listaMunicipios/{codigo}", method = RequestMethod.DELETE)
	public String excluirMunicipio(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			municipioService.excluir(codigo);

			attributes.addFlashAttribute("mensagem", "Município excluído com sucesso");
			return "redirect:/home/municipio/listaMunicipios";
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Município está sendo usado");
			return "redirect:/home/municipio/listaMunicipios";
		}

	}

	// List
	@ModelAttribute("estados")
	public List<EstadoModel> todosEstados() {
		return estadoRepository.findAll();
	}

}
