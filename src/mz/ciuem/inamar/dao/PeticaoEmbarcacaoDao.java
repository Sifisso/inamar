package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoEmbarcacao;

public interface PeticaoEmbarcacaoDao extends GenericDao<PeticaoEmbarcacao>{
	
	public List<PeticaoEmbarcacao> findWithEager();
	public PeticaoEmbarcacao findOneWithEager(long id);

}
