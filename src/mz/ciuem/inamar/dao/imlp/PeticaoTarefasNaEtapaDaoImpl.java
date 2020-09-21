package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoTarefasNaEtapaDao;
import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PeticaoTarefasNaEtapaDaoImpl extends GenericDaoImpl<PeticaoTarefasNaEtapa> implements PeticaoTarefasNaEtapaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoTarefasNaEtapa> findByPeticaoEtapa(
			PeticaoEtapa peticaoEtapa) {
		Query query = getCurrentSession().createQuery("select pte from PeticaoTarefasNaEtapa pte join fetch pte.tarefaNaEtapa te join fetch te.tarefa t where pte.peticaoEtapa = :peticaoEtapa");
		query.setParameter("peticaoEtapa", peticaoEtapa);
		return query.list();
	}

}
