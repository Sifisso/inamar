package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.ContagemPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface ContagemPedidoDao extends GenericDao<ContagemPedido>{

	public List<ContagemPedido> findByPedido(Pedido pedido);
}
