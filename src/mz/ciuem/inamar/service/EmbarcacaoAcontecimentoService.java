package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.EmbarcacaoAcontecimento;
import mz.ciuem.inamar.entity.Embarcacao;

public interface EmbarcacaoAcontecimentoService extends GenericService<EmbarcacaoAcontecimento>{
	public List<EmbarcacaoAcontecimento> findByNome (String embarcacao);
	
}
