package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoEmbarcacaoDao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PeticaoEmbarcacaoDaoImpl extends GenericDaoImpl<PeticaoEmbarcacao> implements PeticaoEmbarcacaoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoEmbarcacao> findWithEager() {
		Query query = getCurrentSession().createQuery("select pe from PeticaoEmbarcacao pe JOIN FETCH pe.pais p JOIN FETCH pe.classeEmbarcacao ce JOIN FETCH pe.servicoDestino sd JOIN FETCH pe.pedido");
		return query.list();
	}

	@Override
	public PeticaoEmbarcacao findOneWithEager(long id) {
		Query query = getCurrentSession().createQuery("select pe from PeticaoEmbarcacao pe LEFT JOIN FETCH pe.peticao pet LEFT JOIN FETCH pe.pais p LEFT JOIN FETCH pe.classeEmbarcacao ce LEFT JOIN FETCH pe.servicoDestino sd JOIN FETCH pe.pedido where pe.id=:id");
		query.setParameter("id", id);
		return (PeticaoEmbarcacao) query.uniqueResult();
	}
	
}
