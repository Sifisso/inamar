package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoEmbarcacao;

public interface PeticaoEmbarcacaoService extends GenericService<PeticaoEmbarcacao>{
	
	public List<PeticaoEmbarcacao> findWithEager();
	public PeticaoEmbarcacao findOneWithEager(long id);

}
