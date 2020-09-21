package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Provincia;

public interface DelegacaoDao extends GenericDao<Delegacao>{

	public List<Delegacao> findByNomeProvincia(String nome, Provincia provincia, Instituicao instituicao);
	
	public List<Delegacao> findByInstituicao(Instituicao instituicao);
	
	public List<Delegacao> findWithEager();
}
