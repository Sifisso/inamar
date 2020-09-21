package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TipoEmbarcacaoPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoEmbarcacaoPedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TipoEmbarcacaoPedidoDaoImpl extends GenericDaoImpl<TipoEmbarcacaoPedido> implements TipoEmbarcacaoPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoEmbarcacaoPedido> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select tp from TipoEmbarcacaoPedido tp  join fetch tp.tipoEmbarcacao te join fetch tp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}



}
