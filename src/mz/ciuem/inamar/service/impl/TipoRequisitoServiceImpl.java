package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.TipoRequisitoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoRequisito;
import mz.ciuem.inamar.service.TipoRequisitoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipoRequisitoService")
public class TipoRequisitoServiceImpl extends GenericServiceImpl<TipoRequisito> implements TipoRequisitoService{

	@Autowired
	private TipoRequisitoDao _tipoRDao;
	
	@Override
	public List<TipoRequisito> findByNomeActivo(String nome, boolean isActivo) {
		return _tipoRDao.findByNomeActivo(nome, isActivo);
	}

	@Override
	public List<TipoRequisito> findNotInPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return _tipoRDao.findNotInPedido(pedido);
	}

}
