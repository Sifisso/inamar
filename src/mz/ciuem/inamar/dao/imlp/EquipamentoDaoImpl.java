package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EquipamentoDao;
import mz.ciuem.inamar.entity.Equipamento;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EquipamentoDaoImpl extends GenericDaoImpl<Equipamento> implements EquipamentoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select e from Equipamento e where e not in (select eq from Equipamento eq join eq.equipamentosPedidos eqp where eqp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
