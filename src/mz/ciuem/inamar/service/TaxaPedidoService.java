package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TaxaPedido;

public interface TaxaPedidoService extends GenericService<TaxaPedido>{
	
	public List<TaxaPedido> findByPedido(Pedido pedido);
	
	public List<TaxaPedido> findByPedidoTaxaPedido(Pedido pedido);

	public List<TaxaPedido> findTaxaPedidoByPedido(Pedido pedido);

}
