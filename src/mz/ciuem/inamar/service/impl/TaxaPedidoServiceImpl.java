package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TaxaPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.service.TaxaPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taxaPedidoService")
public class TaxaPedidoServiceImpl extends GenericServiceImpl<TaxaPedido> implements TaxaPedidoService{

	@Autowired
	private TaxaPedidoDao _tpDao;
	
	@Override
	public List<TaxaPedido> findByPedido(Pedido pedido) {
		return _tpDao.findByPedido(pedido);
	}

	@Override
	public List<TaxaPedido> findByPedidoTaxaPedido(Pedido pedido) {
		
		return _tpDao.findByPedidoTaxaPedido(pedido);
	}
	
	@Override
	public List<TaxaPedido> findTaxaPedidoByPedido(Pedido pedido) {
		
		return _tpDao.findTaxaPedidoByPedido(pedido);
	}

}
