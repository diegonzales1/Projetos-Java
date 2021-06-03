package com.tarefas.taskList.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import net.bytebuddy.asm.Advice.This;

import java.util.Date;


@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message ="Descrição é obrigatória")
	@Size(max = 100, message = "A descrição não pode conter mais de 100 caracteres")
	private String descricao;
	
	@NotNull(message = "Data Final é obrigatória")
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	
	@Enumerated(EnumType.STRING)
	private StatusTarefa status;
	
	public boolean isFazer() {
		return StatusTarefa.FAZER.equals(this.status);
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public StatusTarefa getStatus() {
		return status;
	}
	public void setStatus(StatusTarefa status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
