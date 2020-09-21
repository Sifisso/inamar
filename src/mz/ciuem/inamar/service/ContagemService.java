package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Pedido;

public interface ContagemService extends GenericService<Contagem>{

	public List<Contagem> findNotInPedido(Pedido pedido);

}
