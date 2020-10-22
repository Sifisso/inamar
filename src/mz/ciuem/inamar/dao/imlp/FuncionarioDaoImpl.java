package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.FuncionarioDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Peticao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionarioDaoImpl extends GenericDaoImpl<Funcionario> implements FuncionarioDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> findByNome (String nome, String email){
		org.hibernate.Query query = getCurrentSession().createQuery("select f from Funcionario f where f.nome like :nome or f.email like :email");
		
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("email", "%"+email+"%");
		return query.list();
	}

	@Override
	public Funcionario buscarDadosDoFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select f from Funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where f=:funcionario ");
		query.setParameter("funcionario", funcionario);
		
		return (Funcionario) query.uniqueResult();
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDelegacaoFuncionario() {
		Query query = getCurrentSession().createQuery("select del.nome, "
				+ " count(f.id) "
				+ "from Funcionario f "
				+ "JOIN f.sector s "
				+ "join s.delegacaoDepartamento dep "
				+ "join dep.delegacao del "
				+ "group by del.nome");
		
		return query.list();
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDelegacaoFuncionario() {
		Query query = getCurrentSession().createQuery("SELECT del.nome, count(f.id), "
				+ "(SELECT COUNT(f1.id) from Funcionario f1 JOIN f1.sector s1 join s1.delegacaoDepartamento dd1 join dd1.delegacao dell WHERE del1=del and f.userLogin.enabled=true),"
				+ "(SELECT COUNT(f1.id) from Funcionario f1 JOIN f1.sector s1 join s1.delegacaoDepartamento dd1 join dd1.delegacao dell WHERE del1=del and f.userLogin.enabled=false)"
				+ "from Funcionario f JOIN f.sector s join s.delegacaoDepartamento dd join dd.delegacao del group by del.nome, del, f.userLogin");
		
		return query.list();
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> buscarFuncionarioPorDelegacao(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select f from Funcionario f "
				+ "join fetch f.userLogin us "
				+ "inner JOIN FETCH f.sector s "
				+ "inner join fetch s.delegacaoDepartamento dd "
				+ "inner join fetch dd.delegacao del "
				+ "  where del=:delegacao order by f.updated desc");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
	
}
