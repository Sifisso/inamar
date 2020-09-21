package mz.ciuem.inamar.service.impl;

import mz.ciuem.inamar.dao.RoleDao;
import mz.ciuem.inamar.entity.Role;
import mz.ciuem.inamar.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role>
		implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findByName(String role) {
		// TODO Auto-generated method stub
		return roleDao.findByName(role);
	}

}
