package mz.ciuem.inamar.service;

import java.util.Collection;
import java.util.List;

import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Sectorr;

public interface SectorrService extends GenericService<Sectorr>{
	
	public List<Sectorr> findByNomeIsAdmar(String nome, boolean isAdmar);
	
	public Collection<? extends Sectorr> findByDelegacaoDep(DelegacaoDepartamento delegacaoDepartamento, boolean isAdmar);

}
