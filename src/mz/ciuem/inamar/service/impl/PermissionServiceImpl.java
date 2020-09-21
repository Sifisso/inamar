package mz.ciuem.inamar.service.impl;

import mz.ciuem.inamar.dao.PermissionDao;
import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionServiceImpl extends GenericServiceImpl<Permission>
		implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public Permission getPermission(String usersPermission) {
		// TODO Auto-generated method stub
		return permissionDao.getPermission(usersPermission);
	}

	public Permission buscarPermissoesPeloNome(String param) {
		return permissionDao.buscarPermissoesPeloNome(param);
	}

}
