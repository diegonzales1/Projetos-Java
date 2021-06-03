package com.tarefas.taskList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tarefas.taskList.model.StatusTarefa;
import com.tarefas.taskList.model.Tarefa;
import com.tarefas.taskList.repository.Tarefas;

@Service
public class CadastroTarefaService {
	
	@Autowired
	private Tarefas tarefas;
	
	public void salvar(Tarefa tarefa) {
		try {
			tarefas.save(tarefa);
		}catch(DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	public void deletar(Long codigo) {
		tarefas.deleteById(codigo);
	}
	
	public String confirmar(Long codigo) {
		Tarefa tarefa = tarefas.getOne(codigo);
		tarefa.setStatus(StatusTarefa.FEITO);
		tarefas.save(tarefa);

		return StatusTarefa.FEITO.getDescricao();
	}
}
