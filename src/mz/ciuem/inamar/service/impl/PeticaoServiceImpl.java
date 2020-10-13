package mz.ciuem.inamar.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.dao.PeticaoDao;
import mz.ciuem.inamar.dao.PeticaoEmbarcacaoDao;
import mz.ciuem.inamar.dao.PeticaoMaritimoDao;
import mz.ciuem.inamar.dao.PeticaoMaritimoTaxaPedidoDao;
import mz.ciuem.inamar.dao.PeticaoPedidoRequisitoDao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoEtapa;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.PeticaoPedidoEtapaInstrumentoLegal;
import mz.ciuem.inamar.entity.PeticaoPedidoRequisito;
import mz.ciuem.inamar.entity.PeticaoTarefasNaEtapa;
import mz.ciuem.inamar.entity.TarefaNaEtapa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.PeticaoEmbarcacaoService;
import mz.ciuem.inamar.service.PeticaoEtapaService;
import mz.ciuem.inamar.service.PeticaoMaritimoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoPedidoEtapaInstrumentoLegalService;
import mz.ciuem.inamar.service.PeticaoPedidoRequisitoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.PeticaoTarefasNaEtapaService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TarefaNaEtapaService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TipoPedidoService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;
import mz.ciuem.inamar.util.Gerador;
import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

@Service("peticaoService")
public class PeticaoServiceImpl extends GenericServiceImpl<Peticao> implements PeticaoService{

	@Autowired
	private PeticaoDao _pDao;
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private PeticaoPedidoRequisitoService _peticaoPedidoRequisitoService;
	@WireVariable
	private PeticaoPedidoEtapaInstrumentoLegalService _peticaoPedidoEtapaInstrumentoLegalService;
	@WireVariable
	private PeticaoTarefasNaEtapaService _peticaoTarefasNaEtapaService;
	@WireVariable
	private PeticaoEtapaService _peticaoEtapaService;
	@WireVariable
	private PeticaoMaritimoService _peticaoMaritimoService;
	@WireVariable
	private PeticaoEmbarcacaoService _peticaoEmbarcacaoService;
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private EtapaFluxoService _etapaFluxoService;
	
	List<PeticaoMaritimoTaxaPedido> _peList = new ArrayList<PeticaoMaritimoTaxaPedido>();
	
	public void iniciar(){
		_peticaoPedidoRequisitoService = (PeticaoPedidoRequisitoService) SpringUtil.getBean("peticaoPedidoRequisitoService");
		_peticaoMaritimoService = (PeticaoMaritimoService) SpringUtil.getBean("peticaoMaritimoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_etapaFluxoService = (EtapaFluxoService) SpringUtil.getBean("etapaFluxoService");
		_peticaoEmbarcacaoService = (PeticaoEmbarcacaoService) SpringUtil.getBean("peticaoEmbarcacaoService");
		_peticaoPedidoEtapaInstrumentoLegalService = (PeticaoPedidoEtapaInstrumentoLegalService) SpringUtil.getBean("peticaoPedidoEtapaInstrumentoLegalService");
		_peticaoTarefasNaEtapaService = (PeticaoTarefasNaEtapaService) SpringUtil.getBean("peticaoTarefasNaEtapaService");
		_peticaoEtapaService = (PeticaoEtapaService) SpringUtil.getBean("peticaoEtapaService");
	}
	
	@Override
	public List<Peticao> getbyUser(User user) {
		return _pDao.getbyUser(user);
	}

	@Override
	public List<Peticao> buscarValidadas() {
		return _pDao.buscarValidadas();
	}

	@Override
	public List<Peticao> buscarValidadasOuPagas() {
		return _pDao.buscarValidadasOuPagas();
	}

	@Override
	public List<Peticao> buscarParaAdministradorMaritimo() {
		return _pDao.buscarParaAdministradorMaritimo();
	}

	@Override
	public List<Peticao> buscarParaAdministradorMaritimo2() {
		return _pDao.buscarParaAdministradorMaritimo2();
	}

	@Override
	public List<Peticao> buscarParaAdministradorMaritimo3() {
		return _pDao.buscarParaAdministradorMaritimo3();
	}

	@Override
	public List<Peticao> seccaoTecnica() {
		return _pDao.seccaoTecnica();
	}

	@Override
	public List<Peticao> seccaoTecnica2() {
		return _pDao.seccaoTecnica2();
	}

	@Override
	public List<Peticao> secretaria() {
		return _pDao.secretaria();
	}

	@Override
	public List<Peticao> secretaria2() {
		return _pDao.secretaria2();
	}

	@Override
	public List<Peticao> utente() {
		return _pDao.utente();
	}

	@Override
	public List<Peticao> utente2() {
		return _pDao.utente2();
	}

	@Override
	public List<Peticao> buscarParaChefeSecretaria() {
		return _pDao.buscarParaChefeSecretaria();
	}

	@Override
	public Peticao findEgerPedido(Peticao peticao) {
		return _pDao.findEgerPedido(peticao);
	}
	
	@Override
	public List<Peticao> buscarTesouraria() {
		return _pDao.buscarTesouraria();
	}

	
	@Override
	public List<Peticao> getAllOrderDesc() {
		return _pDao.getAllOrderDesc();
	}
	
	@Override
	public List<Peticao> buscarPeticoesPorDelegacao(Delegacao delegacao) {
		return _pDao.buscarPeticoesPorDelegacao(delegacao);
	}
	
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoAdmMaritimo(Delegacao delegacao) {
		return _pDao.buscarPeticoesPorDelegacaoAdmMaritimo(delegacao);
	}
	
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoTesouraria(Delegacao delegacao) {
		return _pDao.buscarPeticoesPorDelegacaoTesouraria(delegacao);
	}
	

	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoSeccaoTecnica(Delegacao delegacao) {
		return _pDao.buscarPeticoesPorDelegacaoSeccaoTecnica(delegacao);
	}
	
	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoEmbarcacao(Delegacao delegacao) {
		return _pDao.buscarPeticoesPorDelegacaoEmbarcacao(delegacao);
	}

	@Override
	public List<Peticao> buscarPeticoesPorDelegacaoMaritimos(Delegacao delegacao) {
		return _pDao.buscarPeticoesPorDelegacaoMaritimos(delegacao);
	}
	
	@Override
	public List<Object[]> getPeticaoDelegacao(){
		return _pDao.getPeticaoDelegacao();
	}
	
	@Override
	public List<Object[]> getPeticaoDelegacaoImprimir() {
		// TODO Auto-generated method stub
		return _pDao.getPeticaoDelegacaoImprimir();
	}
	
	@Override
	public List<Object[]> getPeticaoDelegacaoDashBoard(){
		return _pDao.getPeticaoDelegacaoDashBoard();
	}
	
	@Override
	public List<Peticao> findByUtentePeticao(String utentepeticao,List<Peticao> list) {
		return _pDao.findByUtentePeticao(utentepeticao, list);
	}
	

	@Override
	public List<Peticao> findByUtentePeticaoValidados(List<Peticao> list) {
		return _pDao.findByUtentePeticaoValidados(list);
	}

	@Override
	public List<Peticao> findByUtentePeticaoPendentes(List<Peticao> list) {
		return _pDao.findByUtentePeticaoPendentes(list);
	}

	@Override
	public List<Peticao> findByUtentePeticaoRecusados(List<Peticao> list) {
		return _pDao.findByUtentePeticaoRecusados(list);
	}

	@Override
	public List<Peticao> findByUtentePeticaoPago(List<Peticao> list) {
		return _pDao.findByUtentePeticaoPago(list);
	}

	@Override
	public List<Peticao> findByUtentePeticaoNaoPago(List<Peticao> list) {
		return _pDao.findByUtentePeticaoNaoPago(list);
	}

	@Override
	public List<Peticao> findByUtentePeticaoTerminados(List<Peticao> list) {
		return _pDao.findByUtentePeticaoTerminados(list);
	}

	@Override
	public List<Peticao> findByUtentePeticaoEntregues(List<Peticao> list) {
		return _pDao.findByUtentePeticaoEntregues(list);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ----------------------------------------------GRAVAR E REDICIONAR----------------------------------------------//
	@Override
	public  void gravarRedicionar(Pedido pd, Utente utente, Delegacao del, User loggedUser, Listbox lbx_requisitos,Listbox lbx_instrumentoLegal, Listbox lbx_taxasPedido,Listbox lbx_etapasFluxo,Include inc_main, Div div_content_out) {
		User u = utente.getUserLogin();
		
		
		

		// Emissao de Cedula Maritima
		if (pd != null && pd.getId() == 43) {
			PeticaoMaritimo pm = new PeticaoMaritimo();
			pm.setPedido(pd);
			pm.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			peticao.setPeticaoMaritimo(pm);
			peticao.setUser(u);
			peticao.setUtente(u.getUtente().getNome() + " " + u.getUtente().getApelido());
			//if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() +" "+ u.getUtente().getApelido());
			peticao.setTipo("1");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pm.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pm.getEntidade());
			peticao.setPedido(pm.getPedido());
			

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(),""+ nr, "08"));
			pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pm.getReferencia());
			peticao.setEntidade(pm.getEntidade());
			peticao.setPedido(pm.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pm.setPeticao(peticao);
			_peticaoMaritimoService.saveOrUpdate(pm);

			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", pm);
			div_content_out.detach();
			inc_main.setSrc("/views/Maritimo/emissaoCedulaMaritima.zul");
		
		}
		
