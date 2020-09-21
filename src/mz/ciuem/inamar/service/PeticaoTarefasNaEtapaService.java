package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;

public interface PeticaoTarefasNaEtapaService extends GenericService<PeticaoTarefasNaEtapa>{
	
	public List<PeticaoTarefasNaEtapa> findByPeticaoEtapa(
			PeticaoEtapa peticaoEtapa);

}
