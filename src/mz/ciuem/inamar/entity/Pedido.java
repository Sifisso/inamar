package mz.ciuem.inamar.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
import mz.ciuem.inamar.service.TaxaPedidoService;

import org.hibernate.annotations.Fetch;

@Entity
@Table(name="param_pedido")
public class Pedido extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="isAdmar")
	private boolean isAdmar;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@Column(name="isMostraFactura")
	private boolean isMostraFactura;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<TipoServico> tipoServicos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<TarefaNaEtapa> tarefasNaEtapa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoPedido_id", insertable = true, updatable = true)
	private TipoPedido tipoPedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fluxo_id", insertable = true, updatable = true)
	private Fluxo fluxo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<PedidoEtapa> pedidosEtapas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<Peticao> peticoes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<LocalPraticaPedido> locaisPraticaPedidos;
	
	/*@SuppressWarnings("deprecation")
	@IndexColumn(name="pedido")*/
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private Set<RotaPedido> rotaPedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<EquipamentoPedido> equipamentosPedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<TipoEmbarcacaoPedido> tipoEmbarcacaoPedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<MeioTransportePedido> meioTransportePedido;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<ContagemPedido> contagemPedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<CursoPedido> cursosPedidos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<TaxaPedido> taxasPedido;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taxaPedido_id")
	private TaxaPedido taxaPedido;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<PedidoRequisito> pedidoRequisitos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<PeticaoMaritimo> peticaoMaritimo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "peticao_id")
	private Peticao peticao; 
	
	public List<PeticaoMaritimo> getPeticaoMaritimo() {
		return peticaoMaritimo;
	}
	
	public List<TarefaNaEtapa> getTarefasNaEtapa() {
		return tarefasNaEtapa;
	}

	public void setTarefasNaEtapa(List<TarefaNaEtapa> tarefasNaEtapa) {
		this.tarefasNaEtapa = tarefasNaEtapa;
	}

	public void setPeticaoMaritimo(List<PeticaoMaritimo> peticaoMaritimo) {
		this.peticaoMaritimo = peticaoMaritimo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAdmar() {
		return isAdmar;
	}

	public void setAdmar(boolean isAdmar) {
		this.isAdmar = isAdmar;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public TipoPedido getTipoPedido() {
		return tipoPedido;
	}

	public void setTipoPedido(TipoPedido tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public List<PedidoRequisito> getPedidoRequisitos() {
		return pedidoRequisitos;
	}

	public void setPedidoRequisitos(List<PedidoRequisito> pedidoRequisitos) {
		this.pedidoRequisitos = pedidoRequisitos;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public Fluxo getFluxo() {
		return fluxo;
	}

	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
	}

	public boolean isMostraFactura() {
		return isMostraFactura;
	}

	public void setMostraFactura(boolean isMostraFactura) {
		this.isMostraFactura = isMostraFactura;
	}

	public List<PedidoEtapa> getPedidosEtapas() {
		return pedidosEtapas;
	}

	public void setPedidosEtapas(List<PedidoEtapa> pedidosEtapas) {
		this.pedidosEtapas = pedidosEtapas;
	}

	public List<Peticao> getPeticoes() {
		return peticoes;
	}

	public void setPeticoes(List<Peticao> peticoes) {
		this.peticoes = peticoes;
	}

	public List<LocalPraticaPedido> getLocaisPraticaPedidos() {
		return locaisPraticaPedidos;
	}

	public void setLocaisPraticaPedidos(
			List<LocalPraticaPedido> locaisPraticaPedidos) {
		this.locaisPraticaPedidos = locaisPraticaPedidos;
	}

	public Set<RotaPedido> getRotaPedidos() {
		return rotaPedidos;
	}

	public void setRotaPedidos(Set<RotaPedido> rotaPedidos) {
		this.rotaPedidos = rotaPedidos;
	}

	public List<EquipamentoPedido> getEquipamentosPedidos() {
		return equipamentosPedidos;
	}

	public void setEquipamentosPedidos(List<EquipamentoPedido> equipamentosPedidos) {
		this.equipamentosPedidos = equipamentosPedidos;
	}

	public List<TipoEmbarcacaoPedido> getTipoEmbarcacaoPedidos() {
		return tipoEmbarcacaoPedidos;
	}

	public void setTipoEmbarcacaoPedidos(
			List<TipoEmbarcacaoPedido> tipoEmbarcacaoPedidos) {
		this.tipoEmbarcacaoPedidos = tipoEmbarcacaoPedidos;
	}

	public List<MeioTransportePedido> getMeioTransportePedido() {
		return meioTransportePedido;
	}

	public void setMeioTransportePedido(
			List<MeioTransportePedido> meioTransportePedido) {
		this.meioTransportePedido = meioTransportePedido;
	}

	public List<ContagemPedido> getContagemPedidos() {
		return contagemPedidos;
	}

	public void setContagemPedidos(List<ContagemPedido> contagemPedidos) {
		this.contagemPedidos = contagemPedidos;
	}

	public List<CursoPedido> getCursosPedidos() {
		return cursosPedidos;
	}

	public void setCursosPedidos(List<CursoPedido> cursosPedidos) {
		this.cursosPedidos = cursosPedidos;
	}

	public List<TaxaPedido> getTaxasPedido() {
		return taxasPedido;
	}

	public void setTaxasPedido(List<TaxaPedido> taxasPedido) {
		this.taxasPedido = taxasPedido;
	}

	public void copyToPedidoResport(PedidoConfigReport pcr, PedidoRequisitoService ps, TaxaPedidoService ts, PedidoEtapaService pes){
		pcr.setCreated(created);
		pcr.setFluxo(fluxo);
		pcr.setDescricao(descricao);
		pcr.setLocaisPraticaPedidos(locaisPraticaPedidos);
		pcr.setTipoPedido(tipoPedido);
		pcr.setAdmar(isAdmar);
		pcr.setActivo(isActivo);
		pcr.setPedidoRequisitos(ps.findByPedido(this));
		pcr.setTaxasPedido(ts.findByPedido(this));
		pcr.setPedidosEtapas(pes.findByPedido(this));
	}
}
