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
@Table(name="tbl_embarcacoesAcontecimento")
public class EmbarcacaoAcontecimento extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="dataOcorrencia") 
	@Temporal(TemporalType.DATE)
	private Date dataOcorrencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "embarcacao_id", insertable = true, updatable = true)
	private Embarcacao embarcacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acontecimento_id", insertable = true, updatable = true)
	private Acontecimento acontecimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id", insertable = true, updatable = true)
	private Pais pais;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userLoggado_id", insertable = true, updatable = true)
	private User userLoggado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public Embarcacao getEmbarcacao() {
		return embarcacao;
	}

	public void setEmbarcacao(Embarcacao embarcacao) {
		this.embarcacao = embarcacao;
	}

	public Acontecimento getAcontecimento() {
		return acontecimento;
	}

	public void setAcontecimento(Acontecimento acontecimento) {
		this.acontecimento = acontecimento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public User getUserLoggado() {
		return userLoggado;
	}

	public void setUserLoggado(User userLoggado) {
		this.userLoggado = userLoggado;
	}

	public Delegacao getDelegacao() {
		return delegacao;
	}

	public void setDelegacao(Delegacao delegacao) {
		this.delegacao = delegacao;
	}
}
