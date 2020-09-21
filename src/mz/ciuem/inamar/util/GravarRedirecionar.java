package mz.ciuem.inamar.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mz.ciuem.inamar.dao.PeticaoDao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.PeticaoPedidoRequisito;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.PeticaoEmbarcacaoService;
import mz.ciuem.inamar.service.PeticaoMaritimoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoPedidoRequisitoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TarefaNaEtapaService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TipoPedidoService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

@SuppressWarnings({ "serial", "rawtypes" })
public class GravarRedirecionar extends GenericForwardComposer{
	
	@WireVariable
	private AreaService _areaService;
	@WireVariable
	private SubAreaService _subAreaService;
	@WireVariable
	private TipoPedidoService _tipoPedidoService;
	@WireVariable
	private PedidoService _pedidoService;
	@WireVariable 
	private PedidoRequisitoService _pedidoRequisitoService;
	@WireVariable
	private PedidoEtapaService _pedidoIntrumentoLegalService;
	@WireVariable
	private TaxaPedidoService _taxaPedidoService;
	@WireVariable
	private TarefaNaEtapaService _tarefaEtapaService;
	@WireVariable
	private PeticaoPedidoRequisitoService _peticaoPedidoRequisitoService;
	@WireVariable
	private UserService _userService;
	@WireVariable
	private  PeticaoMaritimoService _peticaoMaritimoService;
	@WireVariable
	private PeticaoEmbarcacaoService _peticaoEmbarcacaoService;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private EtapaFluxoService _etapaFluxoService;
	
	@Autowired
	private PeticaoDao _pDao;
	
	private Include inc_main;
	private Div div_compor,div_entrada, div_content_out;
	
