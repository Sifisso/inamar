package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Exame;

public interface ExameService extends GenericService<Exame>{

	List<Exame> findBydescricao(String descricao);

}
