package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;

public interface AreaDao extends GenericDao<Area>{
	
	public List<Area> findByNomeIsActivoIsAdmar(String nome, boolean isActivo, boolean isAdmar);

}
