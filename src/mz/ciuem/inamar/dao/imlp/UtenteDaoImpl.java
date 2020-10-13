package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.UtenteDao;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;

import org.hibernate.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UtenteDaoImpl extends GenericDaoImpl<Utente> implements UtenteDao{

	@SuppressWarnings("unchecked")
	@Override
	public Utente buscarUtenteByUser(User user) {
		javax.persistence.Query query = em.createQuery("select u from Utente u where u.userLogin=:user");
		query.setParameter("user", user);
		return (Utente) DataAccessUtils.singleResult(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> findUtentesNotMaritimos() {
		org.hibernate.Query query = getCurrentSession().createQuery("select u from Utente u where u.maritimo=false or u.maritimo=true");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> findUtentesMaritimos() {
		Query query = getCurrentSession().createQuery("select u from Utente u join fetch u.categoria_maritimo cm where u.maritimo=true");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Utente> findAllByMaritimoOuUtente (String nome){
		org.hibernate.Query query = getCurrentSession().createQuery("select u from Utente u where u.nome like :nome");
		
		query.setParameter("nome", "%"+nome+"%");
		return query.list();

	}

}
