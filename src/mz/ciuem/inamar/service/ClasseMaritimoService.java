package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;

public interface ClasseMaritimoService extends GenericService<ClasseMaritimo>{
	
	public List<ClasseMaritimo> finByNomeActivo(String nome, boolean isActivo);
	public List<ClasseMaritimo> findByGrupoMaritimo(GrupoMaritimo grupoMaritimo);

}
