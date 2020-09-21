package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.CursoPedidoDao;
import mz.ciuem.inamar.entity.CursoPedido;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CursoPedidoDaoImpl extends GenericDaoImpl<CursoPedido> implements CursoPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CursoPedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select cp from CursoPedido cp join fetch cp.curso c join fetch cp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
