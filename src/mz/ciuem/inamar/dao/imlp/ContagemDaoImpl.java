package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.ContagemDao;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ContagemDaoImpl extends GenericDaoImpl<Contagem> implements ContagemDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Contagem> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select c from Contagem c where c not in (select co from Contagem co join co.contagemPedidos cp where cp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
