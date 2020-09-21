package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TarefaNaEtapa;

public interface TarefaNaEtapaDao extends GenericDao<TarefaNaEtapa>{
	
	public List<TarefaNaEtapa> findByPedido(Pedido pedido);
	public List<TarefaNaEtapa> findByEtapaFluxo(EtapaFluxo etapaFluxo);
	
}
