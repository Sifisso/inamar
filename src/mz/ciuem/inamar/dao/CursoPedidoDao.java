package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.CursoPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface CursoPedidoDao extends GenericDao<CursoPedido>{

	public List<CursoPedido> findByPedido(Pedido pedido);
}
