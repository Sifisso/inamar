package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.CategoriaMaritimoDao;
import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.service.CategoriaMaritimoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoriaMaritimoService")
public class CategoriaMaritimoServiceImpl extends GenericServiceImpl<CategoriaMaritimo> implements CategoriaMaritimoService{

	@Autowired
	private CategoriaMaritimoDao _catMar;
	
	@Override
	public List<CategoriaMaritimo> findByClasseMaritimo(ClasseMaritimo classeMaritimo) {
		return _catMar.findByClasseMaritimo(classeMaritimo);
	}

}
