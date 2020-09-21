package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;

public interface ClasseMaritimoDao extends GenericDao<ClasseMaritimo>{
	
	public List<ClasseMaritimo> finByNomeActivo(String nome, boolean isActivo);
	public List<ClasseMaritimo> findByGrupoMaritimo(GrupoMaritimo grupoMaritimo);

}
