package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.CursoDao;
import mz.ciuem.inamar.entity.Curso;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cursoService")
public class CursoServiceImpl extends GenericServiceImpl<Curso> implements CursoService{

	@Autowired
	private CursoDao _cDao;
	
	@Override
	public List<Curso> findNotInPedido(Pedido pedido) {
		return _cDao.findNotInPedido(pedido);
	}

}
