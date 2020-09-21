package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.ContagemPedidoDao;
import mz.ciuem.inamar.entity.ContagemPedido;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ContagemPedidoDaoImpl extends GenericDaoImpl<ContagemPedido> implements ContagemPedidoDao{

	@Override
	public List<ContagemPedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select cp from ContagemPedido cp join fetch cp.contagem c join fetch cp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
