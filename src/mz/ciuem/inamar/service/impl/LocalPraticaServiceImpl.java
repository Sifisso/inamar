package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.LocalPraticaDao;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.LocalPraticaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("localPraticaService")
public class LocalPraticaServiceImpl extends GenericServiceImpl<LocalPratica> implements LocalPraticaService{

	@Autowired
	private LocalPraticaDao _lpDao;
	
	@Override
	public List<LocalPratica> findNotInPedido(Pedido pedido) {
		return _lpDao.findNotInPedido(pedido);
	}

}
