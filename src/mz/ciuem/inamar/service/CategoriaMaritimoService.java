package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;

public interface CategoriaMaritimoService extends GenericService<CategoriaMaritimo>{
	
	public List<CategoriaMaritimo> findByClasseMaritimo(ClasseMaritimo classeMaritimo);

}
