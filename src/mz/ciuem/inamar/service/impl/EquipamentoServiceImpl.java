package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.EquipamentoDao;
import mz.ciuem.inamar.entity.Equipamento;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.EquipamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("equipamentoService")
public class EquipamentoServiceImpl extends GenericServiceImpl<Equipamento> implements EquipamentoService{

	@Autowired
	private EquipamentoDao _eDao;
	
	@Override
	public List<Equipamento> findNotInPedido(Pedido pedido) {
		return _eDao.findNotInPedido(pedido);
	}

}
