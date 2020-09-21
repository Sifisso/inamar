package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.RotaDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Rota;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RotaDaoImpl extends GenericDaoImpl<Rota> implements RotaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Rota> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select r from Rota r where r not in (select ro from Rota ro join ro.rotaPedidos rp where rp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
