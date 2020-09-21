package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.CategoriaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoriaService")
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria> implements CategoriaService{

	@Autowired
	private CategoriaDao _catDao;
	
	@Override
	public List<Categoria> findByNomeActivo(String nome, boolean isActivo) {
		return _catDao.findByNomeActivo(nome, isActivo);
	}

	@Override
	public List<Categoria> findByNotInPedido(Area area) {
		return _catDao.findByNotInPedido(area);
	}

}
