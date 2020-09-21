package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.LocalPraticaPedidoDao;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LocalPraticaPedidoDaoImpl extends GenericDaoImpl<LocalPraticaPedido> implements LocalPraticaPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalPraticaPedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lpp from LocalPraticaPedido lpp join fetch lpp.localPratica lp join fetch lpp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
