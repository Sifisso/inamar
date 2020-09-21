package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.Curso;
import mz.ciuem.inamar.entity.Pedido;

public interface CursoService extends GenericService<Curso>{

	 public List<Curso> findNotInPedido(Pedido pedido);

}
