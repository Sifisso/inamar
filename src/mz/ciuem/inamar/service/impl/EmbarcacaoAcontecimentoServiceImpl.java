package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.EmbarcacaoAcontecimentoDao;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.EmbarcacaoAcontecimento;
import mz.ciuem.inamar.service.EmbarcacaoAcontecimentoService;

@Service("embarcacaoAcontecimentoService")
public class EmbarcacaoAcontecimentoServiceImpl extends GenericServiceImpl<EmbarcacaoAcontecimento> implements EmbarcacaoAcontecimentoService{
	@Autowired
	private EmbarcacaoAcontecimentoDao _embAnctDao;
	
	@Override
	public List<EmbarcacaoAcontecimento> findByNome(String embarcacao){
		return _embAnctDao.findByNome(embarcacao);
	}
}
