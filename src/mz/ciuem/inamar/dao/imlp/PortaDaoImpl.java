package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.PortoDao;
import mz.ciuem.inamar.entity.Porto;


@Repository
public class PortaDaoImpl extends GenericDaoImpl<Porto> implements PortoDao{

	@Override
	public List<Porto> findBydescricaoActivo(String descricao, boolean isActivo) {
org.hibernate.Query query = getCurrentSession().createQuery("select a from Porto a where a.descricao like :descricao and a.isActivo=:isActivo");
		
		query.setParameter("descricao", "%"+descricao+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

}
