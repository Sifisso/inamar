package mz.ciuem.inamar.service;

import mz.ciuem.inamar.entity.Acontecimento;

import java.util.List;
public interface AcontecimentoService extends GenericService<Acontecimento>{
	
	
	
	public List <Acontecimento> findBydescricaoActivo(String descricao, boolean isActivo);
	
}
