package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;

public interface InstrumentoLegalDao extends GenericDao<InstrumentoLegal>{
	
	public List<InstrumentoLegal> findByNomeActivo(String nome, boolean isActivo);
	
	public List<InstrumentoLegal> findNotInPedido (Pedido pedido);

}
