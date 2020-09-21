package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Exame;

public interface ExameDao extends GenericDao<Exame>{
	public List<Exame> findBydescricao(String descricao);

}
