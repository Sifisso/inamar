package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Fluxo;

public interface EtapaFluxoDao extends GenericDao<EtapaFluxo>{
	
	public List<EtapaFluxo> findByFluxo (Fluxo fluxo);
	
	public List<EtapaFluxo> findByNomeActivo (String nome, boolean isActivo, Fluxo fluxo);

}
