package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoRequisito;

public interface TipoRequisitoDao extends GenericDao<TipoRequisito>{
	
	public List<TipoRequisito> findByNomeActivo(String nome, boolean isActivo);
	public List<TipoRequisito> findNotInPedido(Pedido pedido);

}
