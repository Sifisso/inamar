package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.DelegacaoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Provincia;

import org.springframework.stereotype.Repository;

@Repository
public class DelegacaoDaoImpl extends GenericDaoImpl<Delegacao> implements DelegacaoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Delegacao> findByNomeProvincia(String nome, Provincia provincia, Instituicao instituicao) {
		org.hibernate.Query query = getCurrentSession().createQuery("select d from Delegacao d join fetch d.provincia p where d.nome like :nome and d.provincia=:provincia and d.instituicao=:instituicao");
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("provincia", provincia);
		query.setParameter("instituicao", instituicao);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delegacao> findByInstituicao(Instituicao instituicao) {
		org.hibernate.Query query = getCurrentSession().createQuery("select DISTINCT d from Delegacao d join fetch d.provincia p left join d.contas c where d.instituicao=:instituicao");
		query.setParameter("instituicao", instituicao);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delegacao> findWithEager() {
		org.hibernate.Query query = getCurrentSession().createQuery("select d from Delegacao d join d.contas c join d.provincia");
		//query.setMaxResults(20);
		return query.list();
	}

}
