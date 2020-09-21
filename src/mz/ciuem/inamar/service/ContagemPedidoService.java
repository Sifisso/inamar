package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.ContagemPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface ContagemPedidoService extends GenericService<ContagemPedido>{
	public List<ContagemPedido> findByPedido(Pedido pedido);

}
