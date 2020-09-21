package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Estado;

public interface EstadoService extends GenericService<Estado>{
	
	public List<Estado> findByNomeIsActivo(String nome, boolean isActivo);


}
