package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;

public interface DelegacaoDepartamentoSectorService extends GenericService<DelegacaoDepartamentoSector>{
	
	public List<DelegacaoDepartamentoSector> findByDelegacaoDepartamento(DelegacaoDepartamento delegacaoDep);
	
	public List<DelegacaoDepartamentoSector> findByNomeActivo(String nome, boolean activo);
}
