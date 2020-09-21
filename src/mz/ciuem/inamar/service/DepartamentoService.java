package mz.ciuem.inamar.service;

import java.util.Collection;
import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Departamento;

public interface DepartamentoService extends GenericService<Departamento>{
	
	public List<Departamento> findByNomeIsAdmar(String nome, boolean isAdmar);
	
	public Collection<? extends Departamento> findByDelegacao(Delegacao delegacao, boolean isAdmar);

}
