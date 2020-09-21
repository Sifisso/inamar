package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.MeioTransportePedidoDao;
import mz.ciuem.inamar.entity.MeioTransportePedido;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MeioTransportePedidoDaoImpl extends GenericDaoImpl<MeioTransportePedido> implements MeioTransportePedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<MeioTransportePedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select mtp from MeioTransportePedido mtp join fetch mtp.meioTransporte mt join fetch mtp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
