package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Estado;

public interface EstadoDao extends GenericDao<Estado>{
	
	public List<Estado> findByNomeIsActivo(String nome, boolean isActivo);

}
