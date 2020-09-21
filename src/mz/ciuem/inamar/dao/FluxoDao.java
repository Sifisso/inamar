package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.TipoPedido;

public interface FluxoDao extends GenericDao<Fluxo>{
	
	public List<Fluxo> findByNomeIsActivo(String nome, boolean isActivo);
	public List<Fluxo> finByNotInPedido(TipoPedido tipoPedido);

}
