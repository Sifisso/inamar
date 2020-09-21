package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.ContagemDao;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.ContagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("contagemService")
public class ContagemServiceImpl extends GenericServiceImpl<Contagem> implements ContagemService{

	@Autowired
	private ContagemDao _cDao;
	
	@Override
	public List<Contagem> findNotInPedido(Pedido pedido) {
		return _cDao.findNotInPedido(pedido);
	}

}
