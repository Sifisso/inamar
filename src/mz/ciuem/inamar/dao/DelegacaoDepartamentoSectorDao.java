package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;

public interface DelegacaoDepartamentoSectorDao extends GenericDao<DelegacaoDepartamentoSector>{
	
	public List<DelegacaoDepartamentoSector> findByDelegacaoDepartamento(DelegacaoDepartamento delegacaoDep);
	
	public List<DelegacaoDepartamentoSector> findByNomeActivo(String nome, boolean activo);

}
