package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.RotaPedido;

public interface RotaPedidoService extends GenericService<RotaPedido>{
	
	public List<RotaPedido> findByPedido (Pedido pedido);


}
