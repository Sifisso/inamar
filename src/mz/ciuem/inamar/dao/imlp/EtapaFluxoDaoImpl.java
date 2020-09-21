package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EtapaFluxoDao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Fluxo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EtapaFluxoDaoImpl extends GenericDaoImpl<EtapaFluxo> implements EtapaFluxoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<EtapaFluxo> findByFluxo(Fluxo fluxo) {
        Query query = getCurrentSession().createQuery("select ef from EtapaFluxo ef JOIN FETCH ef.etapa etapa JOIN FETCH ef.estado estado where ef.fluxo=:fluxo");
        query.setParameter("fluxo", fluxo);
		return query.list();
	}

	@Override
	public List<EtapaFluxo> findByNomeActivo(String nome, boolean isActivo, Fluxo fluxo) {
		Query query = getCurrentSession().createQuery("select ef from EtapaFluxo ef JOIN FETCH ef.etapa etapa JOIN FETCH ef.estado estado where ef.fluxo=:fluxo and ef.descricao like :nome and ef.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		query.setParameter("fluxo", fluxo);
		return query.list();
	}

}
