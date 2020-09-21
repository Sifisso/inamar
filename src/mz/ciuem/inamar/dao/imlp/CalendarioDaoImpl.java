package mz.ciuem.inamar.dao.imlp;

import java.util.List;




import mz.ciuem.inamar.dao.CalendarioDao;
import mz.ciuem.inamar.entity.Calendario;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarioDaoImpl extends GenericDaoImpl<Calendario> implements CalendarioDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Calendario> buscarCalendarioPorCandidato(String cod) {
		Query query = (Query) getCurrentSession().createQuery("Select cal from Calendario cal where cal.codCandidato=:cod order by cal.data asc, cal.hora desc");
		query.setParameter("cod", cod);
		return query.list();
	}

}
