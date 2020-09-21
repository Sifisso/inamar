package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_fluxo")
public class Fluxo extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fluxo")
	private List<Pedido> pedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fluxo")
	private List<EtapaFluxo> etapasFluxos;

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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<EtapaFluxo> getEtapasFluxos() {
		return etapasFluxos;
	}

	public void setEtapasFluxos(List<EtapaFluxo> etapasFluxos) {
		this.etapasFluxos = etapasFluxos;
	}
	

}
