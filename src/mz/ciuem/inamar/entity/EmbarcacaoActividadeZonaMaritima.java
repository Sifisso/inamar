package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="embarcacao_zona_actividade_maritima")
public class EmbarcacaoActividadeZonaMaritima extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "embarcacao_id", insertable = true, updatable = true)
	private Embarcacao embarcacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "act_zona_Mar_id", insertable = true, updatable = true)
	private ActividadeZonaMaritima actividadeZonaMaritima;  
	
	public Embarcacao getEmbarcacao() {
		return embarcacao;
	}

	public void setEmbarcacao(Embarcacao embarcacao) {
		this.embarcacao = embarcacao;
	}

	public ActividadeZonaMaritima getActividadeZonaMaritima() {
		return actividadeZonaMaritima;
	}

	public void setActividadeZonaMaritima(ActividadeZonaMaritima actividadeZonaMaritima) {
		this.actividadeZonaMaritima = actividadeZonaMaritima;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



}
