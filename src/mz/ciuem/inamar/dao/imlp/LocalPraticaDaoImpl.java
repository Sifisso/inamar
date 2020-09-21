package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.LocalPraticaDao;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LocalPraticaDaoImpl extends GenericDaoImpl<LocalPratica> implements LocalPraticaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalPratica> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lp from LocalPratica lp where lp not in (select l_p from LocalPratica l_p join l_p.localPraticaPedidos lpp where lpp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
