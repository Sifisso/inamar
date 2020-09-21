package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.ContagemPedidoDao;
import mz.ciuem.inamar.entity.ContagemPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.ContagemPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contagemPedidoService")
public class ContagemPedidoServiceImpl extends GenericServiceImpl<ContagemPedido> implements ContagemPedidoService{

	@Autowired
	private ContagemPedidoDao _cDao;
	
	@Override
	public List<ContagemPedido> findByPedido(Pedido pedido) {
		return _cDao.findByPedido(pedido);
	}

}
