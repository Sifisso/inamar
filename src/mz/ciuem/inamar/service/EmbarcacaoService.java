package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Pais;

public interface EmbarcacaoService extends GenericService<Embarcacao>{
	public List <Embarcacao> findByNomeActivo(String nome, boolean isActivo);
	List<Object[]> getNaviosByDelegacaoDeRegisto();

}
