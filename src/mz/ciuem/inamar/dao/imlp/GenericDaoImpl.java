package mz.ciuem.inamar.dao.imlp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mz.ciuem.inamar.dao.GenericDao;
import mz.ciuem.inamar.entity.IdEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImpl<T extends IdEntity> implements
		GenericDao<T> {

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@PersistenceContext
	protected EntityManager em;

	public long count() {
		String entity = type.getSimpleName();
		final StringBuffer queryString = new StringBuffer(
				"select count(ent) from " + entity + " ent");
		final Query query = this.em.createQuery(queryString.toString());

		return (Long) query.getSingleResult();
	}

	public T create(final T t) {
		em.persist(t);
		return t;
	}

	public void delete(final T t) {
		getCurrentSession().delete(t);
	}

	public T find(final Object id) {
		return em.find(type, id);
	}

	public T update(final T t) {
		return em.merge(t);
	}
	
	public void  saveOrUpdate(T t){
		 getCurrentSession().saveOrUpdate(t);
		
	}
	

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Query query = em.createQuery("from " + type.getName());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T last() {
		Query query = em.createQuery("from " + type.getName()
				+ " order by created desc");
		return (T) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	public T first() {
		Query query = em.createQuery("from " + type.getName()
				+ " order by created asc");
		return (T) query.getResultList().get(0);
	}

}