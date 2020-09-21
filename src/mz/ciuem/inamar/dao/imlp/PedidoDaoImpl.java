package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoPedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoDaoImpl extends GenericDaoImpl<Pedido> implements PedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findByTipoPedido(TipoPedido tipoPedido) {
		Query query = getCurrentSession().createQuery("select p from Pedido p JOIN FETCH p.tipoPedido tp JOIN FETCH p.fluxo f JOIN FETCH tp.area a where tp=:tipoPedido");
		query.setParameter("tipoPedido", tipoPedido);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findByNomenNomeNomeFluxoIsActivo(String nome, String nomeFluxo, boolean isActivo, TipoPedido tipoPedido) {
		Query query = getCurrentSession().createQuery("select p from Pedido p JOIN FETCH p.tipoPedido tp JOIN FETCH p.fluxo f where p.descricao like :nome and f.nome like :nomeFluxo and p.isActivo=:isActivo and tp=:tipoPedido");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("nomeFluxo", "%"+nomeFluxo+"%");
		query.setParameter("isActivo", isActivo);
		query.setParameter("tipoPedido", tipoPedido);
		return query.list();
	}

}
