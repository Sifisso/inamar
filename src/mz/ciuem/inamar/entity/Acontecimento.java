package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_factos")
public class Acontecimento extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name = "isActivo")
	private boolean isActivo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}
	
}
