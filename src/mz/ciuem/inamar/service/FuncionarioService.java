package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Peticao;

public interface FuncionarioService extends GenericService<Funcionario>{

	public List<Funcionario> findByNome (String nome, String email);
	public Funcionario buscarDadosDoFuncionario(Funcionario funcionario);
	public List<Object[]> getDelegacaoFuncionario();
	public List<Funcionario> buscarFuncionarioPorDelegacao(Delegacao delegacao);
}
