package mz.ciuem.inamar.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.DepartamentoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.service.DepartamentoService;

@Service("departamentoService")
public class DepartamentoServiceImpl extends GenericServiceImpl<Departamento> implements DepartamentoService{

	@Autowired
	private DepartamentoDao _depDao;
	
	
	@Override
	public List<Departamento> findByNomeIsAdmar(String nome, boolean isAdmar) {
		return _depDao.findByNomeIsAdmar(nome, isAdmar);
	}


	@Override
	public Collection<? extends Departamento> findByDelegacao(
			Delegacao delegacao, boolean isAdmar) {
		return _depDao.findByDelegacao(delegacao, isAdmar);
	}


}
