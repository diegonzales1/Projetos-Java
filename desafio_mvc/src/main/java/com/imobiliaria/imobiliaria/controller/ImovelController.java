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

import com.imobiliaria.imobiliaria.model.CategoriaModel;
import com.imobiliaria.imobiliaria.model.EstadoModel;
import com.imobiliaria.imobiliaria.model.ImovelModel;
import com.imobiliaria.imobiliaria.model.NegocioModel;
import com.imobiliaria.imobiliaria.model.QuartoModel;
import com.imobiliaria.imobiliaria.repository.CategoriaRepository;
import com.imobiliaria.imobiliaria.repository.EstadoRepository;
import com.imobiliaria.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.imobiliaria.repository.NegocioRepository;
import com.imobiliaria.imobiliaria.repository.QuartoRepository;
import com.imobiliaria.imobiliaria.service.ImovelService;

@Controller
@RequestMapping("/home/imovel")
public class ImovelController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	NegocioRepository negocioRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	QuartoRepository quartoRepository;

	@Autowired
	ImovelRepository imovelRepository;

	@Autowired
	ImovelService imovelService;

	private static final String CAD_IMOVEL = "Imovel/CadastroImovel";

	@RequestMapping("/novoImovel")
	public ModelAndView novoImovel() {

		ModelAndView mv = new ModelAndView(CAD_IMOVEL);
		mv.addObject(new ImovelModel());
		mv.addObject(todosEstados());
		mv.addObject(todasCategorias());
		mv.addObject(todosNegocios());
		mv.addObject(todosQuartos());

		return mv;
	}

	// Save
	@RequestMapping(method = RequestMethod.POST)
	public String salvarImovel(@Validated ImovelModel imovelModel, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CAD_IMOVEL;
		}

		imovelService.salvar(imovelModel);
		attributes.addFlashAttribute("mensagem", "Imóvel salvo com Sucesso!");

		return "redirect:/home/imovel/novoImovel";
	}

	// Shows
	@RequestMapping("/listaImoveis")
	public ModelAndView listaImoveis() {
		ModelAndView mv = new ModelAndView("Imovel/MostraImovel");

		List<ImovelModel> listaImovel = imovelRepository.findAll();

		mv.addObject(todosEstados());
		mv.addObject(todasCategorias());
		mv.addObject(todosNegocios());
		mv.addObject(todosQuartos());
		mv.addObject("imoveis", listaImovel);
		return mv;
	}

	// Edit
	@RequestMapping("/listaImoveis/{codigo}")
	public ModelAndView editarImovel(@PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView(CAD_IMOVEL);

		ImovelModel imovelModel = imovelRepository.getOne(codigo);
		NegocioModel negocioModel = negocioRepository.getOne(codigo);
		CategoriaModel categoriaModel = categoriaRepository.getOne(codigo);
		EstadoModel estadoModel = estadoRepository.getOne(codigo);
		QuartoModel quartoModel = quartoRepository.getOne(codigo);

		mv.addObject(imovelModel);
		mv.addObject(negocioModel);
		mv.addObject(categoriaModel);
		mv.addObject(estadoModel);
		mv.addObject(quartoModel);
		return mv;
	}

	// Delete
	@RequestMapping(value = "/listaImoveis/{codigo}", method = RequestMethod.DELETE)
	public String excluirImovel(@PathVariable Long codigo, RedirectAttributes attributes) {
		try {
			imovelService.excluir(codigo);
			attributes.addFlashAttribute("mensagem", "Imóvel excluído com sucesso");

			return "redirect:/home/imovel/listaImoveis";
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Imóvel está sendo usado");
			return "redirect:/home/imovel/listaImoveis";
		}
	}

	//Lists

	@ModelAttribute("estados")
	public List<EstadoModel> todosEstados() {
		return estadoRepository.findAll();
	}

	@ModelAttribute("negocios")
	public List<NegocioModel> todosNegocios() {
		return negocioRepository.findAll();
	}

	@ModelAttribute("categorias")
	public List<CategoriaModel> todasCategorias() {
		return categoriaRepository.findAll();
	}

	@ModelAttribute("quartos")
	public List<QuartoModel> todosQuartos() {
		return quartoRepository.findAll();
	}

}
