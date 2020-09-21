package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.CategoriaDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDaoImpl extends GenericDaoImpl<Categoria> implements CategoriaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findByNomeActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select c from Categoria c where c.nome like :nome and c.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findByNotInPedido(Area area) {
		Query query = getCurrentSession().createQuery("select c from Categoria c where c not in (select cat from Categoria cat join cat.tipoPedidos tp where tp.area =:area) order by c.nome asc");
		query.setParameter("area", area);
	    return query.list();
	}

}
