package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoPedidoEtapaInstrumentoLegalDao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PeticaoPedidoEtapaInstrumentoLegalDaoImpl extends GenericDaoImpl<PeticaoPedidoEtapaInstrumentoLegal> implements PeticaoPedidoEtapaInstrumentoLegalDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoPedidoEtapaInstrumentoLegal> findByPeticao(Peticao peticao) {
		Query query = getCurrentSession().createQuery("select pp from PeticaoPedidoEtapaInstrumentoLegal pp join fetch pp.pedidoEtapa pe join fetch pe.instrumentoLegal il where pp.peticao=:peticao");
		query.setParameter("peticao", peticao);
		return query.list();
	}

}
