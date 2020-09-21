package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;

public interface PeticaoPedidoEtapaInstrumentoLegalDao extends GenericDao<PeticaoPedidoEtapaInstrumentoLegal>{
	
	public List<PeticaoPedidoEtapaInstrumentoLegal> findByPeticao(Peticao peticao);

}
