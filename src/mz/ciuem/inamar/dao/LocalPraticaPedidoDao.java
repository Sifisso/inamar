package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface LocalPraticaPedidoDao extends GenericDao<LocalPraticaPedido>{
	
	public List<LocalPraticaPedido> findByPedido(Pedido pedido);

}
