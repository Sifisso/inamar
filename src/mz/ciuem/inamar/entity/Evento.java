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
@Table(name="evento")
public class Evento extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	private User user;
	
	private String tipo;
	
	private String sector;
	
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private String anexo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticao_etapa_id", insertable = true, updatable = true, nullable=true)
	private PeticaoEtapa peticaoEtapa;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

}
