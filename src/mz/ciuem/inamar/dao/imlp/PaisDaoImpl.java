package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.PaisDao;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;

@Repository
public class PaisDaoImpl  extends GenericDaoImpl<Pais> implements PaisDao{

	@Override
	public List<Pais> listarPaises() {
		Query query = getCurrentSession().createQuery("from Pais pais join fetch pais.provincias provincias ");
		query.setMaxResults(15);
		return query.list();
	}

	
	public List<Distrito> listarDistritos(Provincia provincia){
		
		Query query = getCurrentSession().createQuery("from Distrito distrito where distrito.provincia = :provincia");
		
		query.setParameter("provincia", provincia);
		return query.list();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Pais>findByNomePais(String designacao){
		org.hibernate.Query query = getCurrentSession().createQuery("select from Pais");
		query.setParameter("designacao", designacao);
		return query.list();
	}
	
	
	
}
