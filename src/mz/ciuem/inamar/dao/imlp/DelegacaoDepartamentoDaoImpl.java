package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.DelegacaoDepartamentoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DelegacaoDepartamentoDaoImpl extends GenericDaoImpl<DelegacaoDepartamento> implements DelegacaoDepartamentoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegacaoDepartamento> findByDelegacao(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select delDep from DelegacaoDepartamento delDep JOIN FETCH delDep.delegacao deleg JOIN FETCH delDep.departamento dep where deleg =:delegacao");
		query.setParameter("delegacao", delegacao);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegacaoDepartamento> findByNomeActivo(String nome, boolean activo) {
		Query query = getCurrentSession().createQuery("select delDep from DelegacaoDepartamento delDep JOIN FETCH delDep.departamento dep JOIN FETCH delDep.delegacao deleg where dep.nome like :nome and delDep.isActivo =:activo");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("activo", activo);
		return query.list();
	}

}
