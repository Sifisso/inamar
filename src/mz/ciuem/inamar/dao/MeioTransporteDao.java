package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.MeioTransporte;
import mz.ciuem.inamar.entity.Pedido;

public interface MeioTransporteDao extends GenericDao<MeioTransporte>{
	
	public List<MeioTransporte> findNotInPedido(Pedido pedido);

}
