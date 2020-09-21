package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.MeioTransportePedido;
import mz.ciuem.inamar.entity.Pedido;

public interface MeioTransportePedidoDao extends GenericDao<MeioTransportePedido>{
	
	public List<MeioTransportePedido> findByPedido(Pedido pedido);

}
