package mz.ciuem.inamar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mz.ciuem.inamar.dao.UserDao;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.service.UserService;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		return getUser(username);
	}

	@Override
	public User getUser(String login) {
		return userDao.getUser(login);
	}

	@Override
	public User create(User u) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(u.getPassword());
		u.setPassword(hashedPassword);

		User user = userDao.create(u);

		return user;
	}

	public String redifinirSenha(User user) {
		user.setPassword(gerarSenhaAleatoria());
		userDao.update(user);
		return user.getPassword();
				
	}

	private static String gerarSenhaAleatoria() {
		int max = 8;
		String[] caracteres = { "a", "1", "b", "2", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < max; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}
		
		return senha.toString();
	}
	
	@Override
	public String encriptarSenha(String senha){

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(senha);
		
		return hashedPassword;
	}

	@Override
	public List<User> retornarUserNaoCandidato() {
		return userDao.retornarUserNaoCandidato();
	}
	@Override
	public boolean emailExiste(String email) {
		// TODO Auto-generated method stub
		return userDao.emailExiste(email);
	}

	@Override
	public List<User> procurarUser(String username) {
		// TODO Auto-generated method stub
		return userDao.procurarUser(username);
	}
	
	public User buscarUser(String login){
		return userDao.buscarUser(login);
	}

}
