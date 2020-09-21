package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.EquipamentoDao;
import mz.ciuem.inamar.dao.EquipamentoPedidoDao;
import mz.ciuem.inamar.entity.EquipamentoPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.EquipamentoPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("equipamentoPedidoService")
public class EquipamentoPedidoServiceImpl extends GenericServiceImpl<EquipamentoPedido> implements EquipamentoPedidoService{

	@Autowired
	private EquipamentoPedidoDao _eqDao;
	
	@Override
	public List<EquipamentoPedido> findByPedido(Pedido pedido) {
		return _eqDao.findByPedido(pedido);
	}

}
