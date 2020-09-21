package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.EmbarcacaoDao;
import mz.ciuem.inamar.entity.Embarcacao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EmbarcacaoDaoImpl extends GenericDaoImpl<Embarcacao> implements EmbarcacaoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Embarcacao> findByNomeActivo (String nome, boolean isActivo){
		org.hibernate.Query query = getCurrentSession().createQuery("select e from Embarcacao e where e.nome like :nome	or e.propreitario like :nome and e.isActivo=:isActivo");
		
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("isActivo", isActivo);
		return query.list();
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNaviosByDelegacaoDeRegisto() {
		Query query = getCurrentSession().createQuery("SELECT del.nome, nac.designacao, "
				+ " count (emb.id)"
				+ "from Embarcacao emb "
				+ "join emb.delegacao del "
				+ "join emb.nacionalidade nac "
				+ "group by del.nome, nac.designacao ");
		
		return query.list();
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNaviosByDelegacaoDeRegisto() {
		Query query = getCurrentSession().createQuery("SELECT e.delegacao.nome, e.nacionalidade.designacao, COUNT(e.id), "
				+ "(SELECT COUNT(em.id) FROM Embarcacao em join em.delegacao d join em.nacionalidade n where em.isActivo=true and em.delegacao=d.id and d.id=e.delegacao and em.nacionalidade=n.id and n.id=e.nacionalidade), "
				+ "(SELECT COUNT(em.id) FROM Embarcacao em join em.delegacao d join em.nacionalidade n where em.isActivo=false and em.delegacao=d.id and d.id=e.delegacao and em.nacionalidade=n.id and n.id=e.nacionalidade) "
				+ "FROM Embarcacao e group by e.delegacao.nome, e.nacionalidade.designacao, e.delegacao, e.nacionalidade");
		
		return query.list();
	}
	
}
