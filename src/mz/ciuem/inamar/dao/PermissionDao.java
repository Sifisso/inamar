package mz.ciuem.inamar.dao;

import mz.ciuem.inamar.entity.Permission;

public interface PermissionDao extends GenericDao<Permission> {

	public Permission getPermission(String usersPermission);
	public  Permission buscarPermissoesPeloNome(String param);
	
}
