package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TipoEmbarcacaoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;



@Repository
public class TipoEmbarcacaoDaoImpl extends GenericDaoImpl<TipoEmbarcacao> implements TipoEmbarcacaoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoEmbarcacao> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select te from TipoEmbarcacao te where te not in (select tem from TipoEmbarcacao tem join tem.tipoEmbarcacaoPedidos tp where tp.pedido=:pedido)");
        query.setParameter("pedido", pedido);
		return query.list();
	}

	

}
