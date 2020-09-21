package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;

public interface DelegacaoDepartamentoService extends GenericService<DelegacaoDepartamento>{
	
	public List<DelegacaoDepartamento> findByDelegacao(Delegacao delegacao);
	
	public List<DelegacaoDepartamento> findByNomeActivo(String nome, boolean activo);


}
