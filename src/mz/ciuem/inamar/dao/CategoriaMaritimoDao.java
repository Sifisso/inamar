package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;

public interface CategoriaMaritimoDao extends GenericDao<CategoriaMaritimo>{
	
	public List<CategoriaMaritimo> findByClasseMaritimo(ClasseMaritimo classeMaritimo);

}
