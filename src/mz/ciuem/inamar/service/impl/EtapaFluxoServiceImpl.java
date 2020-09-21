package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.EtapaFluxoDao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.service.EtapaFluxoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("etapaFluxoService")
public class EtapaFluxoServiceImpl extends GenericServiceImpl<EtapaFluxo> implements EtapaFluxoService{

	@Autowired
	private EtapaFluxoDao _eDao;
	
	@Override
	public List<EtapaFluxo> findByFluxo(Fluxo fluxo) {
		return _eDao.findByFluxo(fluxo);
	}

	@Override
	public List<EtapaFluxo> findByNomeActivo(String nome, boolean isActivo, Fluxo fluxo) {
		return _eDao.findByNomeActivo(nome, isActivo, fluxo);
	}

}
