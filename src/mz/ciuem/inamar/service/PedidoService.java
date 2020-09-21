package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoPedido;

public interface PedidoService extends GenericService<Pedido>{
	
	public List<Pedido> findByTipoPedido (TipoPedido tipoPedido);
	public List<Pedido> findByNomenNomeNomeFluxoIsActivo(String nome, String nomeFluxo, boolean isActivo, TipoPedido tipoPedido);

}
