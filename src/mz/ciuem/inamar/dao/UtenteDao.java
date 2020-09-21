package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;

public interface UtenteDao extends GenericDao<Utente>{
	
	public Utente buscarUtenteByUser(User user);
	public List<Utente> findUtentesNotMaritimos();
	public List<Utente> findUtentesMaritimos();
	public List<Utente> findAllByMaritimoOuUtente(String nome, boolean isEmpresa);

}
