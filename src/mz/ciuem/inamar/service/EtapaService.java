package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Etapa;

public interface EtapaService extends GenericService<Etapa>{
	
	public List<Etapa>  findByNomeIsActivo(String nome, boolean isActivo);

}
