package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.ExameDao;
import mz.ciuem.inamar.entity.Exame;
import mz.ciuem.inamar.service.ExameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("exameService")
public class ExameServiceImpl extends GenericServiceImpl<Exame> implements ExameService{

	@Autowired
	private ExameDao _exDao;
	
	@Override
	public List<Exame> findBydescricao(String descricao){
		return _exDao.findBydescricao(descricao);
	}
	
	
}
