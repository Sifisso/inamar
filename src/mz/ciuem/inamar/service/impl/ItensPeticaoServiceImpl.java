package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.ItensPeticaoDao;
import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.service.ItensPeticaoService;

@Service("itensPeticaoService")
public class ItensPeticaoServiceImpl extends GenericServiceImpl<ItensPeticao> implements ItensPeticaoService{

	@Autowired
	ItensPeticaoDao _itDao;

	@Override
	public List<ItensPeticao> findByPeticaoID(Peticao peticao) {
		// TODO Auto-generated method stub
		return _itDao.findByPeticaoID(peticao);
		
	}
	
	

	
	
}