		// Segunda via de Emissao de Cedula Maritima
				if (pd != null && pd.getId() == 44) {
					PeticaoMaritimo pm = new PeticaoMaritimo();
					pm.setPedido(pd);
					pm.setUser(u);

					// Gerar Peticao (temporario)
					Peticao peticao = new Peticao();
					peticao.setPeticaoMaritimo(pm);
					peticao.setDelegacao(del);
					peticao.setUserLoggado(loggedUser);
					peticao.setUser(u);
					if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() +" "+ u.getUtente().getApelido());
					peticao.setTipo("11");
					peticao.setDescricao(pd.getDescricao());
					peticao.setValor(pm.getValor());
					peticao.setLocalizacao("Secretaria");
					peticao.setEntidade(pm.getEntidade());
					peticao.setPedido(pm.getPedido());

					_peticaoService.saveOrUpdate(peticao);
					long nr = peticao.getId() + 10000;

					pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(),""+ nr, "08"));
					pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
					
					// Gerar REquisitos
					gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
					
					// Gerar Instrumentos Legais
					gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
					
					// Gerar Taxas
					gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
					
					//Gerar PeticaoEtapa
					gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

					Date data = new Date();
					SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
					String f = formatador.format(data);
					String nrFact = "" + nr + "" + f;
					peticao.setNrFactura("" + nrFact);
					peticao.setNrExpediente("" + nr);
					peticao.setReferencia(pm.getReferencia());
					peticao.setEntidade(pm.getEntidade());
					peticao.setPedido(pm.getPedido());
					_peticaoService.saveOrUpdate(peticao);

					pm.setPeticao(peticao);
					_peticaoMaritimoService.saveOrUpdate(pm);

					Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", pm);
					div_content_out.detach();
					inc_main.setSrc("/views/Maritimo/segundaViaEmissaoCedulaMaritima.zul");

				}
				
				// Averbamento de cedula Maritima
				if (pd != null && pd.getId() == 45) {
					PeticaoMaritimo pm = new PeticaoMaritimo();
					pm.setPedido(pd);
					pm.setUser(u);

					// Gerar Peticao (temporario)
					Peticao peticao = new Peticao();
					peticao.setPeticaoMaritimo(pm);
					peticao.setDelegacao(del);
					peticao.setUserLoggado(loggedUser);
					peticao.setUser(u);
					if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() +" "+ u.getUtente().getApelido());
					peticao.setTipo("11");
					peticao.setDescricao(pd.getDescricao());
					peticao.setValor(pm.getValor());
					peticao.setLocalizacao("Secretaria");
					peticao.setEntidade(pm.getEntidade());
					peticao.setPedido(pm.getPedido());

					_peticaoService.saveOrUpdate(peticao);
					long nr = peticao.getId() + 10000;

					pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(),""+ nr, "08"));
					pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
					
					// Gerar REquisitos
					gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
					
					// Gerar Instrumentos Legais
					gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
					
					// Gerar Taxas
					gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
					
					//Gerar PeticaoEtapa
					gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

					Date data = new Date();
					SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
					String f = formatador.format(data);
					String nrFact = "" + nr + "" + f;
					peticao.setNrFactura("" + nrFact);
					peticao.setNrExpediente("" + nr);
					peticao.setReferencia(pm.getReferencia());
					peticao.setEntidade(pm.getEntidade());
					peticao.setPedido(pm.getPedido());
					_peticaoService.saveOrUpdate(peticao);

					pm.setPeticao(peticao);
					_peticaoMaritimoService.saveOrUpdate(pm);

					Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", pm);
					div_content_out.detach();
					inc_main.setSrc("/views/Maritimo/averbamentoCedulaMaritima.zul");

					
					// Pedido de Exame ou Carta
				}else if(pd != null && pd.getId() == 46){
					PeticaoMaritimo pm = new PeticaoMaritimo();
					pm.setPedido(pd);
					pm.setUser(u);

					// Gerar Peticao (temporario)
					Peticao peticao = new Peticao();
					peticao.setPeticaoMaritimo(pm);
					peticao.setDelegacao(del);
					peticao.setUserLoggado(loggedUser);
					peticao.setUser(u);
					if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() +" "+ u.getUtente().getApelido());
					peticao.setTipo("11");
					peticao.setDescricao(pd.getDescricao());
					peticao.setValor(pm.getValor());
					peticao.setLocalizacao("Secretaria");
					peticao.setEntidade(pm.getEntidade());
					peticao.setPedido(pm.getPedido());

					_peticaoService.saveOrUpdate(peticao);
					long nr = peticao.getId() + 10000;

					pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(),""+ nr, "08"));
					pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
					
					// Gerar REquisitos
					gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
					
					// Gerar Instrumentos Legais
					gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
					
					// Gerar Taxas
					gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
					
					//Gerar PeticaoEtapa
					gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

					Date data = new Date();
					SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
					String f = formatador.format(data);
					String nrFact = "" + nr + "" + f;
					peticao.setNrFactura("" + nrFact);
					peticao.setNrExpediente("" + nr);
					peticao.setReferencia(pm.getReferencia());
					peticao.setEntidade(pm.getEntidade());
					peticao.setPedido(pm.getPedido());
					_peticaoService.saveOrUpdate(peticao);

					pm.setPeticao(peticao);
					_peticaoMaritimoService.saveOrUpdate(pm);

					Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", pm);
					div_content_out.detach();
					inc_main.setSrc("/views/Maritimo/pedidoExame.zul");
				}
		
		
		// Pedido de Averbamento de Cedula Maritima
		else if (pd != null && pd.getId() == 15) {
			PeticaoMaritimo pm = new PeticaoMaritimo();
			pm.setPedido(pd);
			pm.setUser(u);
			_peticaoMaritimoService.create(pm);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoMaritimo(pm);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("3");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pm.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pm.getEntidade());
			peticao.setPedido(pm.getPedido());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;
			pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			_peticaoMaritimoService.saveOrUpdate(pm);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pm.getReferencia());
			peticao.setPedido(pm.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pm.setPeticao(peticao);
			_peticaoMaritimoService.saveOrUpdate(pm);

			Executions.getCurrent().getSession()
					.setAttribute("ss_peticaoMaritimo", pm);
			div_content_out.detach();
			inc_main.setSrc("/views/Maritimo/averbamentoCedulaMaritima.zul");
		
		// Pedido de vistoria de Uma Embarcacao	
		}else if(pd != null && pd.getId() == 47){
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());
			peticao.setPedido(pe.getPedido());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/vistoriaDeEmbarcacao.zul");
		}
		// Pedido de Registo de uma Embarca��o importada
		else if (pd != null && pd.getId() == 36) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());
			peticao.setPedido(pe.getPedido());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/embarcacaoImportada.zul");
		}

		// Pedido de Licen�a de constru��o duma embarca��o
		else if (pd != null && pd.getId() == 41) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("16");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido()
					.getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""
					+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido()
					.getCodigo()));

			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/emissaoLicencaConstrucaoEmbarcacao.zul");
		}

		// Registo de uma embarcacao acabada de construir
		else if (pd != null && pd.getId() == 37) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);


			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() + " "+ u.getUtente().getApelido());
			peticao.setTipo("13");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido()
					.getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""
					+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido()
					.getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/registoEmbarcacaoAcabadaConstruir.zul");
		}

		// Cancelamento de registo de uma embarcacao por ter sido vendida
		else if (pd != null && pd.getId() == 42) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() + " "+ u.getUtente().getApelido());
			peticao.setTipo("17");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);
			
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));

			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession().setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/cancelamentoRegistoEmbarcacaoVendida.zul");
		}
		// Registo de Embarcacao Acabada de Comprar
		else if (pd != null && pd.getId() == 38){
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() + " "+ u.getUtente().getApelido());
			peticao.setTipo("13");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);
			
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession().setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/registoEmbarcacaoAcabadaComprar.zul");
		}
		
		// EMISSAO DE TERMO OU TITULO DE PROPRIEDADE
		else if (pd != null && pd.getId() == 40){
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() + " "+ u.getUtente().getApelido());
			peticao.setTipo("15");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);
			
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession().setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/emissaoTituloPropriedade.zul");
		}
		// EMISSAO DE TERMO OU TITULO DE PROPRIEDADE 2� VIA
		else if (pd != null && pd.getId() == 39){
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);


			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() + " "+ u.getUtente().getApelido());
			peticao.setTipo("14");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);
			
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));

			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			peticao.setPedido(pe.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			Executions.getCurrent().getDesktop().getSession().setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/emissaoTituloPropriedadeSegundaVia.zul");
		}
		// Provisorio
		else {
			// Criar outro tipio de peticao aqui
			PeticaoMaritimo pm = new PeticaoMaritimo();
			pm.setPedido(pd);
			pm.setUser(u);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoMaritimo(pm);
			peticao.setUser(u);
			peticao.setDelegacao(del);
			peticao.setUserLoggado(loggedUser);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("2");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pm.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setReferencia(pm.getReferencia());
			peticao.setEntidade(pm.getEntidade());
			peticao.setPedido(pm.getPedido());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));

			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			
			// Gerar Instrumentos Legais
			gerarPedidoInstrumentosLegais(lbx_instrumentoLegal.getItems(), peticao);
			
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			
			//Gerar PeticaoEtapa
			gerarPeticaoEtapa(lbx_etapasFluxo.getItems(), peticao);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pm.getReferencia());
			peticao.setPedido(pm.getPedido());
			_peticaoService.saveOrUpdate(peticao);

			pm.setPeticao(peticao);
			_peticaoMaritimoService.saveOrUpdate(pm);

			Executions.getCurrent().getSession()
					.setAttribute("ss_peticaoMaritimo", pm);
			div_content_out.detach();
			inc_main.setSrc("/views/Maritimo/peticaoGeralEmpty.zul");
		}
	}
	
	
	private void gerarPeticaoEtapa(List<Listitem> items, Peticao peticao) {
		for (Listitem listitem : items) {
			PeticaoEtapa pe = new PeticaoEtapa();
			EtapaFluxo ef = (EtapaFluxo) listitem.getValue();
			pe.setEtapaFluxo(ef);
			pe.setPeticao(peticao);
			_peticaoEtapaService.saveOrUpdate(pe);
			
			gerarPeticaoTarefasEtapa(ef.getTarefasNasEtapas(), pe);
		}
	}

	private void gerarPeticaoTarefasEtapa(List<TarefaNaEtapa> tarefasNasEtapas, PeticaoEtapa pe) {
		for (TarefaNaEtapa tarefaNaEtapa : tarefasNasEtapas) {
			PeticaoTarefasNaEtapa pte = new PeticaoTarefasNaEtapa();
			pte.setPeticaoEtapa(pe);
			pte.setTarefaNaEtapa(tarefaNaEtapa);
			_peticaoTarefasNaEtapaService.saveOrUpdate(pte);
		}
	}

	private void gerarPedidoInstrumentosLegais(List<Listitem> items,Peticao peticao) {
		for (Listitem listitem : items) {
			PeticaoPedidoEtapaInstrumentoLegal ppeil = new PeticaoPedidoEtapaInstrumentoLegal();
			ppeil.setPedidoEtapa((PedidoEtapa) listitem.getValue());
			ppeil.setPeticao(peticao);
			_peticaoPedidoEtapaInstrumentoLegalService.saveOrUpdate(ppeil);
		}
	}

	public void gerarPedidoRequisitos(List<Listitem> listI, Peticao p){
		for (Listitem listitem : listI) {
			PeticaoPedidoRequisito ppr = new PeticaoPedidoRequisito();
			ppr.setPedidoRequisito((PedidoRequisito) listitem.getValue());
			ppr.setPeticao(p);
			_peticaoPedidoRequisitoService.saveOrUpdate(ppr);
		}
	}
	
	
	
	// ----------------------------------------------GRAVAR E REDICIONAR (METODOS AUXILIARES)----------------------------------------------//
	public void gerarPedidoRequisitosEmbarcacao(List<Listitem> listI, Peticao p){
		for (Listitem listitem : listI) {
			PeticaoPedidoRequisito ppr = new PeticaoPedidoRequisito();
			ppr.setPedidoRequisito((PedidoRequisito) listitem.getValue());
			ppr.setPeticao(p);
			_peticaoPedidoRequisitoService.saveOrUpdate(ppr);
		}
	}
	
	public void gerarPedidoTaxas(List<Listitem> listI, Peticao p){
		double valor=0;
		for (Listitem listitem : listI) {
	    	PeticaoMaritimoTaxaPedido _pmtp = new PeticaoMaritimoTaxaPedido();
	    	TaxaPedido tp = (TaxaPedido)listitem.getValue();
	    	_pmtp.setTaxaPedido(tp);
	    	valor+=tp.getTaxa().getValor();
	    	_pmtp.setPeticao(p);
	    	_peticaoMaritimoTaxaPedidoService.saveOrUpdate(_pmtp);
		}
		
		p.setValor(valor);
	}
	
	public void gerarPedidoTaxasEmbarcacao(List<Listitem> listI, Peticao pe){
		double valor=0;
		for (Listitem listitem : listI) {
	    	PeticaoMaritimoTaxaPedido _pmtp = new PeticaoMaritimoTaxaPedido();
	    	TaxaPedido tp = (TaxaPedido)listitem.getValue();
	    	_pmtp.setTaxaPedido(tp);
	    	valor+=tp.getTaxa().getValor();
	    	_pmtp.setPeticao(pe);
	    	_peticaoMaritimoTaxaPedidoService.saveOrUpdate(_pmtp);
		}
		
		pe.setValor(valor);
	}
	// ----------------------------------------------FIM GRAVAR E REDICIONAR (METODOS AUXILIARES)----------------------------------------------//

	
	// ----------------------------------------------FIM GRAVAR E REDICIONAR----------------------------------------------//

	
	
