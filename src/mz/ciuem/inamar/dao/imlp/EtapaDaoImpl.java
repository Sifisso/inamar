package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EtapaDao;
import mz.ciuem.inamar.entity.Etapa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EtapaDaoImpl extends GenericDaoImpl<Etapa> implements EtapaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Etapa> findByNomeIsActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select e from Etapa e where e.nome like :nome and e.isActivo=:isActivo");
		query.setParameter("nome","%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

}
