package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.RotaPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.RotaPedido;
import mz.ciuem.inamar.service.RotaPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rotaPedidoService")
public class RotaPedidoServiceImpl extends GenericServiceImpl<RotaPedido> implements RotaPedidoService{

	@Autowired
	private RotaPedidoDao _rpd;
	@Override
	public List<RotaPedido> findByPedido(Pedido pedido) {
		return _rpd.findByPedido(pedido);
	}

}
