package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.TipoPedido;

public interface TipoPedidoService extends GenericService<TipoPedido>{
	
	public List<TipoPedido> findByArea (Area area);
	public List<TipoPedido> findByNomeCategoriaAreaIsActivo(String nome, Area area, boolean isActivo);


}
