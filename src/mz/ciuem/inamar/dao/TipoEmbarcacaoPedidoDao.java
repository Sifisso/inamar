package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacaoPedido;

public interface TipoEmbarcacaoPedidoDao extends GenericDao<TipoEmbarcacaoPedido>{
	
	public List<TipoEmbarcacaoPedido> findByPedido(Pedido pedido);

}
