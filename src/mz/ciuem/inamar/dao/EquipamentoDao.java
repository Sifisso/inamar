package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Equipamento;
import mz.ciuem.inamar.entity.Pedido;

public interface EquipamentoDao extends GenericDao<Equipamento>{
	
	List<Equipamento> findNotInPedido(Pedido pedido);

}
