package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.UserRoleDao;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl extends GenericServiceImpl<UserRole> implements
		UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserRole findByName(String rollname) {
		return userRoleDao.findByName(rollname);
	}

	@Override
	public UserRole buscarRoleCandidato(String rolename) {
		return userRoleDao.buscarRoleCandidato(rolename);
	}

	@Override
	public List<UserRole> buscarRoleNaoAdminCandidato() {
		return userRoleDao.buscarRoleNaoAdminCandidato();
	}
	
	

}
