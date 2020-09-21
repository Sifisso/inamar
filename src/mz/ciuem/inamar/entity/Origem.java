package mz.ciuem.inamar.entity;

import javax.persistence.Entity;

@Entity
public class Origem extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String designacao;
	private String contacto;

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

}
