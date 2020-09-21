package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;

public interface PedidoRequisitoDao extends GenericDao<PedidoRequisito>{
	
	public List<PedidoRequisito> findByPedido(Pedido pedido);
	public List<PedidoRequisito> findByPedidoVisivelParaUtente(Pedido pedido);
	public List<PedidoRequisito> findByPedidoActivoVisivelParaUtente(Pedido pedido);
}
