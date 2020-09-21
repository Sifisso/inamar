package mz.ciuem.inamar.dao.imlp;

import javax.persistence.Query;

import mz.ciuem.inamar.dao.RoleDao;
import mz.ciuem.inamar.entity.Role;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements
		RoleDao {
	

	@SuppressWarnings("unchecked")
	@Override
	public Role findByName(String role) {
		
		Query query = em
				.createQuery("select r from Role r where r.designation = ?");
		query.setParameter(1, role);

		return (Role) DataAccessUtils.singleResult(query.getResultList());
	}

}
