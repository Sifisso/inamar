package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;

public interface InstrumentoLegalService extends GenericService<InstrumentoLegal>{
	
	public List<InstrumentoLegal> findByNomeActivo(String nome, boolean isActivo);

	public List<InstrumentoLegal> findNotInPedido (Pedido pedido);
}
