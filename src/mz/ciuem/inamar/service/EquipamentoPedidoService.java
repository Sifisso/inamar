package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.EquipamentoPedido;
import mz.ciuem.inamar.entity.Pedido;

public interface EquipamentoPedidoService extends GenericService<EquipamentoPedido>{

	public List<EquipamentoPedido> findByPedido(Pedido pedido);

}
