package mz.ciuem.inamar.dao;

import java.util.List;


import mz.ciuem.inamar.entity.Porto;

public interface PortoDao extends GenericDao<Porto>{
	public List<Porto> findBydescricaoActivo (String descricao, boolean isActivo);

}
