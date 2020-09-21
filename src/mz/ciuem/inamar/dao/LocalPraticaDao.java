package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.Pedido;

public interface LocalPraticaDao extends GenericDao<LocalPratica>{
	
	public List<LocalPratica> findNotInPedido(Pedido pedido);

}
