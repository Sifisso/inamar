package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.Pedido;

public interface LocalPraticaService extends GenericService<LocalPratica>{
	
	public List<LocalPratica> findNotInPedido(Pedido pedido);


}
