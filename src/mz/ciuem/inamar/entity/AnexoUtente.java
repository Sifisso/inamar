package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="anexo")
public class AnexoUtente extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "utente_id")
	private long utenteId;
	
	@Column(name = "designacao")
	private String designacao;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "URI")
	private String URI;

	@Column(name = "tipo")
	private String tipo;

	public long getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(long utenteId) {
		this.utenteId = utenteId;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
