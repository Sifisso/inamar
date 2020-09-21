package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tarefa_na_etapa")
public class TarefaNaEtapa extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name="detalhes")
	private String detalhes;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etapaFluxo_id", insertable = true, updatable = true)
	private EtapaFluxo etapaFluxo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarefa_id", insertable = true, updatable = true)
	private Tarefa tarefa;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarefaNaEtapa")
	private List<PeticaoTarefasNaEtapa> peticoesTarefasNaEtapa;
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public EtapaFluxo getEtapaFluxo() {
		return etapaFluxo;
	}

	public void setEtapaFluxo(EtapaFluxo etapaFluxo) {
		this.etapaFluxo = etapaFluxo;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<PeticaoTarefasNaEtapa> getPeticoesTarefasNaEtapa() {
		return peticoesTarefasNaEtapa;
	}

	public void setPeticoesTarefasNaEtapa(
			List<PeticaoTarefasNaEtapa> peticoesTarefasNaEtapa) {
		this.peticoesTarefasNaEtapa = peticoesTarefasNaEtapa;
	}
	
	
	
	
}
