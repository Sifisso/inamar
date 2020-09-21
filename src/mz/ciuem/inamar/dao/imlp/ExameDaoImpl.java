package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.ExameDao;
import mz.ciuem.inamar.entity.Exame;


@Repository
public class ExameDaoImpl extends GenericDaoImpl<Exame> implements ExameDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Exame> findBydescricao (String descricao){
		org.hibernate.Query query = getCurrentSession().createQuery("select e from Exame e where e.descricao like :descricao");
		
		query.setParameter("descricao", "%"+descricao+"%");
		return query.list();
	}
}
