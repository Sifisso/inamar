package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PedidoEtapaDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.service.PedidoEtapaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pedidoEtapaService")
public class PedidoEtapaServiceImpl extends GenericServiceImpl<PedidoEtapa> implements PedidoEtapaService{

    @Autowired
    private PedidoEtapaDao _peDao;
    
	@Override
	public List<PedidoEtapa> findByPedido(Pedido pedido) {
		return _peDao.findByPedido(pedido);
	}

}
