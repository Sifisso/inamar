package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TarefaNaEtapa;

public interface TarefaNaEtapaService extends GenericService<TarefaNaEtapa>{
	
	public List<TarefaNaEtapa> findByPedido(Pedido pedido);
	public List<TarefaNaEtapa> findByEtapaFluxo(EtapaFluxo etapaFluxo);
}
