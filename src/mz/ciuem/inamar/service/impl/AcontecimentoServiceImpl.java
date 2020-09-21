package mz.ciuem.inamar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.AcontecimentoDao;
import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.service.AcontecimentoService;

import java.util.List;
@Service("acontecimentoService")
public class AcontecimentoServiceImpl extends GenericServiceImpl<Acontecimento> implements AcontecimentoService{

	@Autowired
	private AcontecimentoDao _acoDao;
	
	@Override
	public List <Acontecimento> findBydescricaoActivo(String descricao, boolean isActivo){
		return _acoDao.findBydescricaoActivo(descricao, isActivo);
	}
}




