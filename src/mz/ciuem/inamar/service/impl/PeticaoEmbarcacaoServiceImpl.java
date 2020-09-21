package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoEmbarcacaoDao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.service.PeticaoEmbarcacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peticaoEmbarcacaoService")
public class PeticaoEmbarcacaoServiceImpl extends GenericServiceImpl<PeticaoEmbarcacao> implements PeticaoEmbarcacaoService{

	@Autowired
	private PeticaoEmbarcacaoDao _peDao;
	
	@Override
	public List<PeticaoEmbarcacao> findWithEager() {
		return _peDao.findWithEager();
	}

	@Override
	public PeticaoEmbarcacao findOneWithEager(long id) {
		return _peDao.findOneWithEager(id);
	}

}
