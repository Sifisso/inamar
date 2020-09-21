package mz.ciuem.inamar.dao;

import mz.ciuem.inamar.entity.Role;

public interface RoleDao extends GenericDao<Role> {

	public Role findByName(String role);
}
