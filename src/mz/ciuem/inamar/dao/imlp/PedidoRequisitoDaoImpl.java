package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PedidoRequisitoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRequisitoDaoImpl extends GenericDaoImpl<PedidoRequisito> implements PedidoRequisitoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoRequisito> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lpp from PedidoRequisito lpp join fetch lpp.tipoRequisito lp join fetch lpp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

	@Override
	public List<PedidoRequisito> findByPedidoVisivelParaUtente(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lpp from PedidoRequisito lpp join fetch lpp.tipoRequisito lp join fetch lpp.pedido p where p=:pedido and lp.isVisivelUtente=true");
		query.setParameter("pedido", pedido);
		return query.list();
	}
	
	@Override
	public List<PedidoRequisito> findByPedidoActivoVisivelParaUtente(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lpp from PedidoRequisito lpp join fetch lpp.tipoRequisito lp join fetch lpp.pedido p where p=:pedido and lp.isVisivelUtente=true and lpp.isActivo=true");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
