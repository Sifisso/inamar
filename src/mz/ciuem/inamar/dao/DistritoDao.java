package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Provincia;

public interface DistritoDao extends GenericDao<Distrito> {
	
	public List<Distrito> buscarDistritosDeUmaProvincia(Provincia provincia);
	

	
	public List<Object[]> buscarTodosDoDistritos();
	



}
