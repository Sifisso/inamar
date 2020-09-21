package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.RotaDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Rota;
import mz.ciuem.inamar.service.RotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rotaService")
public class RotaServiceImpl extends GenericServiceImpl<Rota> implements RotaService{

	@Autowired
	private RotaDao _rDao;
	
	@Override
	public List<Rota> findNotInPedido(Pedido pedido) {
		return _rDao.findNotInPedido(pedido);
	}

}
