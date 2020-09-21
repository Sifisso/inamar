package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.InstrumentoLegalDao;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class InstrumentoLegalDaoImpl extends GenericDaoImpl<InstrumentoLegal> implements InstrumentoLegalDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<InstrumentoLegal> findByNomeActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select il from InstrumentoLegal il where il.nome like :nome and il.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@Override
	public List<InstrumentoLegal> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lp from InstrumentoLegal lp where lp not in (select l_p from InstrumentoLegal l_p join l_p.pedidosEtapas lpp where lpp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

}
