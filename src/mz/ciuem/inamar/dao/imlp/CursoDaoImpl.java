package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.CursoDao;
import mz.ciuem.inamar.entity.Curso;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDaoImpl extends GenericDaoImpl<Curso> implements CursoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select c from Curso c where c not in (select cu from Curso cu join cu.cursoPedidos cp where cp.pedido=:pedido)");
		query.setParameter("pedido", pedido);		
		return query.list();
	}

}
