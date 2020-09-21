package mz.ciuem.inamar.service;

import mz.ciuem.inamar.entity.Role;

public interface RoleService extends GenericService<Role> {

	public Role findByName(String role);
}
