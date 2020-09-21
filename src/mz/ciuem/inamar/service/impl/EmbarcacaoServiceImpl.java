package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.AcontecimentoDao;
import mz.ciuem.inamar.dao.EmbarcacaoDao;
import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.service.EmbarcacaoService;

@Service("embarcacaoService")
public class EmbarcacaoServiceImpl extends GenericServiceImpl<Embarcacao> implements EmbarcacaoService{

	@Autowired
	private EmbarcacaoDao _embDao;

	@Override
	public List <Embarcacao> findByNomeActivo(String nome, boolean isActivo){
		return _embDao.findByNomeActivo(nome, isActivo);
	}
	
	@Override
	public List<Object[]> getNaviosByDelegacaoDeRegisto() {
		// TODO Auto-generated method stub
		return _embDao.getNaviosByDelegacaoDeRegisto();
	}
}
