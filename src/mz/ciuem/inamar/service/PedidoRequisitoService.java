package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;

public interface PedidoRequisitoService extends GenericService<PedidoRequisito>{
	
	public List<PedidoRequisito> findByPedido(Pedido pedido);
	public List<PedidoRequisito> findByPedidoVisivelParaUtente(Pedido pedido);
	public List<PedidoRequisito> findByPedidoActivoVisivelParaUtente(Pedido pedido);
}
