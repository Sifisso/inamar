package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TipoPedidoDao;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.TipoPedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TipoPedidoDaoImpl extends GenericDaoImpl<TipoPedido> implements TipoPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPedido> findByArea(Area area) {
		Query query = getCurrentSession().createQuery("select t from TipoPedido t JOIN FETCH t.area a JOIN FETCH t.categoria c where a=:area");
		query.setParameter("area", area);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPedido> findByNomeCategoriaAreaIsActivo(String nome,Area area, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select t from TipoPedido t JOIN FETCH t.area a JOIN FETCH t.categoria c where a=:area and t.nome like :nome and t.isActivo=:isActivo");
		query.setParameter("area", area);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

}
