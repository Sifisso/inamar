package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EntradaDao;
import mz.ciuem.inamar.entity.Entrada;

import org.springframework.stereotype.Repository;

@Repository
public class EntradaDaoImpl extends GenericDaoImpl<Entrada> implements EntradaDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<Entrada> findByNome (String embarcacao){
		org.hibernate.Query query = getCurrentSession().createQuery("select ent from Entrada ent ");
		
		query.setParameter("embarcacao", "%"+embarcacao+"%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Entrada> findByNomeEmb (String embarcacao){
		org.hibernate.Query query = getCurrentSession().createQuery("");
		
		query.setParameter("embarcacao", "%"+embarcacao+"%");
		return query.list();
	}
}
