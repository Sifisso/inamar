package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TaxaPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TaxaPedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TaxaPedidoDaoImpl extends GenericDaoImpl<TaxaPedido> implements TaxaPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxaPedido> findByPedido(Pedido pedido) {
//		Query query = getCurrentSession().createQuery("");
		org.hibernate.Query query = getCurrentSession().createQuery("select lpp from TaxaPedido lpp "
				+ "join fetch lpp.taxa lp "
				+ "join fetch lpp.pedido p "
				+ "where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
		
		//select lpp from TaxaPedido lpp join fetch lpp.taxa lp join fetch lpp.pedido p where p=:pedido
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaxaPedido> findByPedidoTaxaPedido(Pedido pedido) {
	
		org.hibernate.Query query = getCurrentSession().createQuery("select tp from TaxaPedido tp "
				+ "join fetch tp.taxa tx "
				+ "join fetch tp.pedido p "
				+ "where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaxaPedido> findTaxaPedidoByPedido(Pedido pedido) {
	
		org.hibernate.Query query = getCurrentSession().createQuery("select tp from TaxaPedido tp "
				+ "join fetch tp.taxa tx "
				+ "join fetch tp.pedido p "
				+ "");
		//query.setParameter("pedido", pedido);
		return query.list();
	}
}
