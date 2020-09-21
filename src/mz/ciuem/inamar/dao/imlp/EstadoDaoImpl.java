package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EstadoDao;
import mz.ciuem.inamar.entity.Estado;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDaoImpl extends GenericDaoImpl<Estado> implements EstadoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Estado> findByNomeIsActivo(String nome, boolean isActivo) {
        Query query = getCurrentSession().createQuery("select e from Estado e where e.nome like :nome and e.isActivo=:isActivo");
        query.setParameter("nome", "%"+nome+"%");
        query.setParameter("isActivo", isActivo);
		return query.list();
	}

}
