package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.EstadoDao;
import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.service.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("estadoService")
public class EstadoServiceImpl extends GenericServiceImpl<Estado> implements EstadoService{

	@Autowired
	private EstadoDao _estDao;
	
	@Override
	public List<Estado> findByNomeIsActivo(String nome, boolean isActivo) {
		return _estDao.findByNomeIsActivo(nome, isActivo);
	}

}
