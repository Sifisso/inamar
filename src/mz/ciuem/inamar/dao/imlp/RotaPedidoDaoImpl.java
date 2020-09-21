package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.RotaPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.RotaPedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RotaPedidoDaoImpl extends GenericDaoImpl<RotaPedido> implements RotaPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<RotaPedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select rp from RotaPedido rp join fetch rp.rota r join fetch rp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
