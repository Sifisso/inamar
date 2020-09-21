package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacao;

public interface TipoEmbarcacaoDao extends GenericDao<TipoEmbarcacao>{

	public List<TipoEmbarcacao> findNotInPedido(Pedido pedido);
	
}
