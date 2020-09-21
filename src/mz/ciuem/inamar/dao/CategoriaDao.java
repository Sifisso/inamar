package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;

public interface CategoriaDao extends GenericDao<Categoria>{
	
	public List<Categoria> findByNomeActivo(String nome, boolean isActivo);
	
	public List<Categoria> findByNotInPedido(Area area);

}
