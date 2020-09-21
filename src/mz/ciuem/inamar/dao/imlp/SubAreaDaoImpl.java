package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.SubAreaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.SubArea;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubAreaDaoImpl extends GenericDaoImpl<SubArea> implements SubAreaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<SubArea> findByNomeActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select s from SubArea s where s.nome like :nome and s.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubArea> findByArea(Area area) {
		Query query = getCurrentSession().createQuery("select s from SubArea s where s.area=:area");
		query.setParameter("area", area);
		return query.list();
	}

}
