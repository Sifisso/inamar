package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.AcontecimentoDao;
import mz.ciuem.inamar.entity.Acontecimento;

@Repository
public class AcontecimentoDaoImpl extends GenericDaoImpl<Acontecimento> implements AcontecimentoDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Acontecimento> findBydescricaoActivo (String descricao, boolean isActivo){
		org.hibernate.Query query = getCurrentSession().createQuery("select a from Acontecimento a where a.descricao like :descricao and a.isActivo=:isActivo");
		
		query.setParameter("descricao", "%"+descricao+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}
}
