package com.tarefas.taskList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarefas.taskList.model.Tarefa;

public interface Tarefas extends JpaRepository<Tarefa, Long>{

}
