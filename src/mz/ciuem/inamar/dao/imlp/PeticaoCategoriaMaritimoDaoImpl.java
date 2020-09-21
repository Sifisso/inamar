package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoCategoriaMaritimoDao;
import mz.ciuem.inamar.entity.PeticaoCategoriaMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PeticaoCategoriaMaritimoDaoImpl extends GenericDaoImpl<PeticaoCategoriaMaritimo> implements PeticaoCategoriaMaritimoDao{

	@Override
	public PeticaoCategoriaMaritimo findByPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo) {
		Query query = getCurrentSession().createQuery("select pcm from PeticaoCategoriaMaritimo pcm join fetch pcm.peticaoMaritimo pm  join fetch pcm.categoriaMaritimo cm where pm=:peticaoMaritimo order by pcm.id  desc");
		query.setParameter("peticaoMaritimo", peticaoMaritimo);
		query.setMaxResults(1);
		return (PeticaoCategoriaMaritimo) query.uniqueResult();
	}

}
