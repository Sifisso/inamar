package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Fluxo;

public interface EtapaFluxoService extends GenericService<EtapaFluxo>{

	public List<EtapaFluxo> findByFluxo (Fluxo fluxo);
	
	public List<EtapaFluxo> findByNomeActivo (String nome, boolean isActivo, Fluxo fluxo);
}
