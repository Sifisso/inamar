package mz.ciuem.inamar.entity;

import javax.persistence.Entity;

@Entity
public class Distino extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	private String designacao;
	private String contacto;
	private String tipo;

	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
