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
@Table(name="etapa_fluxo")
public class EtapaFluxo extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name="isDaParecer")
	private boolean isDaParecer;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "etapaFluxo")
	private List<TarefaNaEtapa> tarefasNasEtapas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etapaFluxo")
	private List<PedidoEtapa> pedidosEtapas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etapaFluxo")
	private List<PeticaoEtapa> peticoesEtapa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fluxo_id", insertable = true, updatable = true)
	private Fluxo fluxo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etapa_id", insertable = true, updatable = true)
	private Etapa etapa;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_id", insertable = true, updatable = true)
	private Estado estado;

	public List<PeticaoEtapa> getPeticoesEtapa() {
		return peticoesEtapa;
	}


	public void setPeticoesEtapa(List<PeticaoEtapa> peticoesEtapa) {
		this.peticoesEtapa = peticoesEtapa;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public boolean isDaParecer() {
		return isDaParecer;
	}


	public void setDaParecer(boolean isDaParecer) {
		this.isDaParecer = isDaParecer;
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


	public List<PedidoEtapa> getPedidosEtapas() {
		return pedidosEtapas;
	}


	public void setPedidosEtapas(List<PedidoEtapa> pedidosEtapas) {
		this.pedidosEtapas = pedidosEtapas;
	}


	public Fluxo getFluxo() {
		return fluxo;
	}


	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Etapa getEtapa() {
		return etapa;
	}


	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	
	

}
