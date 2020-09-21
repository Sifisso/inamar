package mz.ciuem.inamar.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="peticao_etapa")
public class PeticaoEtapa extends IdEntity{
	//etapaDaPeticao
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticao_id", insertable = true, updatable = true, nullable=true)
	private Peticao peticao;
	
	@Column(name = "user_id")
	private User user;
	
	@Column (name = "observacao")
	private String observacao;
	
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name="pede_parecer")
	private boolean pedeparecer;
	
	@Column(name="tem_resposta")
	private boolean temresposta;
	
	@Column(name="relaizada")
	private boolean realizada;
	
	@Column (name = "anexo")
	private String anexo;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "peticaoEtapa")
	private List<PeticaoTarefasNaEtapa> peticoesTarefasNaEtapa;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticaoEtapa")
	private List<Evento> eventos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etapa_fluxo_id", insertable = true, updatable = true, nullable=true)
	private EtapaFluxo etapaFluxo;
	
	public List<PeticaoTarefasNaEtapa> getPeticoesTarefasNaEtapa() {
		return peticoesTarefasNaEtapa;
	}

	public void setPeticoesTarefasNaEtapa(
			List<PeticaoTarefasNaEtapa> peticoesTarefasNaEtapa) {
		this.peticoesTarefasNaEtapa = peticoesTarefasNaEtapa;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public EtapaFluxo getEtapaFluxo() {
		return etapaFluxo;
	}

	public void setEtapaFluxo(EtapaFluxo etapaFluxo) {
		this.etapaFluxo = etapaFluxo;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isPedeparecer() {
		return pedeparecer;
	}

	public void setPedeparecer(boolean pedeparecer) {
		this.pedeparecer = pedeparecer;
	}

	public boolean isTemresposta() {
		return temresposta;
	}

	public void setTemresposta(boolean temresposta) {
		this.temresposta = temresposta;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
}
