package mz.ciuem.service.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.PermissionService;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.test.config.GenericTestUnit;

import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleTest extends GenericTestUnit {

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PermissionService permissionService;

	@SuppressWarnings("unchecked")
	public UserRole criarUserRole(int num) {

		Date c = Calendar.getInstance().getTime();
		UserRole userRole = new UserRole();

		userRole.setUpdated(c);
		userRole.setRolename("Normal");
		userRole.setUpdated(c);
		Set<Permission> all = (Set<Permission>) permissionService.getAll();
		userRole.setPermissions(all);
		return userRoleService.create(userRole);

	}

}
