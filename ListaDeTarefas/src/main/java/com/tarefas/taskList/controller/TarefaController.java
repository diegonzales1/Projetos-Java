package com.tarefas.taskList.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.tarefas.taskList.model.StatusTarefa;
import com.tarefas.taskList.model.Tarefa;
import com.tarefas.taskList.repository.Tarefas;
import com.tarefas.taskList.service.CadastroTarefaService;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

	private static final String CADASTRO_VIEW = "CadastroTarefa";
	
	@Autowired
	private CadastroTarefaService cadastroTarefaService;

	@Autowired
	private Tarefas tarefas;

	@RequestMapping("/nova")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Tarefa());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Tarefa tarefa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		try {
			cadastroTarefaService.salvar(tarefa);
			attributes.addFlashAttribute("mensagem", "Tarefa salva com Sucesso!");
			return "redirect:/tarefas/nova";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataFinal", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Tarefa> todasTarefas = tarefas.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTarefa");
		mv.addObject("tarefas", todasTarefas);
		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Tarefa tarefa) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(tarefa);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroTarefaService.deletar(codigo);
		attributes.addFlashAttribute("mensagem", "Tarefa excluída com sucesso!");
		return "redirect:/tarefas";
	}
	
	@RequestMapping(value = "/{codigo}/confirmar", method = RequestMethod.PUT)
	public @ResponseBody String confirmar(@PathVariable Long codigo) {
		return cadastroTarefaService.confirmar(codigo);
	}

	@ModelAttribute("todosStatusTarefa")
	public List<StatusTarefa> todosStatusTarefa() {
		return Arrays.asList(StatusTarefa.values());
	}
}
