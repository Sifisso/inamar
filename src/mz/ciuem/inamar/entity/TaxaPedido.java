package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="taxa_pedido")
public class TaxaPedido extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taxa_id", insertable = true, updatable = true)
	private Taxa taxa;
	
	private int valorr;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxaPedido")
	private List<PeticaoMaritimoTaxaPedido> peticoesMaritimosTaxasPedidos;
	
	public List<PeticaoMaritimoTaxaPedido> getPeticoesMaritimosTaxasPedidos() {
		return peticoesMaritimosTaxasPedidos;
	}

	public void setPeticoesMaritimosTaxasPedidos(
			List<PeticaoMaritimoTaxaPedido> peticoesMaritimosTaxasPedidos) {
		this.peticoesMaritimosTaxasPedidos = peticoesMaritimosTaxasPedidos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Taxa getTaxa() {
		return taxa;
	}

	public void setTaxa(Taxa taxa) {
		this.taxa = taxa;
	}

	public int getValorr() {
		return valorr;
	}

	public void setValorr(int valorr) {
		this.valorr = valorr;
	}

	
	
	
}
