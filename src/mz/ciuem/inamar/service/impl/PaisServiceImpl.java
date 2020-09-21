package mz.ciuem.inamar.service.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mz.ciuem.inamar.dao.PaisDao;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.service.PaisService;




@Service("paisService")
public class PaisServiceImpl extends GenericServiceImpl<Pais> implements PaisService {
	
	@Autowired
	private PaisDao paisDao;

	@Override
	public List<Pais> listarPaises() {
		
		return paisDao.listarPaises();
	}

	@Override
	public List<Distrito> listarDistritos(Provincia provincia) {
		return paisDao.listarDistritos(provincia);
	}
	
	@Override
	public List<Pais> findByNomePais (String designacao){
		return paisDao.findByNomePais(designacao);
	}

	

	
	


}
