package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;

public interface PedidoEtapaDao extends GenericDao<PedidoEtapa>{
	
   public List<PedidoEtapa>  findByPedido(Pedido pedido);

}
