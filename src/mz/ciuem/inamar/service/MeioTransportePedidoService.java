package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.MeioTransportePedido;
import mz.ciuem.inamar.entity.Pedido;

public interface MeioTransportePedidoService extends GenericService<MeioTransportePedido>{
	
	public List<MeioTransportePedido> findByPedido(Pedido pedido);

}
