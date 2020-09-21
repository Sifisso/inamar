package mz.ciuem.inamar.dao.imlp;

import java.util.List;
import java.util.Set;

import mz.ciuem.inamar.dao.ProvinciaDao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinciaDaoImpl extends GenericDaoImpl<Provincia> implements ProvinciaDao {

	public List<String> buscarNomeDeTodasProvincias() {
		Query query = getCurrentSession().createQuery("SELECT DISTINCT p.designacao FROM Provincia p");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Provincia> buscarTodasProvinciasDeUmPais(Pais pais) {
		Query query = getCurrentSession().createQuery("select provincia from Provincia provincia where provincia.pais = :pais");
		query.setParameter("pais", pais);
		return query.list();
	}
}
