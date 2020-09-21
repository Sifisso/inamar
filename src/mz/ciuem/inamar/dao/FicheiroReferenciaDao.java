package mz.ciuem.inamar.dao;

import mz.ciuem.inamar.entity.FicheiroReferencia;

public interface FicheiroReferenciaDao extends GenericDao<FicheiroReferencia>{

	
	FicheiroReferencia findNotReadFile();
}
