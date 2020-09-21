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
@Table(name="param_area")
public class Area extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome_area")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="isAdmar")
	private boolean isAdmar;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	private List<TipoPedido> tiposPedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	private List<SubArea> subAreas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isAdmar() {
		return isAdmar;
	}

	public void setAdmar(boolean isAdmar) {
		this.isAdmar = isAdmar;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public List<TipoPedido> getTiposPedidos() {
		return tiposPedidos;
	}

	public void setTiposPedidos(List<TipoPedido> tiposPedidos) {
		this.tiposPedidos = tiposPedidos;
	}

	public List<SubArea> getSubAreas() {
		return subAreas;
	}

	public void setSubAreas(List<SubArea> subAreas) {
		this.subAreas = subAreas;
	}
	

}
