package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.MeioTransporteDao;
import mz.ciuem.inamar.entity.MeioTransporte;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.MeioTransporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("meioTransporteService")
public class MeioTransporteServiceImpl extends GenericServiceImpl<MeioTransporte> implements MeioTransporteService{

	@Autowired
	private MeioTransporteDao _mtDao;
	
	@Override
	public List<MeioTransporte> findNotInPedido(Pedido pedido) {
		return _mtDao.findNotInPedido(pedido);
	}

}
