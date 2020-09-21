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
@Table(name="pedido_requisito")
public class PedidoRequisito extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao_Tpedido")
	private String descricao;
	
	@Column(name="isMostrarUtente")
	private boolean isMostrarUtente;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoRequisito_id", insertable = true, updatable = true)
	private TipoRequisito tipoRequisito;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoRequisito")
	private List<PeticaoPedidoRequisito> peticoesPedidoRequisito;
	
	public List<PeticaoPedidoRequisito> getPeticoesPedidoRequisito() {
		return peticoesPedidoRequisito;
	}

	public void setPeticoesPedidoRequisito(
			List<PeticaoPedidoRequisito> peticoesPedidoRequisito) {
		this.peticoesPedidoRequisito = peticoesPedidoRequisito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isMostrarUtente() {
		return isMostrarUtente;
	}

	public void setMostrarUtente(boolean isMostrarUtente) {
		this.isMostrarUtente = isMostrarUtente;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public TipoRequisito getTipoRequisito() {
		return tipoRequisito;
	}

	public void setTipoRequisito(TipoRequisito tipoRequisito) {
		this.tipoRequisito = tipoRequisito;
	}
	
}
