package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoCategoriaMaritimoDao;
import mz.ciuem.inamar.entity.PeticaoCategoriaMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.service.PeticaoCategoriaMaritimoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoCategoriaMaritimoService")
public class PeticaoCategoriaMaritimoServiceImpl extends GenericServiceImpl<PeticaoCategoriaMaritimo> implements PeticaoCategoriaMaritimoService{

	@Autowired
	private PeticaoCategoriaMaritimoDao _pDao;
	
	@Override
	public PeticaoCategoriaMaritimo findByPeticaoMaritimo(
			PeticaoMaritimo peticaoMaritimo) {
		return _pDao.findByPeticaoMaritimo(peticaoMaritimo);
	}

}
