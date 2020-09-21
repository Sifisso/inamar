package mz.ciuem.inamar.service;

import mz.ciuem.inamar.entity.Permission;

public interface PermissionService extends GenericService<Permission> {

	public Permission getPermission(String usersPermission);
	public Permission buscarPermissoesPeloNome(String param);
}
