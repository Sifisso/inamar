package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Entrada;

public interface EntradaDao extends GenericDao<Entrada>{

	public List<Entrada> findByNome (String embarcacao);
	public List<Entrada> findByNomeEmb (String embarcacao);
}
