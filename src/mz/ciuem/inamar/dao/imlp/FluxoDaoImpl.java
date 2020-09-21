package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.FluxoDao;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.TipoPedido;

@Repository
public class FluxoDaoImpl extends GenericDaoImpl<Fluxo> implements FluxoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Fluxo> findByNomeIsActivo(String nome, boolean isActivo) {
		Query query = getCurrentSession().createQuery("select f from Fluxo f where f.nome like :nome and f.isActivo=:isActivo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@Override
	public List<Fluxo> finByNotInPedido(TipoPedido tipoPedido) {
		Query query = getCurrentSession().createQuery("select f from Fluxo f where f not in (select fl from Fluxo fl join fl.pedidos p where p.tipoPedido =:tipoPedido) order by f.nome asc");
		query.setParameter("tipoPedido", tipoPedido);
	    return query.list();
	}

}
