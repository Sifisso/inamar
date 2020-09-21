package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EquipamentoPedidoDao;
import mz.ciuem.inamar.entity.EquipamentoPedido;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EquipamentoPedidoDaoImpl extends GenericDaoImpl<EquipamentoPedido> implements EquipamentoPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipamentoPedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select eqp from EquipamentoPedido eqp join fetch eqp.pedido p join fetch eqp.equipamento eq where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
