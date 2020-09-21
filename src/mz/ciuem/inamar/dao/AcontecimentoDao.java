package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Acontecimento;

public interface AcontecimentoDao extends GenericDao<Acontecimento>{
	public List<Acontecimento> findBydescricaoActivo (String descricao, boolean isActivo);
}
