package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_pedido_etapa_instrumento_legal")
public class PeticaoPedidoEtapaInstrumentoLegal extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao")
	private String descricao;
	
	private boolean apresentou;
	
	private boolean validou;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticaoid", insertable = true, updatable = true, nullable=true)
	private Peticao peticao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_etapa_id", insertable = true, updatable = true, nullable=true)
	private PedidoEtapa pedidoEtapa;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public PedidoEtapa getPedidoEtapa() {
		return pedidoEtapa;
	}

	public void setPedidoEtapa(PedidoEtapa pedidoEtapa) {
		this.pedidoEtapa = pedidoEtapa;
	}
}
