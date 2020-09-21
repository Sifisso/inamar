package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Rota;

public interface RotaService extends GenericService<Rota>{
	
	public List<Rota> findNotInPedido(Pedido pedido);

}
