package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.DelegacaoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.service.DelegacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("delegacaoService")
public class DelegacaoServiceImpl extends GenericServiceImpl<Delegacao> implements DelegacaoService{

	@Autowired
	private DelegacaoDao delegacaoDao;

	@Override
	public List<Delegacao> findByInstituicao(Instituicao instituicao) {
		return delegacaoDao.findByInstituicao(instituicao);
	}

	@Override
	public List<Delegacao> findWithEager() {
		return delegacaoDao.findWithEager();
	}

	@Override
	public List<Delegacao> findByNomeProvincia(String nome,
			Provincia provincia, Instituicao instituicao) {
		return delegacaoDao.findByNomeProvincia(nome, provincia, instituicao);
	}

}
