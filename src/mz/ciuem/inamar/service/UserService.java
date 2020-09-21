package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.User;

public interface UserService extends GenericService<User> {

	public User getUser(String login);
	public String redifinirSenha(User user);
	public List<User> retornarUserNaoCandidato();
	public boolean emailExiste(String email);
	public List<User> procurarUser(String username);
	public User buscarUser(String login);
	public String encriptarSenha(String senha);
}