	Peticao peticao;
	private Utente utente;
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_areaService = (AreaService) SpringUtil.getBean("areaService");
		_tipoPedidoService = (TipoPedidoService) SpringUtil.getBean("tipoPedidoService");
		_pedidoService = (PedidoService) SpringUtil.getBean("pedidoService");
		_pedidoRequisitoService = (PedidoRequisitoService) SpringUtil.getBean("pedidoRequisitoService");
		_pedidoIntrumentoLegalService = (PedidoEtapaService) SpringUtil.getBean("pedidoEtapaService");
		_taxaPedidoService = (TaxaPedidoService) SpringUtil.getBean("taxaPedidoService");
		_tarefaEtapaService = (TarefaNaEtapaService) SpringUtil.getBean("tarefaNaEtapaService");
		_subAreaService = (SubAreaService) SpringUtil.getBean("subAreaService");
		_peticaoPedidoRequisitoService = (PeticaoPedidoRequisitoService) SpringUtil.getBean("peticaoPedidoRequisitoService");
		_userService = (UserService) SpringUtil.getBean("userService");
		_peticaoMaritimoService = (PeticaoMaritimoService) SpringUtil.getBean("peticaoMaritimoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_etapaFluxoService = (EtapaFluxoService) SpringUtil.getBean("etapaFluxoService");
		_peticaoEmbarcacaoService = (PeticaoEmbarcacaoService) SpringUtil.getBean("peticaoEmbarcacaoService");
	
		utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
		loggeduser = _userService.getUser(authentication.getName());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	public Peticao teste(Peticao p){
		p.setTipo("6");
		p.setDescricao("sdfsdf");
		p.setValor(252);
		p.setLocalizacao("Secretaria");
		p.setEntidade("0323");
		return p;
	}
	
	public  void gravar(Pedido pd, Utente utente, Listbox lbx_requisitos, Listbox lbx_taxasPedido) {
	 
		User u = utente.getUserLogin();

		// Atribui��o de C�dulas Mar�timas
		if (pd != null && pd.getId() == 4) {
			PeticaoMaritimo pm = new PeticaoMaritimo();
			pm.setPedido(pd);
			pm.setUser(u);
			_peticaoMaritimoService.create(pm);

	

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoMaritimo(pm);
			peticao.setUser(u);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() +" "+ u.getUtente().getApelido());
			peticao.setTipo("1");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pm.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pm.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(),""+ nr, "08"));
			pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoMaritimoService.update(pm);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pm.getReferencia());
			peticao.setEntidade(pm.getEntidade());
			_peticaoService.update(peticao);

			pm.setPeticao(peticao);
			_peticaoMaritimoService.update(pm);

			Executions.getCurrent().getSession()
					.setAttribute("ss_peticaoMaritimo", pm);
			div_content_out.detach();
			inc_main.setSrc("/views/Maritimo/emissaoCedulaMaritima.zul");

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
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("3");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pm.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pm.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;
			pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoMaritimoService.update(pm);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pm.getReferencia());
			_peticaoService.update(peticao);

			pm.setPeticao(peticao);
			_peticaoMaritimoService.update(pm);

			Executions.getCurrent().getSession()
					.setAttribute("ss_peticaoMaritimo", pm);
			div_content_out.detach();
			inc_main.setSrc("/views/Maritimo/averbamentoCedulaMaritima.zul");
		}
		// Pedido de Registo de uma Embarca��o importada
		else if (pd != null && pd.getId() == 17) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);
			_peticaoEmbarcacaoService.saveOrUpdate(pe);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("4");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido().getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido().getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoEmbarcacaoService.update(pe);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			_peticaoService.update(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.update(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/embarcacaoImportada.zul");
		}

		// Emiss�o de Licen�a de constru��o duma embarca��o
		else if (pd != null && pd.getId() == 18) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			_peticaoEmbarcacaoService.create(pe);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("5");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido()
					.getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""
					+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido()
					.getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoEmbarcacaoService.update(pe);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			_peticaoService.update(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.update(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/emissaoLicencaConstrucaoEmbarcacao.zul");
		}

		// Registo de uma embarcacao acabada de construir
		else if (pd != null && pd.getId() == 19) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			_peticaoEmbarcacaoService.create(pe);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			if (u.getUtente() != null)peticao.setUtente(u.getUtente().getNome() + " "+ u.getUtente().getApelido());
			peticao.setTipo("6");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido()
					.getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""
					+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido()
					.getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoEmbarcacaoService.update(pe);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			_peticaoService.update(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.update(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/registoEmbarcacaoAcabadaConstruir.zul");
		}

		// Cancelamento de registo de uma embarcacao por ter sido vendida
		else if (pd != null && pd.getId() == 20) {
			PeticaoEmbarcacao pe = new PeticaoEmbarcacao();
			pe.setPedido(pd);
			pe.setUser(u);

			_peticaoEmbarcacaoService.create(pe);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoEmbarcacao(pe);
			peticao.setUser(u);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("7");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pe.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setEntidade(pe.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pe.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido()
					.getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""
					+ nr, "08"));
			pe.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido()
					.getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoEmbarcacaoService.update(pe);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pe.getReferencia());
			_peticaoService.update(peticao);

			pe.setPeticao(peticao);
			_peticaoEmbarcacaoService.update(pe);

			Executions.getCurrent().getDesktop().getSession()
					.setAttribute("p", peticao);
			div_content_out.detach();
			inc_main.setSrc("/views/Embarcacao/cancelamentoRegistoEmbarcacaoVendida.zul");
		}
		// Provisorio
		else {
			// Criar outro tipio de peticao aqui
			PeticaoMaritimo pm = new PeticaoMaritimo();
			pm.setPedido(pd);
			pm.setUser(u);

			_peticaoMaritimoService.create(pm);

			// Gerar Peticao (temporario)
			Peticao peticao = new Peticao();
			peticao.setPeticaoMaritimo(pm);
			peticao.setUser(u);
			if (u.getUtente() != null)
				peticao.setUtente(u.getUtente().getNome() + " "
						+ u.getUtente().getApelido());
			peticao.setTipo("2");
			peticao.setDescricao(pd.getDescricao());
			peticao.setValor(pm.getValor());
			peticao.setLocalizacao("Secretaria");
			peticao.setReferencia(pm.getReferencia());
			peticao.setEntidade(pm.getEntidade());

			_peticaoService.saveOrUpdate(peticao);
			long nr = peticao.getId() + 10000;

			pm.setReferencia(Gerador.gerarReferencia(pd.getTipoPedido()
					.getArea().getCodigo(), pd.getTipoPedido().getCodigo(), ""
					+ nr, "08"));
			pm.setEntidade(Gerador.gerarEntidade("700", pd.getTipoPedido()
					.getCodigo()));
			// Gerar REquisitos
			gerarPedidoRequisitos(lbx_requisitos.getItems(), peticao);
			// Gerar Instrumentos Legais .. . . Em falta
			// Gerar Taxas
			gerarPedidoTaxas(lbx_taxasPedido.getItems(), peticao);
			_peticaoMaritimoService.update(pm);

			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("ddMMyy");
			String f = formatador.format(data);
			String nrFact = "" + nr + "" + f;
			peticao.setNrFactura("" + nrFact);
			peticao.setNrExpediente("" + nr);
			peticao.setReferencia(pm.getReferencia());
			_peticaoService.update(peticao);

			pm.setPeticao(peticao);
			_peticaoMaritimoService.update(pm);

			Executions.getCurrent().getSession()
					.setAttribute("ss_peticaoMaritimo", pm);
			div_content_out.detach();
			inc_main.setSrc("/views/Maritimo/peticaoGeralEmpty.zul");
		}
	}
	
	
	public void gerarPedidoRequisitos(List<Listitem> listI, Peticao pm){
		for (Listitem listitem : listI) {
			PeticaoPedidoRequisito ppr = new PeticaoPedidoRequisito();
			ppr.setPedidoRequisito((PedidoRequisito) listitem.getValue());
			ppr.setPeticao(pm);
			_peticaoPedidoRequisitoService.saveOrUpdate(ppr);
		}
	}
	
	public void gerarPedidoRequisitosEmbarcacao(List<Listitem> listI, Peticao pe){
		for (Listitem listitem : listI) {
			PeticaoPedidoRequisito ppr = new PeticaoPedidoRequisito();
			ppr.setPedidoRequisito((PedidoRequisito) listitem.getValue());
			ppr.setPeticao(pe);
			_peticaoPedidoRequisitoService.saveOrUpdate(ppr);
		}
	}
	
	public void gerarPedidoTaxas(List<Listitem> listI, Peticao pm){
		double valor=0;
		for (Listitem listitem : listI) {
	    	PeticaoMaritimoTaxaPedido _pmtp = new PeticaoMaritimoTaxaPedido();
	    	TaxaPedido tp = (TaxaPedido)listitem.getValue();
	    	_pmtp.setTaxaPedido(tp);
	    	valor+=tp.getTaxa().getValor();
	    	_pmtp.setPeticao(pm);
	    	_peticaoMaritimoTaxaPedidoService.saveOrUpdate(_pmtp);
		}
		
		pm.setValor(valor);
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

}



