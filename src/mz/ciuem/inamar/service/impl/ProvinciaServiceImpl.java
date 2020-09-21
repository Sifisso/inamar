package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.ProvinciaDao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.service.ProvinciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("provinciaService")
public class ProvinciaServiceImpl extends GenericServiceImpl<Provincia>
		implements ProvinciaService {

	@Autowired
	private ProvinciaDao provinciaDao;

	public List<String> buscarNomeDeTodasProvincias() {
		return provinciaDao.buscarNomeDeTodasProvincias();
	}

	@Override
	public List<Provincia> buscarTodasProvinciasDeUmPais(Pais pais) {
		return provinciaDao.buscarTodasProvinciasDeUmPais(pais);
	}

}
