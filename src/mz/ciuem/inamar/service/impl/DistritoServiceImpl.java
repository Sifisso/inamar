package mz.ciuem.inamar.service.impl;

import java.util.List;

import mz.ciuem.inamar.dao.DistritoDao;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.service.DistritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("distritoService")
public class DistritoServiceImpl extends GenericServiceImpl<Distrito>implements DistritoService {
	
	@Autowired
	private DistritoDao distritoDao;

	public List<Distrito> buscarDistritosDeUmaProvincia(Provincia provincia) {
		return distritoDao.buscarDistritosDeUmaProvincia(provincia);
	}

	
	
	public List<Object[]> buscarTodosDoDistritos(){
		return distritoDao.buscarTodosDoDistritos();
	}

}
