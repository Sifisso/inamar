package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface LocalPraticaPedidoService extends GenericService<LocalPraticaPedido>{
	
	public List<LocalPraticaPedido> findByPedido(Pedido pedido);


}
