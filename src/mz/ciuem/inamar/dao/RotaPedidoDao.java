package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.RotaPedido;

public interface RotaPedidoDao extends GenericDao<RotaPedido>{
	
	public List<RotaPedido> findByPedido (Pedido pedido);

}
