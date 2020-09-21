package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.CursoPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface CursoPedidoService extends GenericService<CursoPedido>{

	public List<CursoPedido> findByPedido(Pedido pedido);
	
}
