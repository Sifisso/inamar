package mz.ciuem.inamar.dao.parametrizacao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.inamar.dao.DistritoDao;
import mz.ciuem.inamar.dao.ProvinciaDao;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.test.config.GenericTestUnit;

public class DistritoDaoTest extends GenericTestUnit {

	@Autowired
	private DistritoDao distritoDao;

	@Autowired
	private ProvinciaDao provinciaDao;

	@Test
	public void deveBuscarDistritoDeUmaProvincia() {

		Distrito distrito = new Distrito();
		Distrito distrito2 = new Distrito();
		Distrito distrito3 = new Distrito();
		Provincia provincia = new Provincia();
		provincia.setDesignacao("Manica");

		distrito.setProvincia(provincia);
		distrito2.setProvincia(provincia);
		distrito3.setProvincia(provincia);

		provinciaDao.saveOrUpdate(provincia);
		distritoDao.saveOrUpdate(distrito);
		distritoDao.saveOrUpdate(distrito2);
		distritoDao.saveOrUpdate(distrito3);

		List<Distrito> distritos = distritoDao.buscarDistritosDeUmaProvincia(provincia);

		Assert.assertEquals(3, distritos.size());
		
		for (Distrito distrito4 : distritos) {
			Assert.assertTrue(distritos.contains(distrito4));
			
		}
		
		distritoDao.delete(distrito3);
		distritoDao.delete(distrito2);
		distritoDao.delete(distrito);
		provinciaDao.delete(provincia);

	}
	
	

}
