package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TipoEmbarcacaoPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacaoPedido;
import mz.ciuem.inamar.service.TipoEmbarcacaoPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipoEmbarcacaoPedidoService")
public class TipoEmbarcacaoPedidoServiceImpl extends GenericServiceImpl<TipoEmbarcacaoPedido> implements TipoEmbarcacaoPedidoService{

	@Autowired
	private TipoEmbarcacaoPedidoDao _teDao;

	@Override
	public List<TipoEmbarcacaoPedido> findByPedido(Pedido pedido) {
		return _teDao.findByPedido(pedido);
	}
	

}
