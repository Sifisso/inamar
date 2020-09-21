package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;

public interface ProvinciaService extends GenericService<Provincia> {

	public List<String> buscarNomeDeTodasProvincias();
	public List<Provincia> buscarTodasProvinciasDeUmPais(Pais pais);
	

}
