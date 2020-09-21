package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.MeioTransporteDao;
import mz.ciuem.inamar.entity.MeioTransporte;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MeioTransporteDaoImpl extends GenericDaoImpl<MeioTransporte> implements MeioTransporteDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<MeioTransporte> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select mt from MeioTransporte mt where mt not in (select mtt from MeioTransporte mtt join mtt.meioTransportePedidoPedidos mtp where mtp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
