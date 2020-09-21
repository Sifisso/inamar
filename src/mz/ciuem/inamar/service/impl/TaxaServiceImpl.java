package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TaxaDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.service.TaxaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("taxaService")
public class TaxaServiceImpl extends GenericServiceImpl<Taxa> implements TaxaService{

	@Autowired
	private TaxaDao _taxaDao;
	@Override
	public List<Taxa> findByNomeActivo(String nome, boolean isActivo) {
		return _taxaDao.findByNomeActivo(nome, isActivo);
	}
	@Override
	public List<Taxa> finBySubArea(SubArea subArea) {
		return _taxaDao.finBySubArea(subArea);
	}
	@Override
	public List<Taxa> findNotInPedido(Pedido pedido) {
		return _taxaDao.findNotInPedido(pedido);
	}
	@Override
	public List<Taxa> findNotInPedidoInSubSrea(SubArea _subArea, Pedido _pedido) {
		return _taxaDao.findNotInPedidoInSubSrea(_subArea, _pedido);
	}
	
	

}
