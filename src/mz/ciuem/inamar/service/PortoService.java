package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Porto;

public interface PortoService extends GenericService<Porto>{
	
	//public List <Porto> findBydescricaoActivo(String descricao, boolean isActivo);

	public List<Porto> findBydescricaoActivo(String descricao, boolean isActivo);

}
