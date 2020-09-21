package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.User;

public interface UserDao extends GenericDao<User> {

	public User getUser(String login);
	
	public List<User> retornarUserNaoCandidato();
	public boolean emailExiste(String email);
	public List<User> procurarUser(String username);
	public User buscarUser(String login);
}
