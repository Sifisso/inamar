package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.PeticaoEtapaDao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.service.PeticaoEtapaService;

@Service("peticaoEtapaService")
public class PeticaoEtapaServiceImpl extends GenericServiceImpl<PeticaoEtapa> implements PeticaoEtapaService{
	
	@Autowired
	private PeticaoEtapaDao _pEDao;

	@Override
	public List<PeticaoEtapa> findByPeticaoLikePerfil(Peticao peticao,String perfil) {
		return _pEDao.findByPeticaoLikePerfil(peticao, perfil);
	}

	@Override
	public PeticaoEtapa findByPeticaoLikePerfilEstado(Peticao peticao,String perfil, String estado) {
		return _pEDao.findByPeticaoLikePerfilEstado(peticao, perfil, estado);
	}

	@Override
	public List<PeticaoEtapa> findByPeticao(Peticao peticao) {
		return _pEDao.findByPeticao(peticao);
	}

	
}
