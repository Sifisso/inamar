package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Rota;

public interface RotaDao extends GenericDao<Rota>{

	public List<Rota> findNotInPedido(Pedido pedido);
}
