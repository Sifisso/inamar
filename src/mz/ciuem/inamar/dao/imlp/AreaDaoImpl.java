package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.AreaDao;
import mz.ciuem.inamar.entity.Area;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AreaDaoImpl extends GenericDaoImpl<Area> implements AreaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findByNomeIsActivoIsAdmar(String nome, boolean isActivo, boolean isAdmar) {
		Query query = getCurrentSession().createQuery("select a from Area a where a.nome like :nome and a.isActivo=:isActivo and a.isAdmar=:isAdmar");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		query.setParameter("isAdmar", isAdmar);
		return query.list();
	}

}