// ----------------------------------------------EDITAR DETALHES----------------------------------------------//
	
	public void onClickRecusa(Peticao pet, Include inc_main, Div div_content_out){
			
		//if(pet.getPedido().getTipoPedido().getArea().getId()==(1)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/pedido_recusado.zul");
		//}
    		
	}
	
	//Editar Detalhes
	public void onClickDetalhes(Peticao pet, Include inc_main, Div div_content_out){
		//Atribuicao de Cedulas maritimas
		if(pet.getPedido().getId()==(43)){
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/emissaoCedulaMaritima.zul");
    		
		}else if(pet.getPedido().getId()==(46)){
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/pedidoExame.zul");
		
		}
		
		else if(pet.getPedido().getId()==(45)){
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/averbamentoCedulaMaritima.zul");
		}else if(pet.getPedido().getId()==(44)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/segundaViaEmissaoCedulaMaritima.zul");
		}else if(pet.getPedido().getId()==(43)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/emissaoCedulaMaritima.zul");
		}else if(pet.getPedido().getId()==(36)){
			//PeticaoEmbarcacao petEmb = pet.getPeticaoEmbarcacao();
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/embarcacaoImportada.zul");
		}else if(pet.getPedido().getId()==(37)){
			//PeticaoEmbarcacao petEmb = pet.getPeticaoEmbarcacao();
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/registoEmbarcacaoAcabadaConstruir.zul");
		}else if(pet.getPedido().getId()==(38)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/registoEmbarcacaoAcabadaComprar.zul");
		}else if(pet.getPedido().getId()==(39)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/emissaoTituloPropriedadeSegundaVia.zul");
		}else if(pet.getPedido().getId()==(40)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/emissaoTituloPropriedade.zul");
		}else if(pet.getPedido().getId()==(41)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/emissaoLicencaConstrucaoEmbarcacao.zul");
		}else if(pet.getPedido().getId()==(42)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/cancelamentoRegistoEmbarcacaoVendida.zul");
		}else if(pet.getPedido().getId()==(47)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Embarcacao/vistoriaDeEmbarcacao.zul");
		}
		else{
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/peticaoGeralEmpty.zul");
		}
			
			
	}
	
	public void onClickDetalhess(Peticao pet, Include inc_main, Div div_content_out){
		//Atribuicao de Cedulas maritimas
		if(pet.getPedido().getId()==(43)){
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Maritimo/emissaoCedulaMaritima.zul");
    		
		}else if(pet.getPedido().getId()==(45)){
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Maritimo/averbamentoCedulaMaritima.zul");
		}else if(pet.getPedido().getId()==(44)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Maritimo/segundaViaEmissaoCedulaMaritima.zul");
		}else if(pet.getPedido().getId()==(43)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Maritimo/emissaoCedulaMaritima.zul");
		}else if(pet.getPedido().getId()==(36)){
			//PeticaoEmbarcacao petEmb = pet.getPeticaoEmbarcacao();
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/embarcacaoImportada.zul");
		}else if(pet.getPedido().getId()==(37)){
			//PeticaoEmbarcacao petEmb = pet.getPeticaoEmbarcacao();
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/registoEmbarcacaoAcabadaConstruir.zul");
		}else if(pet.getPedido().getId()==(38)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/registoEmbarcacaoAcabadaComprar.zul");
		}else if(pet.getPedido().getId()==(39)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/emissaoTituloPropriedadeSegundaVia.zul");
		}else if(pet.getPedido().getId()==(40)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/emissaoTituloPropriedade.zul");
		}else if(pet.getPedido().getId()==(41)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/emissaoLicencaConstrucaoEmbarcacao.zul");
		}else if(pet.getPedido().getId()==(42)){
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/expediente/Embarcacao/cancelamentoRegistoEmbarcacaoVendida.zul");
		}else{
			Executions.getCurrent().getSession().setAttribute("p", pet);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/peticaoGeralEmpty.zul");
		}
			
			
	}
	
	
	// ---------------------------------------------- FIM EDITAR DETALHES----------------------------------------------//
	
	
	
	
	// ----------------------------------------------IMPRESSAO FACTURAS E RECIBOS----------------------------------------------//
	public void onClickVerFactura(Peticao p, Window win) throws JRException{
		//EMBARCACAO
    	if(p.getTipo().equals("13") || p.getTipo().equals("14") || p.getTipo().equals("15") || p.getTipo().equals("16")|| p.getTipo().equals("17")){
			PeticaoEmbarcacao _peticaoEmbarcacao = p.getPeticaoEmbarcacao();
			verFacturaEmbarcacao(_peticaoEmbarcacao, p, win);
			
		}else if (p.getTipo().equals("2")){
			//MARITIMMO OU INDEFINIDO
			PeticaoMaritimo _peticaoMaritimo = p.getPeticaoMaritimo();
			verFacturaMaritimo(_peticaoMaritimo, p, win);
		}
    	
    	
   }
	
	public void onClickVerFacturaa(Peticao p, Window win) throws JRException{
		//EMBARCACAO
    	if(p.getPedido().getId()==36 || p.getPedido().getId()==37 || p.getPedido().getId()==38 || p.getPedido().getId()==39 ||
    			p.getPedido().getId()==40 || p.getPedido().getId()==41 || p.getPedido().getId()==42 || p.getPedido().getId()==47){
			PeticaoEmbarcacao _peticaoEmbarcacao = p.getPeticaoEmbarcacao();
			verFacturaEmbarcacao(_peticaoEmbarcacao, p, win);
			
    	}else if(p.getPedido().getId()==43 || p.getPedido().getId()==44 || p.getPedido().getId()==45 || 
    			p.getPedido().getId()==46){
    		PeticaoMaritimo _peticaoMaritimo = p.getPeticaoMaritimo();
			verFacturaMaritimo(_peticaoMaritimo, p, win);

    }
    	
   }
	
	
	public void onClickVerRecibo(Peticao p, Window win) throws JRException{
		//EMBARCACAO
    	if(p.getPedido().getId()==36 || p.getPedido().getId()==37 || p.getPedido().getId()==38 || p.getPedido().getId()==39 ||
    			p.getPedido().getId()==40 || p.getPedido().getId()==41 || p.getPedido().getId()==42 || p.getPedido().getId()==47){
			PeticaoEmbarcacao _peticaoEmbarcacao = p.getPeticaoEmbarcacao();
			verReciboEmbarcacao(_peticaoEmbarcacao, p, win);
			
    	}else if(p.getPedido().getId()==43 || p.getPedido().getId()==44 || p.getPedido().getId()==45 || 
    			p.getPedido().getId()==46){
    		PeticaoMaritimo _peticaoMaritimo = p.getPeticaoMaritimo();
			verReciboMaritimo(_peticaoMaritimo, p, win);

    	}

   }
	
	
	private void verReciboEmbarcacao(PeticaoEmbarcacao _peticaoEmbarcacao, Peticao p, Window win) throws JRException {
	    Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        Utente u = _utenteService.buscarUtenteByUser(_peticaoEmbarcacao.getUser());
        //Parametros
        //Fazer query
        
        mapaParam.put("pedido", ""+_peticaoEmbarcacao.getPedido().getDescricao());
        mapaParam.put("valorTotal", ""+p.getValorTotal()+"0");
        mapaParam.put("dataPagamento", ""+p.getPagamento().getDataRecepcaoValor());
        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
        mapaParam.put("nomePai", ""+u.getNomePai());
        mapaParam.put("primeiroNome", ""+u.getNome());
        mapaParam.put("nomeMae", ""+u.getNomeMae());
        mapaParam.put("nuit",""+ u.getNuit());
        
        int idade = new Date().getYear()-u.getDataNascimento().getYear();
        mapaParam.put("idade", ""+idade);
        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
        //Nao existe
        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
        mapaParam.put("bairro", ""+u.getBairro());
        mapaParam.put("quarteirao", ""+u.getQuarteirao());
        mapaParam.put("nrCasa", ""+u.getNrCasa());
        mapaParam.put("nrTelefone", ""+u.getCelular());
        //Nao existe
        mapaParam.put("tipoUtente", "Empresa");
        //Nao existe
        mapaParam.put("paticionarioNr", p.getNrExpediente());
        //Nso existe
        mapaParam.put("codigoArea", "02");
        //Nao Existe
        mapaParam.put("codigoSubArea", "04");
        //Nao Existe
        mapaParam.put("nrFactura", p.getNrFactura());
        mapaParam.put("referencia", p.getReferencia());
        mapaParam.put("subToal", p.getValor());
       // double iva = p.getValor()+0.17;
       // double valorTotal = p.getValor()+iva;
        mapaParam.put("entidade", "70003");
        
        mapaParam.put("nrExpediente", p.getNrExpediente());
        mapaParam.put("hora", ""+_peticaoEmbarcacao.getCreated().getHours());
        
        
       //Factura Utente 
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoEmbarcacao(_peticaoEmbarcacao);
   	   MasterRep.imprimir("/reportParam/facturas/reciboPedidoUtente.jrxml", _list, mapaParam, win);
		
	}
	
	private void verReciboMaritimo(PeticaoMaritimo _peticaoMaritimo, Peticao p,Window win) throws JRException {
	    Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        Utente u = _utenteService.buscarUtenteByUser(_peticaoMaritimo.getUser());
        //Parametros
        //Fazer query
       // Messagebox.show("Valor total vindo de pedido = "+p.getPedido().getTotal());
        mapaParam.put("dataPagamento", ""+p.getPagamento().getDataRecepcaoValor());
        mapaParam.put("valorTotal", ""+p.getValorTotal()+"0");
        mapaParam.put("pedido", ""+_peticaoMaritimo.getPedido().getDescricao());
        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
        mapaParam.put("nomePai", ""+u.getNomePai());
        mapaParam.put("nomeMae", ""+u.getNomeMae());
        mapaParam.put("nuit",""+ u.getNuit());
        
       int idade = new Date().getYear()-u.getDataNascimento().getYear();
       
        mapaParam.put("idade", ""+idade);
        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
        //Nao existe
        mapaParam.put("dataEmissaoDocumento", ""+u.getValidade());
        mapaParam.put("bairro", ""+u.getBairro());
        mapaParam.put("quarteirao", ""+u.getQuarteirao());
        mapaParam.put("nrCasa", ""+u.getNrCasa());
        mapaParam.put("nrTelefone", ""+u.getCelular());
        //Nao existe
        mapaParam.put("tipoUtente", ""+u.getTipo());
        //Nao existe
        mapaParam.put("paticionarioNr", p.getNrExpediente());
        //Nso existe
        mapaParam.put("codigoArea", "02");
        //Nao Existe
        mapaParam.put("codigoSubArea", "04");
        //Nao Existe
        mapaParam.put("nrFactura", p.getNrFactura());
        mapaParam.put("referencia", p.getReferencia());
        mapaParam.put("subToal", p.getValor());
        
        //double valorTotal = p.getValor()+iva;
        mapaParam.put("entidade",""+ p.getEntidade());
        
        mapaParam.put("nrExpediente", p.getNrExpediente());
        mapaParam.put("hora", ""+_peticaoMaritimo.getCreated().getHours());
        
        
       //Factura Utente
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoMaritimo(_peticaoMaritimo);
   	   MasterRep.imprimir("/reportParam/facturas/reciboPedidoUtente.jrxml", _list, mapaParam, win);
		
	}
	
	private void verFacturaEmbarcacao(PeticaoEmbarcacao _peticaoEmbarcacao, Peticao p, Window win) throws JRException {
	    Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        Utente u = _utenteService.buscarUtenteByUser(_peticaoEmbarcacao.getUser());
        //Parametros
        //Fazer query
        mapaParam.put("pedido", ""+_peticaoEmbarcacao.getPedido().getDescricao());
        mapaParam.put("valorTotal", ""+p.getValorTotal());
        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
        mapaParam.put("nomePai", ""+u.getNomePai());
        mapaParam.put("primeiroNome", ""+u.getNome());
        mapaParam.put("nomeMae", ""+u.getNomeMae());
        mapaParam.put("nuit",""+ u.getNuit());
        
        int idade = new Date().getYear()-u.getDataNascimento().getYear();
       
        mapaParam.put("idade", ""+idade);
        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
        //Nao existe
        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
        mapaParam.put("bairro", ""+u.getBairro());
        mapaParam.put("quarteirao", ""+u.getQuarteirao());
        mapaParam.put("nrCasa", ""+u.getNrCasa());
        mapaParam.put("nrTelefone", ""+u.getCelular());
        //Nao existe
        mapaParam.put("tipoUtente", "Empresa");
        //Nao existe
        mapaParam.put("paticionarioNr", p.getNrExpediente());
        //Nso existe
        mapaParam.put("codigoArea", "02");
        //Nao Existe
        mapaParam.put("codigoSubArea", "04");
        //Nao Existe
        mapaParam.put("nrFactura", p.getNrFactura());
        mapaParam.put("referencia", p.getReferencia());
        mapaParam.put("subToal", p.getValor());
       // double iva = p.getValor()+0.17;
       // double valorTotal = p.getValor()+iva;
        mapaParam.put("entidade", "70003");
        
        mapaParam.put("nrExpediente", p.getNrExpediente());
        mapaParam.put("hora", ""+_peticaoEmbarcacao.getCreated().getHours());
        
        
       //Factura Utente 
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoEmbarcacao(_peticaoEmbarcacao);
   	   MasterRep.imprimir("/reportParam/facturas/facturaPedidoUtente.jrxml", _list, mapaParam, win);
		
	}

	private void verFacturaMaritimo(PeticaoMaritimo _peticaoMaritimo, Peticao p,Window win) throws JRException {
	    Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        Utente u = _utenteService.buscarUtenteByUser(_peticaoMaritimo.getUser());
        //Parametros
        //Fazer query
       // Messagebox.show("Valor total vindo de pedido = "+p.getPedido().getTotal());
        mapaParam.put("valorTotal", ""+p.getValorTotal());
        mapaParam.put("pedido", ""+_peticaoMaritimo.getPedido().getDescricao());
        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
        mapaParam.put("nomePai", ""+u.getNomePai());
        mapaParam.put("nomeMae", ""+u.getNomeMae());
        mapaParam.put("nuit",""+ u.getNuit());
        
       int idade = new Date().getYear()-u.getDataNascimento().getYear();
       
       /*List<PeticaoMaritimoTaxaPedido> _listt = _peticaoMaritimoTaxaPedidoService.findByPeticaoMaritimo(_peticaoMaritimo);
               
       double valor_total = 0.0;
       
       for(PeticaoMaritimoTaxaPedido valor:_listt){
    	   valor_total = valor_total+valor.getTaxaPedido().getTaxa().getValor();
       }*/
     
       	//mapaParam.put("valorTotal", ""+valor_total);
        mapaParam.put("idade", ""+idade);
        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
        //Nao existe
        mapaParam.put("dataEmissaoDocumento", ""+u.getValidade());
        mapaParam.put("bairro", ""+u.getBairro());
        mapaParam.put("quarteirao", ""+u.getQuarteirao());
        mapaParam.put("nrCasa", ""+u.getNrCasa());
        mapaParam.put("nrTelefone", ""+u.getCelular());
        //Nao existe
        mapaParam.put("tipoUtente", ""+u.getTipo());
        //Nao existe
        mapaParam.put("paticionarioNr", p.getNrExpediente());
        //Nso existe
        mapaParam.put("codigoArea", "02");
        //Nao Existe
        mapaParam.put("codigoSubArea", "04");
        //Nao Existe
        mapaParam.put("nrFactura", p.getNrFactura());
        mapaParam.put("referencia", p.getReferencia());
        mapaParam.put("subToal", p.getValor());
        
        //double valorTotal = p.getValor()+iva;
        mapaParam.put("entidade",""+ p.getEntidade());
        
        mapaParam.put("nrExpediente", p.getNrExpediente());
        mapaParam.put("hora", ""+_peticaoMaritimo.getCreated().getHours());
        
        
       //Factura Utente
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoMaritimo(_peticaoMaritimo);
   	   MasterRep.imprimir("/reportParam/facturas/facturaPedidoUtente.jrxml", _list, mapaParam, win);
		
	}
	// ----------------------------------------------FIM IMPRESSAO FACTURAS E RECIBOS----------------------------------------------//
	
	
	
	// ----------------------------------------------IMPRESSAO TRAMITACAO----------------------------------------------//
	public void onClickVerTramitacao(Peticao p,Window win) throws JRException{
		if(p.getPedido().getId()==36 || p.getPedido().getId()==37 || p.getPedido().getId()==38 || p.getPedido().getId()==39 ||
    			p.getPedido().getId()==40 || p.getPedido().getId()==41 || p.getPedido().getId()==42 || p.getPedido().getId()==47){
			//EMBARCACACAO
			PeticaoEmbarcacao _peticaoEmbarcacao = p.getPeticaoEmbarcacao();
			tramitacaoEmbarcacao(_peticaoEmbarcacao, win);
		}else if(p.getPedido().getId()==43 || p.getPedido().getId()==44 || p.getPedido().getId()==45 || 
    			p.getPedido().getId()==46){
			//MARITIMO
			PeticaoMaritimo _peticaoMaritimo = p.getPeticaoMaritimo();
			tramitacaoMaritimo(_peticaoMaritimo, win);
		}
	}
	
	private void tramitacaoEmbarcacao(PeticaoEmbarcacao _peticaoEmbarcacao,Window win) throws JRException {
		
		   Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");  
	   		InputStream inputS= ex.getDesktop().getWebApp().getResourceAsStream("/img/yes.png"); 
	   		InputStream inputN= ex.getDesktop().getWebApp().getResourceAsStream("/img/no.png"); 
	   		mapaParam.put("imagemsim", inputS);
	   		mapaParam.put("imagemnao", inputN);
	        mapaParam.put("imagemLogo", inputV);
	        Utente u = _utenteService.buscarUtenteByUser(_peticaoEmbarcacao.getUser());
	        //Parametros
	        //Fazer query
	        mapaParam.put("pedido", ""+_peticaoEmbarcacao.getPedido().getDescricao());
	        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
	        mapaParam.put("nomePai", ""+u.getNomePai());
	        mapaParam.put("nomeMae", ""+u.getNomeMae());
	        
	     //   int idade = (u.getDataNascimento()!=null)? new Date().getYear()-u.getDataNascimento().getYear() : 0;
	        
	        mapaParam.put("idade", ""+0);
	        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
	        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
	        //Nao existe
	        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
	        mapaParam.put("bairro", ""+u.getBairro());
	        mapaParam.put("quarteirao", ""+u.getQuarteirao());
	        mapaParam.put("nrCasa", ""+u.getNrCasa());
	        mapaParam.put("nrTelefone", ""+u.getCelular());
	        //Nao existe
	        mapaParam.put("tipoUtente", u.getTipo());
	        //Nao existe
	        //Acertar
	        mapaParam.put("paticionarioNr", "12312312312");
	        //Nso existe
	        mapaParam.put("codigoArea", "02");
	        //Nao Existe
	        mapaParam.put("codigoSubArea", "04");
	        //Nao Existe
	        mapaParam.put("nrExpediente", "115502MF");
	        mapaParam.put("hora", ""+_peticaoEmbarcacao.getCreated().getHours());
	        //Nr Factura
	        mapaParam.put("nrFactura", "1321651IN");
	        //Fluxo
	        //chamr o verdadeiro nome
	        Pedido pe = _peticaoEmbarcacao.getPedido();
	        mapaParam.put("fluxo", pe.getFluxo().getNome());
	        String realPath = ex.getDesktop().getWebApp().getRealPath("/reportParam/");
	        mapaParam.put("SUBREPORT_DIR", realPath);
	   	   
	   	// Factura Secretario 
	      // List<EtapaFluxo> estF = _etapaFluxoService.findByFluxo(pe.getFluxo());
	       List<PeticaoEtapa> petE = _peticaoEtapaService.findByPeticao(_peticaoEmbarcacao.getPeticao());
	   	   MasterRep.imprimir("/reportParam/facturaPedido.jrxml", petE, mapaParam, win);
		
	}

	private void tramitacaoMaritimo(PeticaoMaritimo _peticaoMaritimo,Window win) throws JRException {
		  Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");  
	   		InputStream inputS= ex.getDesktop().getWebApp().getResourceAsStream("/img/yes.png"); 
	   		InputStream inputN= ex.getDesktop().getWebApp().getResourceAsStream("/img/no.png"); 
	   		mapaParam.put("imagemsim", inputS);
	   		mapaParam.put("imagemnao", inputN);
	        mapaParam.put("imagemLogo", inputV);
	        Utente u = _utenteService.buscarUtenteByUser(_peticaoMaritimo.getUser());
	        //Parametros
	        //Fazer query
	        mapaParam.put("pedido", ""+_peticaoMaritimo.getPedido().getDescricao());
	        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
	        mapaParam.put("nomePai", ""+u.getNomePai());
	        mapaParam.put("nomeMae", ""+u.getNomeMae());
	        
	       // int idade = (u.getDataNascimento()==null)? new Date().getYear()-u.getDataNascimento().getYear() : 0;
	        mapaParam.put("idade", ""+0);
	        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
	        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
	        //Nao existe
	        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
	        mapaParam.put("bairro", ""+u.getBairro());
	        mapaParam.put("quarteirao", ""+u.getQuarteirao());
	        mapaParam.put("nrCasa", ""+u.getNrCasa());
	        mapaParam.put("nrTelefone", ""+u.getCelular());
	        //Nao existe
	        mapaParam.put("tipoUtente", "Empresa");
	        //Nao existe
	        mapaParam.put("paticionarioNr", "12312312312");
	        //Nso existe
	        mapaParam.put("codigoArea", "02");
	        //Nao Existe
	        mapaParam.put("codigoSubArea", "04");
	        //Nao Existe
	        mapaParam.put("nrExpediente", "115502MF");
	        mapaParam.put("hora", ""+_peticaoMaritimo.getCreated().getHours());
	        //Nr Factura
	        mapaParam.put("nrFactura", "1321651IN");
	        //Fluxo
	        //chamr o verdadeiro nome
	        Pedido pe = _peticaoMaritimo.getPedido();
	        mapaParam.put("fluxo", pe.getFluxo().getNome());
	        String realPath = ex.getDesktop().getWebApp().getRealPath("/reportParam/");
	        mapaParam.put("SUBREPORT_DIR", realPath);
	   	   
	   	// Factura Secretario 
	    /*   List<EtapaFluxo> estF = _etapaFluxoService.findByFluxo(pe.getFluxo());
	   	   MasterRep.imprimir("/reportParam/facturaPedido.jrxml", estF, mapaParam, win);*/
	   	   
	   	  List<PeticaoEtapa> petE = _peticaoEtapaService.findByPeticao(_peticaoMaritimo.getPeticao());
	   	   MasterRep.imprimir("/reportParam/facturaPedido.jrxml", petE, mapaParam, win);
		
	}
	// ----------------------------------------------FIM IMPRESSAO TRAMITACAO----------------------------------------------//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------FICHAs-----------------------------------------------------------//
	
	public void onClickVerFicha(Peticao p, Window win) throws JRException{
		//EMBARCACAO
    	if(p.getTipo().equals("4") || p.getTipo().equals("5") || p.getTipo().equals("6") || p.getTipo().equals("7")|| p.getTipo().equals("8")|| p.getTipo().equals("9")|| p.getTipo().equals("10")){
			PeticaoEmbarcacao _peticaoEmbarcacao = p.getPeticaoEmbarcacao();
			verFichaEmbarcacao(_peticaoEmbarcacao, p, win);
			
		}else{
			//MARITIMMO OU INDEFINIDO
			PeticaoMaritimo _peticaoMaritimo = p.getPeticaoMaritimo();
			verFichaMaritimo(_peticaoMaritimo, p, win);
		}
   }
	
	private void verFichaEmbarcacao(PeticaoEmbarcacao _peticaoEmbarcacao, Peticao p, Window win) throws JRException {
	    Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        Utente u = _utenteService.buscarUtenteByUser(_peticaoEmbarcacao.getUser());
        //Parametros
        //Fazer query
        mapaParam.put("pedido", ""+_peticaoEmbarcacao.getPedido().getDescricao());
        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
        mapaParam.put("nomePai", ""+u.getNomePai());
        mapaParam.put("nomeMae", ""+u.getNomeMae());
        mapaParam.put("nuit",""+ u.getNuit());
        
       // int idade = new Date().getYear()-u.getDataNascimento().getYear();
        mapaParam.put("idade", ""+0);
        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
        //Nao existe
        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
        mapaParam.put("bairro", ""+u.getBairro());
        mapaParam.put("quarteirao", ""+u.getQuarteirao());
        mapaParam.put("nrCasa", ""+u.getNrCasa());
        mapaParam.put("nrTelefone", ""+u.getCelular());
        //Nao existe
        mapaParam.put("tipoUtente", "Empresa");
        //Nao existe
        mapaParam.put("paticionarioNr", p.getNrExpediente());
        //Nso existe
        mapaParam.put("codigoArea", "02");
        //Nao Existe
        mapaParam.put("codigoSubArea", "04");
        //Nao Existe
        mapaParam.put("nrFactura", p.getNrFactura());
        mapaParam.put("referencia", p.getReferencia());
        mapaParam.put("subToal", p.getValor());
        double iva = p.getValor()+0.17;
        double valorTotal = p.getValor()+iva;
        mapaParam.put("valor_pagar",valorTotal);
        mapaParam.put("iva_valor", iva);
        mapaParam.put("entidade",""+ p.getEntidade());
        
        mapaParam.put("nrExpediente", p.getNrExpediente());
        mapaParam.put("hora", ""+_peticaoEmbarcacao.getCreated().getHours());
        
        
       //Factura Utente 
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoEmbarcacao(_peticaoEmbarcacao);
   	   MasterRep.imprimir("/reportParam/facturaPedidoUtente.jrxml", _list, mapaParam, win);
		
	}


	private void verFichaMaritimo(PeticaoMaritimo _peticaoMaritimo, Peticao p,Window win) throws JRException {
	    Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        Utente u = _utenteService.buscarUtenteByUser(_peticaoMaritimo.getUser());
        //Parametros
        //Fazer query
        mapaParam.put("pedido", ""+_peticaoMaritimo.getPedido().getDescricao());
        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
        mapaParam.put("nomePai", ""+u.getNomePai());
        mapaParam.put("nomeMae", ""+u.getNomeMae());
        mapaParam.put("nuit",""+ u.getNuit());
        
       // int idade = new Date().getYear()-u.getDataNascimento().getYear();
        mapaParam.put("idade", ""+0);
        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
        //Nao existe
        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
        mapaParam.put("bairro", ""+u.getBairro());
        mapaParam.put("quarteirao", ""+u.getQuarteirao());
        mapaParam.put("nrCasa", ""+u.getNrCasa());
        mapaParam.put("nrTelefone", ""+u.getCelular());
        //Nao existe
        mapaParam.put("tipoUtente", "Empresa");
        //Nao existe
        mapaParam.put("paticionarioNr", p.getNrExpediente());
        //Nso existe
        mapaParam.put("codigoArea", "02");
        //Nao Existe
        mapaParam.put("codigoSubArea", "04");
        //Nao Existe
        mapaParam.put("nrFactura", p.getNrFactura());
        mapaParam.put("referencia", p.getReferencia());
        mapaParam.put("subToal", p.getValor());
        double iva = p.getValor()+0.17;
        double valorTotal = p.getValor()+iva;
        mapaParam.put("valor_pagar",valorTotal);
        mapaParam.put("iva_valor", iva);
        mapaParam.put("entidade",""+ p.getEntidade());
        
        mapaParam.put("nrExpediente", p.getNrExpediente());
        mapaParam.put("hora", ""+_peticaoMaritimo.getCreated().getHours());
        
        
       //Factura Utente
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoMaritimo(_peticaoMaritimo);
   	   MasterRep.imprimir("/reportParam/facturaPedidoUtente.jrxml", _list, mapaParam, win);
		
	}
	
	
	
	
	
	//------------------------------------------------FIM FICHAS---------------------------------------------------------//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------------------BUSCAS-------------------------------------------------------------------//
	
	//Tirando os comentarios em cada metodo, tereremos as buscas a serem feitas na lista que resultara da de pesquisa
	public void procurarPeloNomePedido(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		//List<Peticao> listTemp = _peticaoService.findByUtentePeticao(utentepeticao, list);
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticao(utentepeticao, list)));
		//list = listTemp;
	}

	public void procurarPeloNomePedidoValidados(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		//List<Peticao> listTemp = _peticaoService.findByUtentePeticao(utentepeticao, list);
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoValidados(list)));
		//list = listTemp;
	}
	
	public void procurarPeloNomePedidoPendentes(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		//List<Peticao> listTemp = _peticaoService.findByUtentePeticao(utentepeticao, list);
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoPendentes(list)));
		//list = listTemp;
	}
	
	
	public void procurarPeloNomePedidoRecusados(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		//List<Peticao> listTemp = _peticaoService.findByUtentePeticao(utentepeticao, list);
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoRecusados(list)));
		//list = listTemp;
	}

	public void procurarPeloNomePedidoPago(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		//List<Peticao> listTemp = _peticaoService.findByUtentePeticao(utentepeticao, list);
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoPago(list)));
		//list = listTemp;
	}

	public void procurarPeloNomePedidoNaoPago(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoNaoPago(list)));
	}
	
	public void procurarPeloNomePedidoTerminados(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoTerminados(list)));
	}
	
	public void procurarPeloNomePedidoEntregues(Listbox lbx_peticao, String utentepeticao, List<Peticao> list){
		lbx_peticao.setModel(new ListModelList<Peticao>(_peticaoService.findByUtentePeticaoEntregues(list)));
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------**************************************FIM DAS BUSCAS***************************************************--------//

}
