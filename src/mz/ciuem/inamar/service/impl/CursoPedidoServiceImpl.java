package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.CursoPedidoDao;
import mz.ciuem.inamar.entity.CursoPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.CursoPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cursoPedidoService")
public class CursoPedidoServiceImpl extends GenericServiceImpl<CursoPedido> implements CursoPedidoService{

	@Autowired
	private CursoPedidoDao _cpDao;
	
	@Override
	public List<CursoPedido> findByPedido(Pedido pedido) {
		return _cpDao.findByPedido(pedido);
	}

}
