package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_maritimo_taxa_pedido")
public class PeticaoMaritimoTaxaPedido extends IdEntity{
	//ignorar a parte maritimo pois no final do dia e' peticaoTaxaPedido
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticao_id", insertable = true, updatable = true, nullable=true)
	private Peticao peticao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taxaPedido_id", insertable = true, updatable = true, nullable=true)
	private TaxaPedido taxaPedido;
	
	private double valor;
	
	private boolean pago;

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public TaxaPedido getTaxaPedido() {
		return taxaPedido;
	}

	public void setTaxaPedido(TaxaPedido taxaPedido) {
		this.taxaPedido = taxaPedido;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public double getValor() {

		return valor;
	}

	

}
