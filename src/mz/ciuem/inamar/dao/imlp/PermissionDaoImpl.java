package mz.ciuem.inamar.dao.imlp;

import javax.persistence.Query;

import mz.ciuem.inamar.dao.PermissionDao;
import mz.ciuem.inamar.entity.Permission;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements
		PermissionDao {

	@SuppressWarnings("unchecked")
	@Override
	public Permission getPermission(String usersPermission) {

		Query query = em
				.createQuery("select p from Permission p where p.permissionname = ?");
		query.setParameter(1, usersPermission);
		
		return (Permission) DataAccessUtils.singleResult(query.getResultList());
	}

	@Override
	public Permission buscarPermissoesPeloNome(String param) {
		Query query = em
				.createQuery("select p from Permission p where p.permissionname = :param");
		query.setParameter("param", "GESTÃO_DE_VALIDACÃO_DE_PRE_REGISTO");
		return (Permission) query.getSingleResult();

}
	}
