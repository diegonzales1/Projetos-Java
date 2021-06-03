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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imobiliaria.imobiliaria.model.BairroModel;
import com.imobiliaria.imobiliaria.model.EstadoModel;
import com.imobiliaria.imobiliaria.model.MunicipioModel;
import com.imobiliaria.imobiliaria.repository.BairroRepository;
import com.imobiliaria.imobiliaria.repository.EstadoRepository;
import com.imobiliaria.imobiliaria.repository.MunicipioRepository;
import com.imobiliaria.imobiliaria.repository.filter.BairroFilter;
import com.imobiliaria.imobiliaria.service.BairroService;

@Controller
@RequestMapping("/home/bairro")
public class BairroController {

	private static final String CAD_BAIRRO = "Bairro/CadastroBairro";

	@Autowired
	BairroRepository bairroRepository;

	@Autowired
	BairroService bairroService;

	@Autowired
	MunicipioRepository municipioRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@RequestMapping("/novoBairro")
	public ModelAndView novoBairro() {

		ModelAndView mv = new ModelAndView(CAD_BAIRRO);

		mv.addObject(new BairroModel());
		mv.addObject(todosEstados());
		mv.addObject(todosMunicipios());

		return mv;
	}

	// Save
	@RequestMapping(method = RequestMethod.POST)
	public String salvarBairro(@Validated BairroModel bairroModel, Errors errors, RedirectAttributes attributes) {
		
		
		if (errors.hasErrors()) {
			return CAD_BAIRRO;
		}


		bairroService.salvar(bairroModel);
		attributes.addFlashAttribute("mensagem", "Bairro salvo com Sucesso!");

		return "redirect:/home/bairro/novoBairro";
	}

	// Shows and Filters
	@RequestMapping("/listaBairros")
	public ModelAndView listaBairro(@ModelAttribute("filtro") BairroFilter filtro) {
		ModelAndView mv = new ModelAndView("Bairro/MostraBairro");

		List<BairroModel> todosBairros = bairroService.filtrarBairro(filtro);

		mv.addObject(todosMunicipios());
		mv.addObject(todosEstados());
		mv.addObject("bairros", todosBairros);
		return mv;
	}

	// Edit
	@RequestMapping("/listaBairros/{codigo}")
	public ModelAndView editarBairro(@PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView(CAD_BAIRRO);

		BairroModel bairroModel = bairroRepository.getOne(codigo);
		EstadoModel estadoModel = estadoRepository.getOne(codigo);
		
		List<MunicipioModel> municipios = municipioRepository.findByEstado_Codigo(codigo);

		mv.addObject(bairroModel);
		mv.addObject(estadoModel);
		mv.addObject("municipios", municipios);

		return mv;
	}

	// Delete
	@RequestMapping(value = "/listaBairros/{codigo}", method = RequestMethod.DELETE)
	public String excluirBairro(@PathVariable Long codigo, RedirectAttributes attributes) {

		try {
			bairroService.excluir(codigo);

			attributes.addFlashAttribute("mensagem", "Bairro excluído com sucesso");
			return "redirect:/home/bairro/listaBairros";
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Bairro está sendo usado");
			return "redirect:/home/bairro/listaBairros";
		}
	}

	@RequestMapping(value = "/listarPorEstado", method = RequestMethod.GET)
	@ResponseBody
	public List<MunicipioModel> listaMunicipioPorEstado(Long codigoEstado) {
		
		List<MunicipioModel> listaMunicipios =  municipioRepository.findByEstado_Codigo(codigoEstado);
		return listaMunicipios;
	}

	// Lists

	@ModelAttribute("estados")
	public List<EstadoModel> todosEstados() {
		return estadoRepository.findAll();
	}

	@ModelAttribute("municipios")
	public List<MunicipioModel> todosMunicipios() {
		return municipioRepository.findAll();
	}

}
