package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoCategoriaMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimo;

public interface PeticaoCategoriaMaritimoDao extends GenericDao<PeticaoCategoriaMaritimo>{
	
	public PeticaoCategoriaMaritimo findByPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo);

}
