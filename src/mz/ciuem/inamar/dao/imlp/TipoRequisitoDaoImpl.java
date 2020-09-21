package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TipoRequisitoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoRequisito;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TipoRequisitoDaoImpl extends GenericDaoImpl<TipoRequisito> implements TipoRequisitoDao{

	@Override
	public List<TipoRequisito> findByNomeActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select t from TipoRequisito t where t.nome like :nome and t.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoRequisito> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lp from TipoRequisito lp where lp not in (select l_p from TipoRequisito l_p join l_p.pedidosRequisitos lpp where lpp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
