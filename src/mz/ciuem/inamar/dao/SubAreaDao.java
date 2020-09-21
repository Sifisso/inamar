package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.SubArea;

public interface SubAreaDao extends GenericDao<SubArea>{
	
	public List<SubArea> findByNomeActivo(String nome, boolean isActivo);
	public List<SubArea> findByArea(Area area);

}
