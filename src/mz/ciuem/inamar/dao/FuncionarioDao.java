package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;
import mz.ciuem.inamar.entity.Funcionario;

public interface FuncionarioDao extends GenericDao<Funcionario>{

	public List<Funcionario> findByNome(String nome, String email);
	public Funcionario buscarDadosDoFuncionario(Funcionario funcionario);
	public List<Object[]> getDelegacaoFuncionario();

}
