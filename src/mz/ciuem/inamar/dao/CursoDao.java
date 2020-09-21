package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Curso;
import mz.ciuem.inamar.entity.Pedido;

public interface CursoDao extends GenericDao<Curso>{

	 public List<Curso> findNotInPedido(Pedido pedido);
}
