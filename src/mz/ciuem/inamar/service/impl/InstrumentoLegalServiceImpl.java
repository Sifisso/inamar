package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.InstrumentoLegalDao;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.InstrumentoLegalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("instrumentoLegalService")
public class InstrumentoLegalServiceImpl extends GenericServiceImpl<InstrumentoLegal> implements InstrumentoLegalService{

	@Autowired
	private InstrumentoLegalDao _insDao;
	
	@Override
	public List<InstrumentoLegal> findByNomeActivo(String nome, boolean isActivo) {
		return _insDao.findByNomeActivo(nome, isActivo);
	}

	@Override
	public List<InstrumentoLegal> findNotInPedido(Pedido pedido) {
		return _insDao.findNotInPedido(pedido);
	}

}
