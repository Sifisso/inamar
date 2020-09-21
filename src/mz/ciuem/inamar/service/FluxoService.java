package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.TipoPedido;

public interface FluxoService extends GenericService<Fluxo>{
	
	public List<Fluxo> findByNomeIsActivo(String nome, boolean isActivo);
	public List<Fluxo> finByNotInPedido(TipoPedido tipoPedido);


}
