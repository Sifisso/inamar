package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.UserRole;

public interface UserRoleDao extends GenericDao<UserRole> {

	public UserRole findByName(String rollname);

	public UserRole buscarRoleCandidato(String rolename);
	
	public List<UserRole> buscarRoleNaoAdminCandidato();

}
