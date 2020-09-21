package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.EntradaDao;
import mz.ciuem.inamar.entity.Entrada;
import mz.ciuem.inamar.service.EntradaService;

@Service("entradaService")
public class EntradaServiceImpl extends GenericServiceImpl<Entrada> implements EntradaService{
	
	@Autowired
	private EntradaDao _entDao;
	
	@Override
	public List <Entrada> findByNome(String embarcacao){
		return _entDao.findByNome(embarcacao);
	}
	
	@Override
	public List <Entrada> findByNomeEmb(String embarcacao){
		return _entDao.findByNome(embarcacao);
	}
	
	
	

}
