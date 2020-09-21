package mz.ciuem.inamar.service;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoCategoriaMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimo;

public interface PeticaoCategoriaMaritimoService extends GenericService<PeticaoCategoriaMaritimo>{

	public PeticaoCategoriaMaritimo findByPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo);
	
}
