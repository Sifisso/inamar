package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Tarefa;

public interface TarefaService extends GenericService<Tarefa>{
	
	public List<Tarefa> findByNomeIsActivo(String nome, boolean isActivo);
	public List<Tarefa> findNotInPedido(Pedido pedido);
	public List<Tarefa> findNotInEtapaFluxo(EtapaFluxo etapaFluxo);

}
