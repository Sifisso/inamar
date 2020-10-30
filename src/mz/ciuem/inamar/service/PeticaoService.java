package mz.ciuem.inamar.service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;

public interface PeticaoService extends GenericService<Peticao>{
	
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
	public  void gravarRedicionar(Pedido pd, Utente utente, Delegacao del, User loggedUser, Listbox lbx_requisitos,Listbox lbx_instrumentoLegal, Listbox lbx_taxasPedido,Listbox lbx_etapasFluxo,Include inc_main, Div div_content_out);
	public void onClickDetalhes(Peticao pet, Include inc_main, Div div_content_out);
	public void onClickRecusa(Peticao pet, Include inc_main, Div div_content_out);
	
	public void onClickVerFactura(Peticao p, Window win) throws JRException;
	public void onClickVerFacturaa(Peticao p, Window win) throws JRException;
	public void onClickVerRecibo(Peticao p, Window win) throws JRException;
	
	public void onClickVerFicha(Peticao p, Window win) throws JRException;
	public void onClickVerTramitacao(Peticao p,Window win) throws JRException;
	public void iniciar();
	public List<Peticao> buscarTesouraria();
	public List<Peticao> getAllOrderDesc();
	public List<Peticao> buscarPeticoesPorDelegacao(Delegacao delegacao);
	public List<Peticao> buscarPeticoesPorDelegacaoAdmMaritimo(Delegacao delegacao);
	public List<Object[]> getPeticaoDelegacao();
	public List<Object[]> getPeticaoDelegacaoDashBoard();
	public List<Object[]> getPeticaoDelegacaoImprimir();
	public List<Object[]> getPeticaoDelegacaoDesempenhoProcessual();


	//Pesquisas
	public List<Peticao> findByUtentePeticao(String utentepeticao, List<Peticao> list);
	public List<Peticao> findByUtentePeticaoValidados(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoPendentes(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoRecusados(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoPago(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoNaoPago(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoTerminados(List<Peticao> list);
	public List<Peticao> findByUtentePeticaoEntregues(List<Peticao> list);
	
	//Alterar
	public void procurarPeloNomePedido(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoValidados(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoPendentes(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoRecusados(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoPago(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoNaoPago(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoTerminados(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public void procurarPeloNomePedidoEntregues(Listbox lbx_peticao, String utentepeticao, List<Peticao> list);
	public List<Peticao> buscarPeticoesPorDelegacaoEmbarcacao(Delegacao delegacao);
	public List<Peticao> buscarPeticoesPorDelegacaoMaritimos(Delegacao delegacao);
	public void onClickDetalhess(Peticao pet, Include inc_main,Div div_content_out);
	public List<Peticao> buscarPeticoesPorDelegacaoTesouraria(Delegacao delegacao);
	public List<Peticao> buscarPeticoesPorDelegacaoSeccaoTecnica(Delegacao delegacao);
	
	
	
	
	
}
