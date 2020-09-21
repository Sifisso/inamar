package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacaoPedido;

public interface TipoEmbarcacaoPedidoService extends GenericService<TipoEmbarcacaoPedido>{
	
	public List<TipoEmbarcacaoPedido> findByPedido(Pedido pedido);

}
