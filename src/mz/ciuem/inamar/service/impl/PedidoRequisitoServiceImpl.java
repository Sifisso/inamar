package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PedidoRequisitoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.service.PedidoRequisitoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pedidoRequisitoService")
public class PedidoRequisitoServiceImpl extends GenericServiceImpl<PedidoRequisito> implements PedidoRequisitoService{

	@Autowired
	private PedidoRequisitoDao _prdao;
	
	@Override
	public List<PedidoRequisito> findByPedido(Pedido pedido) {
		return _prdao.findByPedido(pedido);
	}

	@Override
	public List<PedidoRequisito> findByPedidoVisivelParaUtente(Pedido pedido) {
		return _prdao.findByPedidoVisivelParaUtente(pedido);
	}

	@Override
	public List<PedidoRequisito> findByPedidoActivoVisivelParaUtente(
			Pedido pedido) {
		return _prdao.findByPedidoActivoVisivelParaUtente(pedido);
	}

}
