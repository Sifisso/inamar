package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Entrada;

public interface EntradaService extends GenericService<Entrada>{

	public List<Entrada> findByNome (String embarcacao);
	public List<Entrada> findByNomeEmb (String embarcacao);
}
