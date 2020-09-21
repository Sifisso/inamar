package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;

public interface PeticaoPedidoEtapaInstrumentoLegalService extends GenericService<PeticaoPedidoEtapaInstrumentoLegal>{

	public List<PeticaoPedidoEtapaInstrumentoLegal> findByPeticao(Peticao peticao);
}
