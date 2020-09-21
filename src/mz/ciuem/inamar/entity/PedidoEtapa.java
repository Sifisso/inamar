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
@Table(name="pedido_Etapa")
public class PedidoEtapa extends IdEntity{
	//PeticaoInstrumentoLegalEtapa verdadeiro
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name="codigo")
	private String detalhes;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etapaFluxo_id", insertable = true, updatable = true)
	private EtapaFluxo etapaFluxo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insLegal_id", insertable = true, updatable = true)
	private InstrumentoLegal instrumentoLegal;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoEtapa")
	private List<PeticaoPedidoEtapaInstrumentoLegal> peticoesInstrumentoLegal;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
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

	public EtapaFluxo getEtapaFluxo() {
		return etapaFluxo;
	}

	public void setEtapaFluxo(EtapaFluxo etapaFluxo) {
		this.etapaFluxo = etapaFluxo;
	}

	public InstrumentoLegal getInstrumentoLegal() {
		return instrumentoLegal;
	}

	public List<PeticaoPedidoEtapaInstrumentoLegal> getPeticoesInstrumentoLegal() {
		return peticoesInstrumentoLegal;
	}

	public void setPeticoesInstrumentoLegal(
			List<PeticaoPedidoEtapaInstrumentoLegal> peticoesInstrumentoLegal) {
		this.peticoesInstrumentoLegal = peticoesInstrumentoLegal;
	}

	public void setInstrumentoLegal(InstrumentoLegal instrumentoLegal) {
		this.instrumentoLegal = instrumentoLegal;
	}
	
	
	
}
