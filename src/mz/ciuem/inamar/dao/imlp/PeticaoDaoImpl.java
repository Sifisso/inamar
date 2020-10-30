package mz.ciuem.inamar.dao.imlp;

import java.util.List;

import mz.ciuem.inamar.dao.PeticaoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.User;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.zkoss.zul.Messagebox;

@Repository
public class PeticaoDaoImpl extends GenericDaoImpl<Peticao> implements PeticaoDao{

	//Utente
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> getbyUser(User user) {
		Query query = getCurrentSession().createQuery("select p from Peticao p join fetch p.user u LEFT JOIN FETCH p.pedido pedi where u=:user order by p.updated desc");
		query.setParameter("user", user);
	    return query.list();
	}

	//Tesoureiro
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarValidadas() {
		Query query = getCurrentSession().createQuery("select p from Peticao p left join p.pagamento pag  LEFT JOIN FETCH p.pedido pedi where p.isValidado=true and p.pedeParecer=false order by p.updated desc");
	    return query.list();
	}
	
	

	//Administrador Maritimo
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarValidadasOuPagas() {
		Query query = getCurrentSession().createQuery("select p from Peticao p left join p.pagamento pag LEFT  JOIN FETCH p.pedido pedi where p.pedeParecer=false and p.isValidado=true OR p.pago=true order by p.updated desc");
	    return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarParaChefeSecretaria() {
		Query query = getCurrentSession().createQuery("select p from Peticao p left join p.pagamento pag LEFT JOIN FETCH p.pedido pedi where p.isPreValidado=true or p.chefeSecretaria=true order by p.updated desc");
	    return query.list();
	}

	@Override
	public List<Peticao> buscarParaAdministradorMaritimo2() {
	    return null;
	}

	@Override
	public List<Peticao> buscarParaAdministradorMaritimo3() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> seccaoTecnica() {
		Query query = getCurrentSession().createQuery("select p from Peticao p LEFT  JOIN FETCH p.pedido pedi where p.seccaoTecnica=true order by p.updated desc");
	    return query.list();
	}
	
	@Override
	public Peticao findEgerPedido(Peticao peticao) {
		Query query = getCurrentSession().createQuery("select p from Peticao p LEFT  join fetch p.pedido ped where p=:peticao");
		query.setParameter("peticao", peticao);
	    return (Peticao) query.uniqueResult();
	}

	@Override
	public List<Peticao> seccaoTecnica2() {
		return null;
	}

	@Override
	public List<Peticao> secretaria() {
		return null;
	}

	@Override
	public List<Peticao> secretaria2() {
		return null;
	}

	@Override
	public List<Peticao> utente() {
		return null;
	}

	@Override
	public List<Peticao> utente2() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> getAllOrderDesc() {
		Query query = getCurrentSession().createQuery("select p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "  left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "");
	    return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarPeticoesPorDelegacao(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select distinct p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "join fetch p.userLoggado us "
				+ "join fetch us.funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where del=:delegacao order by p.updated desc");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Peticao> buscarPeticoesPorDelegacaoTesouraria(Delegacao
	 * delegacao) { Query query =
	 * getCurrentSession().createQuery("select distinct p from Peticao p " +
	 * "left join fetch p.pedido ped " + "left join fetch ped. taxasPedido tp " +
	 * "left  join fetch tp.taxa t " + "join fetch p.userLoggado us " +
	 * "join fetch us.funcionario f " + "left JOIN FETCH f.sector s " +
	 * "left join fetch s.delegacaoDepartamento dd " +
	 * "left join fetch dd.delegacao del " +
	 * "  where del=:delegacao and p.isValidado=true and p.pedeParecer=false and p.tesouraria=true and p.admMaritima2=false or p.pago=true order by p.updated desc"
	 * ); query.setParameter("delegacao", delegacao); return query.list(); }
	 */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoTesouraria(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select distinct p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "join fetch p.userLoggado us "
				+ "join fetch us.funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where del=:delegacao and p.tesouraria=true order by p.updated desc");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoSeccaoTecnica(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select distinct p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "join fetch p.userLoggado us "
				+ "join fetch us.funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where del=:delegacao and p.seccaoTecnica=true order by p.updated desc");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarTesouraria() {
		Query query = getCurrentSession().createQuery("select p from Peticao p "
				+ "left join p.pagamento pag  "
				+ "LEFT JOIN FETCH p.pedido pedi "
				+ "where p.isValidado=true and p.pedeParecer=false and p.tesouraria=true order by p.updated desc");
	    return query.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarParaAdministradorMaritimo() {
		Query query = getCurrentSession().createQuery("select p from Peticao p "
				+ "LEFT  JOIN FETCH p.pedido pedi "
				+ "where p.admMaritima=true or p.admMaritima2=true order by p.updated desc");
	    return query.list();
	}
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Peticao>
	 * buscarPeticoesPorDelegacaoAdmMaritimo(Delegacao delegacao) { Query query =
	 * getCurrentSession().createQuery("select distinct p from Peticao p " +
	 * "left join fetch p.pedido ped " + "left join fetch ped. taxasPedido tp " +
	 * "left  join fetch tp.taxa t " + "join fetch p.userLoggado us " +
	 * "join fetch us.funcionario f " + "left JOIN FETCH f.sector s " +
	 * "left join fetch s.delegacaoDepartamento dd " +
	 * "left join fetch dd.delegacao del " +
	 * "  where del=:delegacao and p.admMaritima=true or p.admMaritima2=true or p.tesouraria=true or p.terminada=true or p.tratada=true order by p.updated desc"
	 * ); query.setParameter("delegacao", delegacao); return query.list(); }
	 */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoAdmMaritimo(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select distinct p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "join fetch p.userLoggado us "
				+ "join fetch us.funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where del=:delegacao and p.admMaritima=true order by p.updated desc");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Object[]> getPeticaoDelegacao() {
		Query query = getCurrentSession().createQuery("SELECT del.nome, ar.nome, "
				+ " COUNT(p.id)"
				+ "FROM Peticao p "
				+ "left join p.pedido ped left "
				+ "join ped.tipoPedido tipPed "
				+ "join tipPed.area ar "
				+ "join p.userLoggado us "
				+ "join us.funcionario f left JOIN"
				+ " f.sector s "
				+ "join s.delegacaoDepartamento dd "
				+ "join dd.delegacao del "
				+ "group by del.nome, ar.nome");
		
		List<Object[]> lista = query.list();
		

			return lista = query.list();
		}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Object[]> getPeticaoDelegacaoDesempenhoProcessual() {
		Query query = getCurrentSession().createQuery("select p.delegacao.nome, "
				+ "(SELECT COUNT(pet.id) FROM Peticao pet join pet.delegacao d where pet.delegacao.id=d.id and d.id=p.delegacao.id), "
				+ "(SELECT COUNT(pet.id) FROM Peticao pet join pet.delegacao d where pet.pago=true and pet.delegacao.id=d.id and d.id=p.delegacao.id), "
				+ "(SELECT COUNT(pet.id) FROM Peticao pet join pet.delegacao d where pet.pago=false and pet.delegacao.id=d.id and d.id=p.delegacao.id) "
				+ "from Peticao p group by p.delegacao.id");
		
		List<Object[]> lista = query.list();
		

			return lista = query.list();
		}
	
	
	/*@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Object[]> getPeticaoDelegacao() {
		Query query = getCurrentSession().createQuery("SELECT p.delegacao.nome, COUNT(p.id), "
				+ "(SELECT ar.nome FROM Peticao pet left join pet.pedido ped join ped.tipoPedido tp join tp.area ar join pet.userLoggado us join us.funcionario f left JOIN f.sector s join s.delegacaoDepartamento dd join dd.delegacao del), "
				+ "(SELECT COUNT(pet.id) FROM Peticao pet left join pet.pedido ped join ped.tipoPedido tp join tp.area ar join pet.userLoggado us join us.funcionario f left JOIN f.sector s join s.delegacaoDepartamento dd join dd.delegacao del where pet.pago=true), "
				+ "(SELECT COUNT(pet.id) FROM Peticao pet left join pet.pedido ped join ped.tipoPedido tp join tp.area ar join pet.userLoggado us join us.funcionario f left JOIN f.sector s join s.delegacaoDepartamento dd join dd.delegacao del where pet.terminada=true) "
				+ "FROM Peticao p join p.pedido pedi join pedi.tipoPedido tipPed join tipPed.area area group by p.delegacao.nome, p.pedido.tipoPedido.area.nome, p.delegacao, p.pedido.tipoPedido.area");
		
		List<Object[]> lista = query.list();
		

			return lista = query.list();
		}
		
	*/
	
	/*@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Object[]> getPeticaoDelegacao() {
		Query query = getCurrentSession().createQuery("select p.delegacao.nome, p.pedido.tipoPedido.area.nome, COUNT(p.id), " 
				+ "(SELECT ar.nome from Peticao pet left join pet.pedido ped join ped.tipoPedido tp join tp.area ar join pet.delegacao del), "
				+ "(SELECT COUNT(pet.id) from Peticao pet left join pet.pedido ped join ped.tipoPedido tp join tp.area ar join pet.delegacao del where pet.pago=true), "
				+ "(SELECT COUNT(pet.id) from Peticao pet left join pet.pedido ped join ped.tipoPedido tp join tp.area ar join pet.delegacao del where pet.terminada=true) "
				+ "FROM Peticao p group by p.delegacao.nome, p.pedido.tipoPedido.area.nome ");
		
		List<Object[]> lista = query.list();
		

			return lista = query.list();
		}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoEmbarcacao(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "join ped.tipoPedido tipPed "
				+ "join tipPed.area ar "
				+ "left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "join fetch p.userLoggado us "
				+ "join fetch us.funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where del=:delegacao and ar.nome like 'Embarcaçoes' and p.isValidado=true and p.isPreValidado=false or del=:delegacao and ar.nome like 'Embarcaçoes' and p.isValidado=false and p.isPreValidado=true ");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoMaritimos(Delegacao delegacao) {
		Query query = getCurrentSession().createQuery("select p from Peticao p "
				+ "left join fetch p.pedido ped "
				+ "join ped.tipoPedido tipPed "
				+ "join tipPed.area ar "
				+ "left join fetch ped. taxasPedido tp "
				+ "left  join fetch tp.taxa t "
				+ "join fetch p.userLoggado us "
				+ "join fetch us.funcionario f "
				+ "left JOIN FETCH f.sector s "
				+ "left join fetch s.delegacaoDepartamento dd "
				+ "left join fetch dd.delegacao del "
				+ "  where del=:delegacao and ar.nome like 'Maritimos' and p.isValidado=true and p.isPreValidado=false or del=:delegacao and ar.nome like 'Maritimos' and p.isValidado=false and p.isPreValidado=true ");
		query.setParameter("delegacao", delegacao);
	    return query.list();
	}
		
		
		/*@SuppressWarnings("unchecked")
		@Override
		public List<Object[]> getPeticaoDelegacao() {
			Query query = getCurrentSession().createQuery("SELECT del.nome, ar.nome"
					+ " COUNT(p.id)"
					+ "FROM Peticao p "
					+ "left join p.pedido ped left "
					+ "join ped.tipoPedido tipPed "
					+ "join tipPed.area ar "
					+ "join ped.taxasPedido tp "
					+ "left  join tp.taxa t "
					+ "join p.userLoggado us "
					+ "join us.funcionario f left JOIN"
					+ " f.sector s "
					+ "left join s.delegacaoDepartamento dd "
					+ "left join dd.delegacao del "
					+ "group by del.nome, ar.nome");
			
			List<Object[]> lista = query.list();
			
			return lista = query.list();
		
		}*/
		
		@SuppressWarnings({ "unchecked", "unused" })
		@Override
		public List<Object[]> getPeticaoDelegacaoImprimir() {
			Query query = getCurrentSession().createQuery("select DISTINCT del, ar"
					+ "");
			
			List<Object[]> lista = query.list();
			
			return lista = query.list();
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<Object[]> getPeticaoDelegacaoDashBoard() {
			Query query = getCurrentSession().createQuery("select count(p.id) from Peticao p");
			
			List<Object[]> lista = query.list();
			
			return lista = query.list();
		
		}
		
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object[]> getPeticaoDelegacao() {
//		Query query = getCurrentSession().createQuery("SELECT delegacao.nome, "
//				+ " COUNT(p.id)"
//				+ "FROM Peticao p "
//				+ "group by delegacao.nome");
//		//select delegacao_id, count(id) from param_peticao group by delegacao_id;
//		return query.list();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticao(String utentepeticao, List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p  where p.utente like :utentepeticao or p.descricao like :utentepeticao and p in (:list) order by p.updated desc");
		query.setParameter("utentepeticao", "%"+utentepeticao+"%");
		query.setParameterList("list", list);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoValidados(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p  where  p.isValidado=true and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoPendentes(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p  where p.isValidado=false and p.isRecusado=false  and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoRecusados(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p where p.isRecusado=true and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoPago(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p where p.pago=true and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoNaoPago(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p where p.pago=false and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoTerminados(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p where p.terminada=true and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Peticao> findByUtentePeticaoEntregues(List<Peticao> list) {
		Query query = getCurrentSession().createQuery("select p from Peticao p where p.realizada=true and p in (:list) order by p.updated desc");
		query.setParameterList("list", list);
	    return query.list();
	}

	

	


}
