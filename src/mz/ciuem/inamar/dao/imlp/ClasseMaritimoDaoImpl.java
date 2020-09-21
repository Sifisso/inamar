package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.ClasseMaritimoDao;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ClasseMaritimoDaoImpl extends GenericDaoImpl<ClasseMaritimo> implements ClasseMaritimoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ClasseMaritimo> finByNomeActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select c from ClasseMaritimo c where c.nome like :nome and c.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClasseMaritimo> findByGrupoMaritimo(GrupoMaritimo grupoMaritimo) {
		Query query = getCurrentSession().createQuery("select c from ClasseMaritimo c JOIN FETCH c.grupoMaritimo gm where gm=:grupoMaritimo");
		query.setParameter("grupoMaritimo", grupoMaritimo);
	    return query.list();
	}

}
