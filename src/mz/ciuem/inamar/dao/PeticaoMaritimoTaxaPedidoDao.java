package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;

public interface PeticaoMaritimoTaxaPedidoDao extends GenericDao<PeticaoMaritimoTaxaPedido>{
	
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo);
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoEmbarcacao(PeticaoEmbarcacao peticaoEmbarcacao);
	public List<PeticaoMaritimoTaxaPedido> findByPeticao(Peticao peticao);
	public List<PeticaoMaritimoTaxaPedido> findByTaxaPedido(Pedido pedido);
	public List<PeticaoMaritimoTaxaPedido> findByTaxaPedidos();

}
