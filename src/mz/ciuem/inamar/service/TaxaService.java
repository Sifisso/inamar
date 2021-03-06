package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.Taxa;

public interface TaxaService extends GenericService<Taxa>{
	
	public List<Taxa> findByNomeActivo(String nome, boolean isActivo);
	public List<Taxa> finBySubArea(SubArea subArea);
	public List<Taxa> findNotInPedido(Pedido pedido);
	public List<Taxa> findNotInPedidoInSubSrea(SubArea _subArea, Pedido _pedido);

}
