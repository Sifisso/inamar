package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TarefaNaEtapaDao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TarefaNaEtapa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TarefaNaEtapaDaoImpl extends GenericDaoImpl<TarefaNaEtapa> implements TarefaNaEtapaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TarefaNaEtapa> findByPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lpp from TarefaNaEtapa lpp join fetch lpp.tarefa lp join fetch lpp.pedido p where p=:pedido");
		query.setParameter("pedido", pedido);
		return query.list();
	}

	@Override
	public List<TarefaNaEtapa> findByEtapaFluxo(EtapaFluxo etapaFluxo) {
		Query query = getCurrentSession().createQuery("select lpp from TarefaNaEtapa lpp join fetch lpp.tarefa lp join fetch lpp.etapaFluxo ep where ep=:etapaFluxo");
		query.setParameter("etapaFluxo", etapaFluxo);
		return query.list();
	}

}
