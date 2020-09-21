package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Pedido;

public interface ContagemDao extends GenericDao<Contagem>{
    
	public List<Contagem> findNotInPedido(Pedido pedido);
}
