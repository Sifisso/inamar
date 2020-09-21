package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TipoPedidoDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.service.TipoPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipoPedidoService")
public class TipoPedidoServiceImpl extends GenericServiceImpl<TipoPedido> implements TipoPedidoService{

	@Autowired
	private TipoPedidoDao _tdao;
	
	@Override
	public List<TipoPedido> findByArea(Area area) {
		return _tdao.findByArea(area);
	}

	@Override
	public List<TipoPedido> findByNomeCategoriaAreaIsActivo(String nome, Area area, boolean isActivo) {
		return _tdao.findByNomeCategoriaAreaIsActivo(nome, area, isActivo);
	}

}
