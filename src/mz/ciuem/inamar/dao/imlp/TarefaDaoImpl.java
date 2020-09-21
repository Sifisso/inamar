package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.TarefaDao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Tarefa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TarefaDaoImpl extends GenericDaoImpl<Tarefa> implements TarefaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarefa> findByNomeIsActivo(String nome, boolean isActivo) {
        Query query = getCurrentSession().createQuery("select t from Tarefa t where t.descricao like :nome and t.isActivo=:isActivo");
		query.setParameter("nome","%"+nome+"%");
        query.setParameter("isActivo", isActivo);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarefa> findNotInPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select lp from Tarefa lp where lp not in (select l_p from Tarefa l_p join l_p.tarefasNasEtapas lpp where lpp.pedido=:pedido)");
		query.setParameter("pedido", pedido);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarefa> findNotInEtapaFluxo(EtapaFluxo etapaFluxo) {
		Query query = getCurrentSession().createQuery("select lp from Tarefa lp where lp not in (select l_p from Tarefa l_p join l_p.tarefasNasEtapas lpp where lpp.etapaFluxo=:etapaFluxo)");
		query.setParameter("etapaFluxo", etapaFluxo);
		return query.list();
	}

}
