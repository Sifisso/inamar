package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;

public interface DelegacaoDepartamentoDao extends GenericDao<DelegacaoDepartamento>{
	
	public List<DelegacaoDepartamento> findByDelegacao(Delegacao delegacao);
	
	public List<DelegacaoDepartamento> findByNomeActivo(String nome, boolean activo);
	
}
