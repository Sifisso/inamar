package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_tarefa")
public class Tarefa extends IdEntity{
	
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="descricao")
	private String descricao;

	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarefa")
	private List<TarefaNaEtapa> tarefasNasEtapas;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public List<TarefaNaEtapa> getTarefasNasEtapas() {
		return tarefasNasEtapas;
	}

	public void setTarefasNasEtapas(List<TarefaNaEtapa> tarefasNasEtapas) {
		this.tarefasNasEtapas = tarefasNasEtapas;
	}
	
	

}
