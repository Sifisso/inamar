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
@Table(name="param_sub_area")
public class SubArea extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome_subArea")
	private String nome;
	
	@Column (name = "codigo")
	private String codigo;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id", insertable = true, updatable = true)
	private Area area;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "subArea")
	private List<TipoTaxa> tiposTaxas;*/
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subArea")
	private List<Taxa> taxas;

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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Taxa> getTaxas() {
		return taxas;
	}

	public void setTaxas(List<Taxa> taxas) {
		this.taxas = taxas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
