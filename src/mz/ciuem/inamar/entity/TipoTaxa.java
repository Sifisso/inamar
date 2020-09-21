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
@Table(name="param_tipo_taxas")
public class TipoTaxa extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome_Tpedido")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id", insertable = true, updatable = true)
	private SubArea subArea;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoTaxa")
	private List<Taxa> pedidos;*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public SubArea getSubArea() {
		return subArea;
	}

	public void setSubArea(SubArea subArea) {
		this.subArea = subArea;
	}

}
