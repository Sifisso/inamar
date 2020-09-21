package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TaxaPedido;

public interface TaxaPedidoDao extends GenericDao<TaxaPedido>{

	public List<TaxaPedido> findByPedido(Pedido pedido);
	
	//valores
    public List<TaxaPedido> findByPedidoTaxaPedido(Pedido pedido);
}
