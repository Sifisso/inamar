package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;

public interface PeticaoTarefasNaEtapaDao extends GenericDao<PeticaoTarefasNaEtapa>{
	
	public List<PeticaoTarefasNaEtapa> findByPeticaoEtapa(PeticaoEtapa peticaoEtapa);

}
