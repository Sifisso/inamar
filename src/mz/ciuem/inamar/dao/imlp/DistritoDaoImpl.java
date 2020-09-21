package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.DistritoDao;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Provincia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DistritoDaoImpl extends GenericDaoImpl<Distrito> implements DistritoDao {

	@SuppressWarnings("unchecked")
	public List<Distrito> buscarDistritosDeUmaProvincia(Provincia provincia) {
		Query query = getCurrentSession().createQuery("select dst from Distrito dst where dst.provincia =:provincia");
		query.setParameter("provincia", provincia);
		return query.list();
	}

		
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> buscarTodosDoDistritos() {
		Query query = getCurrentSession().createQuery("select distinct d.designacao, d.provincia.designacao from Distrito d order by d.provincia.designacao asc");
		return (List<Object[]>) query.list();
	}

}
