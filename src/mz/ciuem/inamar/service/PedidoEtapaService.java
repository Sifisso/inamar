package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;

public interface PedidoEtapaService extends GenericService<PedidoEtapa>{
	
	   public List<PedidoEtapa>  findByPedido(Pedido pedido);

}
