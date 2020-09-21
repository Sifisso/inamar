package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.SubArea;

public interface SubAreaService extends GenericService<SubArea>{
	
	public List<SubArea> findByNomeActivo(String nome, boolean isActivo);
	public List<SubArea> findByArea(Area area);

}
