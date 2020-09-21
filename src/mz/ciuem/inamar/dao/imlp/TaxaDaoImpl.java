package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TaxaDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.Taxa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TaxaDaoImpl extends GenericDaoImpl<Taxa> implements TaxaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxa> findByNomeActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select t from Taxa t where t.nome like :nome and t.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxa> finBySubArea(SubArea subArea) {
		Query query = getCurrentSession().createQuery("select t from Taxa t where t.subArea=:subArea");
		query.setParameter("subArea", subArea);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxa> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lp from Taxa lp where lp not in (select l_p from Taxa l_p join l_p.taxasPedido lpp where lpp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taxa> findNotInPedidoInSubSrea(SubArea subArea, Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lp from Taxa lp where lp not in (select l_p from Taxa l_p join l_p.taxasPedido lpp where lpp.pedido=:pedido) and lp.subArea=:subArea");
		query.setParameter("pedido", pedido);
		query.setParameter("subArea", subArea);
		return query.list();
	}

}
