package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Equipamento;
import mz.ciuem.inamar.entity.Pedido;

public interface EquipamentoService extends GenericService<Equipamento>{

	List<Equipamento> findNotInPedido(Pedido pedido);
}
