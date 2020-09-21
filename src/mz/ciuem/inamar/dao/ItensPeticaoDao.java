package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Peticao;

public interface ItensPeticaoDao extends GenericDao<ItensPeticao>{
	
	public List<ItensPeticao> findByPeticaoID(Peticao peticao);
	
	
}
