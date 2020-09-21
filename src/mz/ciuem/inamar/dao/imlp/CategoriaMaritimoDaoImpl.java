package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.CategoriaMaritimoDao;
import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaMaritimoDaoImpl extends GenericDaoImpl<CategoriaMaritimo> implements CategoriaMaritimoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaMaritimo> findByClasseMaritimo(ClasseMaritimo classeMaritimo) {
		Query query = getCurrentSession().createQuery("select c from CategoriaMaritimo c join fetch c.classeMaritimo cm where cm=:classeMaritimo");
		query.setParameter("classeMaritimo", classeMaritimo);
		return query.list();
	}

}
