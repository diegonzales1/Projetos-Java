package com.tarefas.taskList.model;

public enum StatusTarefa {

	FAZER("A Fazer "), 
	FEITO("Concluída ");
	
	private String descricao;
	
	StatusTarefa(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
