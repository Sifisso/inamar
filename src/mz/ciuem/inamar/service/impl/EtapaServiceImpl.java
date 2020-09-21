package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.EtapaDao;
import mz.ciuem.inamar.entity.Etapa;
import mz.ciuem.inamar.service.EtapaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("etapaService")
public class EtapaServiceImpl extends GenericServiceImpl<Etapa> implements  EtapaService{

	@Autowired
	private EtapaDao _etDao;
	
	@Override
	public List<Etapa> findByNomeIsActivo(String nome, boolean isActivo) {
		return _etDao.findByNomeIsActivo(nome, isActivo);
	}

}
