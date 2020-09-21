package mz.ciuem.inamar.dao;


import java.util.List;

import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;

public interface ProvinciaDao extends GenericDao<Provincia> {

	public List<String> buscarNomeDeTodasProvincias();
	public List<Provincia> buscarTodasProvinciasDeUmPais(Pais pais);

}
