package mz.ciuem.inamar.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

public class PedidoConfigReport {

	private static final long serialVersionUID = 1L;
	
	private Date created;
	
	private String codigo;
	
	private String descricao;
	
	private boolean isAdmar;
	
	private boolean isActivo;
	
	private boolean isMostraFactura;
	
	private List<TipoServico> tipoServicos;
	
	private List<TarefaNaEtapa> tarefasNaEtapa;
	
	private TipoPedido tipoPedido;
	
	private Fluxo fluxo;
	
	private List<PedidoEtapa> pedidosEtapas;
	
	private List<Peticao> peticoes;
	
	private List<LocalPraticaPedido> locaisPraticaPedidos;
	
	private Set<RotaPedido> rotaPedidos;
	
	private List<EquipamentoPedido> equipamentosPedidos;
	
	private List<TipoEmbarcacaoPedido> tipoEmbarcacaoPedidos;
	
	private List<MeioTransportePedido> meioTransportePedido;
	
	private List<ContagemPedido> contagemPedidos;
	
	private List<CursoPedido> cursosPedidos;
	
	private List<TaxaPedido> taxasPedido;
	
	private List<PedidoRequisito> pedidoRequisitos;
	
	private List<PeticaoMaritimo> peticaoMaritimo;
	
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	

}
