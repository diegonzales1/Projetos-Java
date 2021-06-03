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
import com.imobiliaria.imobiliaria.repository.CategoriaRepository;
import com.imobiliaria.imobiliaria.repository.filter.CategoriaFilter;
import com.imobiliaria.imobiliaria.service.CategoriaService;


@Controller
@RequestMapping("/home")
public class CategoriaController {

	private static final String CAD_CATEGORIA = "Categoria/CadastroCategoria";

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	CategoriaRepository categoriaRepository;

	@RequestMapping("/novaCategoria")
	public ModelAndView novaCategoria() {
		ModelAndView mv = new ModelAndView(CAD_CATEGORIA);
		mv.addObject(new CategoriaModel());
		return mv;
	}

	// Save
	@RequestMapping(method = RequestMethod.POST)
	public String salvarCategoria(@Validated CategoriaModel categoriaModel, Errors errors,
			RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Ocorreu um erro!");
			return CAD_CATEGORIA;
		}
		
		categoriaService.salvar(categoriaModel);
		attributes.addFlashAttribute("mensagem", "Categoria salva com Sucesso!");
		return "redirect:/home/novaCategoria";
	}

	// Shows and Filters
	@RequestMapping("/listaCategorias")
	public ModelAndView listaCategoria(@ModelAttribute("filtro") CategoriaFilter filtro) {

		List<CategoriaModel> todasCategorias = categoriaService.filtrar(filtro);

		ModelAndView mv = new ModelAndView("Categoria/MostraCategoria");
		mv.addObject("categorias", todasCategorias);
		return mv;
	}

	// Edit
	@RequestMapping("/listaCategorias/{codigo}")
	public ModelAndView editarCategoria(@PathVariable("codigo") Long codigoCadastro) {
		CategoriaModel categoriaModel = categoriaRepository.getOne(codigoCadastro);
		ModelAndView mv = new ModelAndView(CAD_CATEGORIA);
		mv.addObject(categoriaModel);
		return mv;
	}

	// Delete
	@RequestMapping(value = "/listaCategorias/{codigo}", method = RequestMethod.DELETE)
	public String excluirCategoria(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		try {
			categoriaService.excluir(codigo);

			attributes.addFlashAttribute("mensagem", "Categoria excluída com sucesso");
			return "redirect:/home/listaCategorias";
			
		}catch(Exception e) {
			
			attributes.addFlashAttribute("mensagemErro", "Erro ao excluir! Categoria está sendo usada");
			return "redirect:/home/listaCategorias";
		}
		
	}
}
