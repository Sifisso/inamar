package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_pedido_requisito")
public class PeticaoPedidoRequisito extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	private boolean apresentou;
	
	private boolean validou;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticaoid", insertable = true, updatable = true, nullable=true)
	private Peticao peticao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedidoRequisito_id", insertable = true, updatable = true, nullable=true)
	private PedidoRequisito pedidoRequisito;

	public PedidoRequisito getPedidoRequisito() {
		return pedidoRequisito;
	}

	public void setPedidoRequisito(PedidoRequisito pedidoRequisito) {
		this.pedidoRequisito = pedidoRequisito;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public boolean isApresentou() {
		return apresentou;
	}

	public void setApresentou(boolean apresentou) {
		this.apresentou = apresentou;
	}

	public boolean isValidou() {
		return validou;
	}

	public void setValidou(boolean validou) {
		this.validou = validou;
	}
	
	
	

}
