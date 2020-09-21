package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.UtenteDao;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("utenteService")
public class UtenteServiceImpl extends GenericServiceImpl<Utente> implements UtenteService{

	@Autowired
	private UtenteDao _uDao;
	
	@Override
	public Utente buscarUtenteByUser(User user) {
		return _uDao.buscarUtenteByUser(user);
	}

	@Override
	public List<Utente> findUtentesNotMaritimos() {
		return _uDao.findUtentesNotMaritimos();
	}

	@Override
	public List<Utente> findUtentesMaritimos() {
		return _uDao.findUtentesMaritimos();
	}
	
	@Override
	public List<Utente> findAllByMaritimoOuUtente(String nome, boolean maritimo){
		return _uDao.findAllByMaritimoOuUtente(nome, maritimo);
	}

}
