package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.DelegacaoDepartamentoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.service.DelegacaoDepartamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("delegacaoDepartamentoService")
public class DelegacaoDepartamentoServiceImpl extends GenericServiceImpl<DelegacaoDepartamento> implements DelegacaoDepartamentoService{

	@Autowired
	private DelegacaoDepartamentoDao _delDepDao;
	
	@Override
	public List<DelegacaoDepartamento> findByDelegacao(Delegacao delegacao) {
		return _delDepDao.findByDelegacao(delegacao);
	}

	@Override
	public List<DelegacaoDepartamento> findByNomeActivo(String nome,
			boolean activo) {
		return _delDepDao.findByNomeActivo(nome, activo);
	}

}
