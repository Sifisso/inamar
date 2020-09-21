package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoPedido;

public interface PedidoDao extends GenericDao<Pedido>{
	
	public List<Pedido> findByTipoPedido (TipoPedido tipoPedido);
	public List<Pedido> findByNomenNomeNomeFluxoIsActivo(String nome, String nomeFluxo, boolean isActivo, TipoPedido tipoPedido);

}
