package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoMaritimoTaxaPedidoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PeticaoMaritimoTaxaPedidoDaoImpl extends GenericDaoImpl<PeticaoMaritimoTaxaPedido> implements PeticaoMaritimoTaxaPedidoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo) {
		Query query = getCurrentSession().createQuery("select distinct pmtp from PeticaoMaritimoTaxaPedido pmtp join fetch pmtp.peticao pet join fetch pmtp.taxaPedido tp join fetch tp.taxa t join fetch pet.peticaoMaritimo pm where pm=:peticaoMaritimo");
		query.setParameter("peticaoMaritimo", peticaoMaritimo);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoMaritimoTaxaPedido> findByPeticao(Peticao peticao) {
		Query query = getCurrentSession().createQuery("select distinct pmtp from PeticaoMaritimoTaxaPedido pmtp join fetch pmtp.peticao pet join fetch pmtp.taxaPedido tp join fetch tp.taxa t where pmtp.peticao=:peticao");
		query.setParameter("peticao", peticao);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoMaritimoTaxaPedido> findByTaxaPedido(Pedido pedido) {
		Query query = getCurrentSession().createQuery("select distinct pmtp from PeticaoMaritimoTaxaPedido pmtp "
				+ " join fetch pmtp.taxaPedido tp "
				+ " join fetch tp.pedido p "
				+ " left join fetch tp.taxa t "
				+ " ");
		//query.setParameter("pedido", pedido);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoMaritimoTaxaPedido> findByTaxaPedidos() {
		Query query = getCurrentSession().createQuery("select distinct pmtp from PeticaoMaritimoTaxaPedido pmtp "
				+ " join fetch pmtp.taxaPedido tp "
				+ " left join fetch tp.taxa t "
				+ " join fetch tp.pedido p");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PeticaoMaritimoTaxaPedido> findByPeticaoEmbarcacao(PeticaoEmbarcacao peticaoEmbarcacao) {
		Query query = getCurrentSession().createQuery("select distinct pmtp from PeticaoMaritimoTaxaPedido pmtp join fetch pmtp.peticao pet  join fetch pmtp.taxaPedido tp join fetch tp.taxa t join fetch pet.peticaoEmbarcacao pm where pm=:peticaoEmbarcacao");
		query.setParameter("peticaoEmbarcacao", peticaoEmbarcacao);
		return query.list();
	}

}
