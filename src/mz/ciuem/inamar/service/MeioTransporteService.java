package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.MeioTransporte;
import mz.ciuem.inamar.entity.Pedido;

public interface MeioTransporteService extends GenericService<MeioTransporte>{

	public List<MeioTransporte> findNotInPedido(Pedido pedido);

}
