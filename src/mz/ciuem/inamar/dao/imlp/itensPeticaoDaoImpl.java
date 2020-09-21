package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.ItensPeticaoDao;
import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Peticao;

@Repository
public class itensPeticaoDaoImpl extends GenericDaoImpl<ItensPeticao> implements ItensPeticaoDao{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItensPeticao> findByPeticaoID(Peticao peticao) {
		Query query = em.createQuery("select it from ItensPeticao it join fetch it.peticao p where it.peticao=:peticao");
		query.setParameter("peticao", peticao);
		return query.getResultList();
	}

}
