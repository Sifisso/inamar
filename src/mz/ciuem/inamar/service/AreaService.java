package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Area;

public interface AreaService extends GenericService<Area>{
	
	public List<Area> findByNomeIsActivoIsAdmar(String nome, boolean isActivo, boolean isAdmar);


}
