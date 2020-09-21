package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoRequisito;

public interface TipoRequisitoService extends GenericService<TipoRequisito>{
	
	public List<TipoRequisito> findByNomeActivo(String nome, boolean isActivo);
	public List<TipoRequisito> findNotInPedido(Pedido pedido);

}
