package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.UserRole;


public interface UserRoleService extends GenericService<UserRole> {

	public UserRole findByName(String rollname);

	public UserRole buscarRoleCandidato(String rolename);
	
	public List<UserRole> buscarRoleNaoAdminCandidato();

}
