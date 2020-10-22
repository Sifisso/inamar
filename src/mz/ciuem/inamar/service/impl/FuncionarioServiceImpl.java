package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.FuncionarioDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("funcionarioService")
public class FuncionarioServiceImpl extends GenericServiceImpl<Funcionario> implements FuncionarioService{

	@Autowired
	private FuncionarioDao _funDao;
	
	@Override
	public List<Funcionario> findByNome(String nome, String email) {
		// TODO Auto-generated method stub
		return _funDao.findByNome(nome, email);
	}

	@Override
	public Funcionario buscarDadosDoFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return _funDao.buscarDadosDoFuncionario( funcionario);
	}

	@Override
	public List<Object[]> getDelegacaoFuncionario() {
		// TODO Auto-generated method stub
		return _funDao.getDelegacaoFuncionario();
	}

	@Override
	public List<Funcionario> buscarFuncionarioPorDelegacao(Delegacao delegacao) {
		// TODO Auto-generated method stub
		return _funDao.buscarFuncionarioPorDelegacao(delegacao);
	}
	
	

}
