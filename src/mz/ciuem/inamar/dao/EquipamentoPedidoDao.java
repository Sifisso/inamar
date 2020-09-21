package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.EquipamentoPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface EquipamentoPedidoDao extends GenericDao<EquipamentoPedido>{

	public List<EquipamentoPedido> findByPedido(Pedido pedido);
}
