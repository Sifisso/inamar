package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Calendario;

public interface CalendarioDao extends GenericDao<Calendario>{
	
	public List<Calendario> buscarCalendarioPorCandidato(String cod);

}
