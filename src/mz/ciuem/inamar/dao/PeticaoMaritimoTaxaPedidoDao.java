package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;

public interface PeticaoMaritimoTaxaPedidoDao extends GenericDao<PeticaoMaritimoTaxaPedido>{
	
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo);
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoEmbarcacao(PeticaoEmbarcacao peticaoEmbarcacao);

}
