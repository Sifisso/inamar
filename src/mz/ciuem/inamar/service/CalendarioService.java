package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Calendario;

public interface CalendarioService extends GenericService<Calendario>{

	public List<Calendario> buscarCalendarioPorCandidato(String cod);
	
}
