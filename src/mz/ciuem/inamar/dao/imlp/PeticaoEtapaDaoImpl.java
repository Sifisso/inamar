package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoEtapaDao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEtapa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PeticaoEtapaDaoImpl extends GenericDaoImpl<PeticaoEtapa> implements PeticaoEtapaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoEtapa> findByPeticaoLikePerfil(Peticao peticao,String perfil) {
		Query query = getCurrentSession().createQuery("select pe from PeticaoEtapa pe join fetch pe.etapaFluxo ef join fetch ef.etapa et where pe.peticao=:peticao and et.nome like :perfil");
		query.setParameter("peticao", peticao);
		query.setParameter("perfil", "%"+perfil+"%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PeticaoEtapa findByPeticaoLikePerfilEstado(Peticao peticao,String perfil, String estado) {
																																																									// and pe.realizada=false
		Query query = getCurrentSession().createQuery("select pe from PeticaoEtapa pe join fetch pe.peticao pet join fetch pe.etapaFluxo ef join fetch ef.etapa et where pet=:peticao and et.nome like :perfil and pet.estado=:estado");
		query.setParameter("peticao", peticao);
		query.setParameter("perfil", "%"+perfil+"%");
		query.setParameter("estado", estado);
		query.setMaxResults(1);
		return (PeticaoEtapa) query.uniqueResult();
	}

	@Override
	public List<PeticaoEtapa> findByPeticao(Peticao peticao) {
		Query query = getCurrentSession().createQuery("select pe from PeticaoEtapa pe join fetch pe.peticoesTarefasNaEtapa pte join fetch pte.tarefaNaEtapa te where pe.peticao=:peticao");
		query.setParameter("peticao", peticao);
		return query.list();
	}

}
