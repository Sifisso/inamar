package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.EmbarcacaoAcontecimento;

public interface EmbarcacaoAcontecimentoDao extends GenericDao<EmbarcacaoAcontecimento>{
	public List<EmbarcacaoAcontecimento> findByNome(String embarcacao);

}
