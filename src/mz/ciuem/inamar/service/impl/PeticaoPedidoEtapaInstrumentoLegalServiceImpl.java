package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoPedidoEtapaInstrumentoLegalDao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;
import mz.ciuem.inamar.service.PeticaoPedidoEtapaInstrumentoLegalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoPedidoEtapaInstrumentoLegalService")
public class PeticaoPedidoEtapaInstrumentoLegalServiceImpl extends GenericServiceImpl<PeticaoPedidoEtapaInstrumentoLegal>
implements PeticaoPedidoEtapaInstrumentoLegalService{
	
	@Autowired
	private PeticaoPedidoEtapaInstrumentoLegalDao _pDao;

	@Override
	public List<PeticaoPedidoEtapaInstrumentoLegal> findByPeticao(
			Peticao peticao) {
		return _pDao.findByPeticao(peticao);
	}

}
