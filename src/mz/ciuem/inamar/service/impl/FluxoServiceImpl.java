package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.FluxoDao;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.service.FluxoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fluxoService")
public class FluxoServiceImpl extends GenericServiceImpl<Fluxo> implements FluxoService{

	@Autowired
	private FluxoDao _fluxoDao;
	
	@Override
	public List<Fluxo> findByNomeIsActivo(String nome, boolean isActivo) {
		return _fluxoDao.findByNomeIsActivo(nome, isActivo);
	}

	@Override
	public List<Fluxo> finByNotInPedido(TipoPedido tipoPedido) {
		return _fluxoDao.finByNotInPedido(tipoPedido);
	}

}
