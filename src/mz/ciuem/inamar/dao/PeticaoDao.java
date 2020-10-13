package mz.ciuem.inamar.dao;

import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.User;

public interface PeticaoDao extends GenericDao<Peticao>{
	
	public List<Peticao> getbyUser(User user);
	public List<Peticao> buscarValidadas();
	public List<Peticao> buscarValidadasOuPagas();
	
	public List<Peticao> buscarParaChefeSecretaria();
	public List<Peticao> buscarParaAdministradorMaritimo();
	public List<Peticao> buscarParaAdministradorMaritimo2();
	public List<Peticao> buscarParaAdministradorMaritimo3();
	public List<Peticao> seccaoTecnica();
	public List<Peticao> seccaoTecnica2();
	public List<Peticao> secretaria();
	public List<Peticao> secretaria2();
	public List<Peticao> utente();
	public List<Peticao> utente2();
	public Peticao findEgerPedido(Peticao peticao);
	public List<Peticao> buscarTesouraria();
	List<Peticao> buscarPeticoesPorDelegacaoTesouraria(Delegacao delegacao);
	public List<Peticao> getAllOrderDesc();
	public List<Peticao> buscarPeticoesPorDelegacao(Delegacao delegacao);
	public List<Peticao> buscarPeticoesPorDelegacaoAdmMaritimo(Delegacao delegacao);
	public List<Object[]> getPeticaoDelegacao();
	public List<Object[]> getPeticaoDelegacaoDashBoard();
	public List<Object[]> getPeticaoDelegacaoImprimir();
	
	//Pesquisas
	public List<Peticao> findByUtentePeticao(String utentepeticao, List<Peticao> list);
	public List<Peticao> findByUtentePeticaoValidados(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoPendentes(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoRecusados(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoPago(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoNaoPago(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoTerminados(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoEntregues(List<Peticao> list);
	public List<Peticao> buscarPeticoesPorDelegacaoEmbarcacao(Delegacao delegacao);
	public List<Peticao> buscarPeticoesPorDelegacaoMaritimos(Delegacao delegacao);
	public List<Peticao> buscarPeticoesPorDelegacaoSeccaoTecnica(Delegacao delegacao);
	
	
	
	

	
	

}
