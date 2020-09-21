package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.MeioTransporteDao;
import mz.ciuem.inamar.dao.MeioTransportePedidoDao;
import mz.ciuem.inamar.entity.MeioTransportePedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.MeioTransportePedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("meioTransportePedidoService")
public class MeioTransportePedidoServiceImpl extends GenericServiceImpl<MeioTransportePedido> implements MeioTransportePedidoService{

	@Autowired
	private MeioTransportePedidoDao _mtDao;
	@Override
	public List<MeioTransportePedido> findByPedido(Pedido pedido) {
		return _mtDao.findByPedido(pedido);
	}

}
