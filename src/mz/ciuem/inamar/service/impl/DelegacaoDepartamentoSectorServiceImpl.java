package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.DelegacaoDepartamentoSectorDao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;
import mz.ciuem.inamar.service.DelegacaoDepartamentoSectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("delegacaoDepartamentoSectorService")
public class DelegacaoDepartamentoSectorServiceImpl extends GenericServiceImpl<DelegacaoDepartamentoSector> implements DelegacaoDepartamentoSectorService{

	@Autowired
	private DelegacaoDepartamentoSectorDao _delDepSectDao;
	
	@Override
	public List<DelegacaoDepartamentoSector> findByDelegacaoDepartamento(
			DelegacaoDepartamento delegacaoDep) {
		return _delDepSectDao.findByDelegacaoDepartamento(delegacaoDep);
	}

	@Override
	public List<DelegacaoDepartamentoSector> findByNomeActivo(String nome,
			boolean activo) {
		return _delDepSectDao.findByNomeActivo(nome, activo);
	}

}
