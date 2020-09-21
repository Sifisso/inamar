package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="param_tipo_servico")
public class TipoServico extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@Column(name="isMostrarUtente")
	private boolean isMostrarUtente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taxa_id", insertable = true, updatable = true)
	private Taxa taxa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public boolean isMostrarUtente() {
		return isMostrarUtente;
	}

	public void setMostrarUtente(boolean isMostrarUtente) {
		this.isMostrarUtente = isMostrarUtente;
	}

	public Taxa getTaxa() {
		return taxa;
	}

	public void setTaxa(Taxa taxa) {
		this.taxa = taxa;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	

}
