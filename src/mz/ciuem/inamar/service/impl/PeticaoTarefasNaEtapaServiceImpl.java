package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoTarefasNaEtapaDao;
import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;
import mz.ciuem.inamar.service.PeticaoTarefasNaEtapaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoTarefasNaEtapaService")
public class PeticaoTarefasNaEtapaServiceImpl extends GenericServiceImpl<PeticaoTarefasNaEtapa> implements PeticaoTarefasNaEtapaService{

	@Autowired
	private PeticaoTarefasNaEtapaDao _pDao;
	
	@Override
	public List<PeticaoTarefasNaEtapa> findByPeticaoEtapa(
			PeticaoEtapa peticaoEtapa) {
		// TODO Auto-generated method stub
		return _pDao.findByPeticaoEtapa(peticaoEtapa);
	}

}
