package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.UserRoleDao;
import mz.ciuem.inamar.entity.UserRole;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole> implements
		UserRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getAll() {
		Query query = getCurrentSession().createQuery("from UserRole ur where ur.type = 'Normal'");
		return query.list();
	}

	@Override
	public UserRole findByName(String rolename) {
		
		Query query = getCurrentSession().createQuery("from UserRole ur where ur.rolename = ?");
		query.setParameter(1, rolename);
		return (UserRole) query.uniqueResult();
	}

	public UserRole buscarRoleCandidato(String rolename) {
		
		Query query = getCurrentSession().createQuery("select ur from UserRole ur where ur.rolename = :rolename");
		query.setParameter("rolename", rolename);
		return (UserRole) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> buscarRoleNaoAdminCandidato() {
		Query query = getCurrentSession().createQuery("select ur from UserRole ur where ur.rolename <> 'Admin' and ur.rolename <> 'Candidato'");
		return query.list();
	}
}
