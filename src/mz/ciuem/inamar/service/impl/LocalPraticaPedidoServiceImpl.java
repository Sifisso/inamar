package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.LocalPraticaDao;
import mz.ciuem.inamar.dao.LocalPraticaPedidoDao;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("localPraticaPedidoService")
public class LocalPraticaPedidoServiceImpl extends GenericServiceImpl<LocalPraticaPedido> implements LocalPraticaPedidoService{

	@Autowired
	private LocalPraticaPedidoDao _lpDao;
	
	@Override
	public List<LocalPraticaPedido> findByPedido(Pedido pedido) {
		return _lpDao.findByPedido(pedido);
	}

}
