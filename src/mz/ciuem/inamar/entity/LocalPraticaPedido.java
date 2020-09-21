package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="local_pratica_pedido")
public class LocalPraticaPedido extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localPratica_id", insertable = true, updatable = true)
	private LocalPratica localPratica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;

	public LocalPratica getLocalPratica() {
		return localPratica;
	}

	public void setLocalPratica(LocalPratica localPratica) {
		this.localPratica = localPratica;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
