package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Pais;

public interface EmbarcacaoDao extends GenericDao<Embarcacao> {
	
	public List<Embarcacao> findByNomeActivo (String nome, boolean isActivo);

	List<Object[]> getNaviosByDelegacaoDeRegisto();

}
