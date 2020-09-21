package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Etapa;

public interface EtapaDao extends GenericDao<Etapa>{
	
	public List<Etapa>  findByNomeIsActivo(String nome, boolean isActivo);

}
