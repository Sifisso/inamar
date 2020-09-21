package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.inamar.dao.UserDao;
import mz.ciuem.inamar.entity.User;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String login) {
		Query query = em.createQuery("SELECT u FROM User u where u.username = ?");
		query.setParameter(1, login);
		return (User) DataAccessUtils.singleResult(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> retornarUserNaoCandidato() {
		org.hibernate.Query query = getCurrentSession().createQuery("select u from User u  join u.roles ur ");
		return query.list();
	}

	@Override
	public  boolean emailExiste(String email) {
		
		org.hibernate.Query query = getCurrentSession().createQuery("select u from User u where u.username=:email");
		query.setParameter("email", email);
		User user = (User)query.uniqueResult();
		if(user==null){
			return false;
		}else{
			return true;
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> procurarUser(String username) {
		org.hibernate.Query query = getCurrentSession().createQuery("select u from User u  where u.username like :username");
		query.setParameter("username", "%"+username+"%");
		query.setMaxResults(20);
		return query.list();
	}
	
	@Override
	public User buscarUser(String login) {
		Query query = em.createQuery("select u from User u where u.username =:username");
		query.setParameter("username", login);
		return (User) DataAccessUtils.singleResult(query.getResultList());
	}

}
