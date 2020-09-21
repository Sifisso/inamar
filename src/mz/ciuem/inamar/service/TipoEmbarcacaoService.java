package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacao;

public interface TipoEmbarcacaoService extends GenericService<TipoEmbarcacao>{
	
	public List<TipoEmbarcacao> findNotInPedido(Pedido pedido);


}
