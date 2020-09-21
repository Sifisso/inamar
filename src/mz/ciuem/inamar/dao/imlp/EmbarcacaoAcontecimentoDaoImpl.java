package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ciuem.inamar.dao.EmbarcacaoAcontecimentoDao;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.EmbarcacaoAcontecimento;

@Repository
public class EmbarcacaoAcontecimentoDaoImpl extends GenericDaoImpl<EmbarcacaoAcontecimento> implements EmbarcacaoAcontecimentoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<EmbarcacaoAcontecimento> findByNome (String embarcacao){
		org.hibernate.Query query = getCurrentSession().createQuery("select ea from EmbarcacaoAcontecimento ea join fetch ea.embarcacao e where e.nome like:embarcacao");
		query.setParameter("embarcacao", "%"+embarcacao+"%");
		 return query.list();
	}
}
