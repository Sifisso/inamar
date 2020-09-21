package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pedidoService")
public class PedidoServiceImpl extends GenericServiceImpl<Pedido> implements PedidoService{

	@Autowired
	private PedidoDao _pDao;
	
	@Override
	public List<Pedido> findByTipoPedido(TipoPedido tipoPedido) {
		// TODO Auto-generated method stub
		return _pDao.findByTipoPedido(tipoPedido);
	}

	@Override
	public List<Pedido> findByNomenNomeNomeFluxoIsActivo(String nome,
			String nomeFluxo, boolean isActivo, TipoPedido tipoPedido) {
		// TODO Auto-generated method stub
		return _pDao.findByNomenNomeNomeFluxoIsActivo(nome, nomeFluxo, isActivo, tipoPedido);
	}

}
