package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipo_requisito")
public class TipoRequisito extends IdEntity{
	
private static final long serialVersionUID = 1L;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@Column(name="isVisivelUtente")
	private boolean isVisivelUtente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoRequisito")
	private List<PedidoRequisito> pedidosRequisitos;

	public boolean isVisivelUtente() {
		return isVisivelUtente;
	}

	public void setVisivelUtente(boolean isVisivelUtente) {
		this.isVisivelUtente = isVisivelUtente;
	}

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

	public List<PedidoRequisito> getPedidosRequisitos() {
		return pedidosRequisitos;
	}

	public void setPedidosRequisitos(List<PedidoRequisito> pedidosRequisitos) {
		this.pedidosRequisitos = pedidosRequisitos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
