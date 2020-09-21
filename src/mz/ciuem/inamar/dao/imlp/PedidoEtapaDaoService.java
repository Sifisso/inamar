package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PedidoEtapaDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoEtapaDaoService extends GenericDaoImpl<PedidoEtapa> implements PedidoEtapaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoEtapa> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lpp from PedidoEtapa lpp join fetch lpp.instrumentoLegal lp join fetch lpp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
