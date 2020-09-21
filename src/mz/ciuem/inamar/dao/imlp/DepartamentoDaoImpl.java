package mz.ciuem.inamar.dao.imlp;

import java.util.Collection;
import java.util.List;

import mz.ciuem.inamar.dao.DepartamentoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Departamento;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoDaoImpl extends GenericDaoImpl<Departamento> implements DepartamentoDao{

	@Override
	public List<Departamento> findByNomeIsAdmar(String nome, boolean isAdmar) {
		org.hibernate.Query query = getCurrentSession().createQuery("select d from Departamento d where d.nome like :nome and d.isAdmar=:isAdmar");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isAdmar", isAdmar);
		//query.setMaxResults(20);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends Departamento> findByDelegacao(Delegacao delegacao, boolean isAdmar) {
		Query query = getCurrentSession().createQuery("select d from Departamento d where d.isAdmar=:isAdmar and d not in (select dlp from Departamento dlp join dlp.delegacaoDepartamentos  delDepts where delDepts.delegacao=:delegacao ) order by d.nome asc");
		query.setParameter("delegacao", delegacao);
		query.setParameter("isAdmar", isAdmar);
		return query.list();
	}

}
