package mz.ciuem.inamar.dao;

import java.util.Collection;
import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.entity.Sectorr;

public interface SectorrDao extends GenericDao<Sectorr>{
	
	public List<Sectorr> findByNomeIsAdmar(String nome, boolean isAdmar);
	
	public Collection<? extends Sectorr> findByDelegacaoDep(DelegacaoDepartamento delegacaoDepartamento, boolean isAdmar);

}
