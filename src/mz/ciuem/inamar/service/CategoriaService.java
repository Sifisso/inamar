package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;

public interface CategoriaService extends GenericService<Categoria>{
	
	public List<Categoria> findByNomeActivo(String nome, boolean isActivo);
	
	public List<Categoria> findByNotInPedido(Area area);

}
