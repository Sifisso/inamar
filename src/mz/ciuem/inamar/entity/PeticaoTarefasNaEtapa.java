package mz.ciuem.inamar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="paticao_tarefa_etapa")
public class PeticaoTarefasNaEtapa extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticao_etapa_id", insertable = true, updatable = true, nullable=true)
	private PeticaoEtapa peticaoEtapa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = true, updatable = true, nullable=true)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarefa_etapa_id", insertable = true, updatable = true, nullable=true)
	private TarefaNaEtapa tarefaNaEtapa;


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

	public PeticaoEtapa getPeticaoEtapa() {
		return peticaoEtapa;
	}

	public void setPeticaoEtapa(PeticaoEtapa peticaoEtapa) {
		this.peticaoEtapa = peticaoEtapa;
	}

	public TarefaNaEtapa getTarefaNaEtapa() {
		return tarefaNaEtapa;
	}

	public void setTarefaNaEtapa(TarefaNaEtapa tarefaNaEtapa) {
		this.tarefaNaEtapa = tarefaNaEtapa;
	}
	
}
