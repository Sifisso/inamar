package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;

public interface PaisService extends GenericService<Pais> {

	List<Pais> listarPaises();
	List<Distrito> listarDistritos(Provincia provincia);
	
	public List<Pais> findByNomePais(String designacao);
	

}
