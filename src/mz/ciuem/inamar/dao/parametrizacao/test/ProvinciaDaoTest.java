package mz.ciuem.inamar.dao.parametrizacao.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mz.ciuem.inamar.dao.PaisDao;
import mz.ciuem.inamar.dao.ProvinciaDao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.test.config.GenericTestUnit;

public class ProvinciaDaoTest extends GenericTestUnit {

	@Autowired
	private PaisDao paisDao;

	@Autowired
	private ProvinciaDao provinciaDao;

	@Test
	public void deveBuscarTodasProvinciasDeUmPais() {

		Pais mocambique = new Pais();
		mocambique.setDesignacao("Mocambique");

		Provincia maputo = new Provincia();
		maputo.setDesignacao("Maputo");
		maputo.setPais(mocambique);

		Provincia beira = new Provincia();
		beira.setDesignacao("Beira");
		beira.setPais(mocambique);
		mocambique.setProvincias(Arrays.asList(maputo, beira));

		paisDao.saveOrUpdate(mocambique);
		provinciaDao.saveOrUpdate(maputo);
		provinciaDao.saveOrUpdate(beira);
		List<Provincia> provincias = provinciaDao.buscarTodasProvinciasDeUmPais(mocambique);
		Assert.assertEquals(2, provincias.size());

		paisDao.delete(mocambique);
		provinciaDao.delete(beira);
		provinciaDao.delete(maputo);

	}

	@Test
	public void deveBuscarNomeDeTodasProvincias() {

		Provincia maputo = new Provincia();
		maputo.setDesignacao("Nairobe");
		Provincia sofala = new Provincia();
		sofala.setDesignacao("Zamzibar");

		provinciaDao.saveOrUpdate(maputo);
		provinciaDao.saveOrUpdate(sofala);

		List<String> provincias = provinciaDao.buscarNomeDeTodasProvincias();
		Assert.assertTrue(provincias.contains("Maputo"));
		Assert.assertTrue(provincias.contains("Sofala"));

		provinciaDao.delete(maputo);
		provinciaDao.delete(sofala);

	}

	@Test
	public void naoDeveTernomesDuplicados() {

		Provincia maputo1 = new Provincia();
		maputo1.setDesignacao("Maputo");
		Provincia maputo2 = new Provincia();
		maputo2.setDesignacao("Maputo");

		provinciaDao.saveOrUpdate(maputo1);
		provinciaDao.saveOrUpdate(maputo2);

		List<String> provincias = provinciaDao.buscarNomeDeTodasProvincias();

		Assert.assertTrue(provincias.lastIndexOf("Maputo") == 0);

		provinciaDao.delete(maputo1);
		provinciaDao.delete(maputo2);

	}

}
