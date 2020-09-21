package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Provincia;

public interface DistritoService extends GenericService<Distrito> {

	public List<Distrito> buscarDistritosDeUmaProvincia(Provincia provincia);

	
	
	public List<Object[]> buscarTodosDoDistritos();




}
