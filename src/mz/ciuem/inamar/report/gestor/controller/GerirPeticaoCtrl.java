package mz.ciuem.inamar.report.gestor.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.PeticaoPedidoRequisito;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
import mz.ciuem.inamar.service.PedidoService;
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
import mz.ciuem.inamar.util.Gerador;
import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GerirPeticaoCtrl extends GenericForwardComposer{

	private Div div_compor,div_entrada, div_content_out;
	private Combobox cbx_area,cbx_tipoPedido,cbx_pedido, cbx_utente;
	private Listbox lbx_requisitos, lbx_taxasPedido, lbx_instLegalPedido, lbx_peticao;
	private Button btn_proximo;
	private Include inc_main;
	private Window win_regPedidoExpediente;
	private Textbox txb_nomefind;
	
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
	private PeticaoMaritimoService _peticaoMaritimoService;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private EtapaFluxoService _etapaFluxoService;
	
	Peticao peticao;
	private Utente utente;
	protected User loggeduser;
	private List<Peticao> listPeticao = new ArrayList<Peticao>();
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	Gerador gerador = new Gerador();
	
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
	
		utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
		loggeduser = _userService.getUser(authentication.getName());
		
		_peticaoService.iniciar();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		//listarUtente();
		//listarArea();
		listarPeticao();
	}
	
	public void onClickDarParecer(ForwardEvent e){
		Peticao _pet = (Peticao) e.getData();
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/AdministradorMaritimo/tratar_peticao.zul", win_regPedidoExpediente, mapContaReceber);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Pedidos da Secretaria");
        @SuppressWarnings("unchecked")
		List<Peticao> l = (List<Peticao>) lbx_peticao.getListModel();
   		MasterRep.imprimir("/reportParam/desempenho_por_admar.jrxml", listPeticao, mapaParam, win_regPedidoExpediente);
   	}
	
	
	public void onClickTratar(ForwardEvent e){
		Peticao _pet = (Peticao) e.getData();
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/AdministradorMaritimo/tratar_peticaoGeral.zul", win_regPedidoExpediente, mapContaReceber);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickPedirParecer(final ForwardEvent e) {
		Messagebox.show("Deseja submeter a Parecer da Secção Técnica?", "Parecer",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Peticao _pet = (Peticao) e.getData();
					_pet.setPedeParecer(true);
					_pet.setAdmMaritima(false);
					_pet.setSeccaoTecnica(true);
					_pet.setSeccaoTecnicaParecer(true);
					_pet.setLocalizacao("Secção Técnica");
					_peticaoService.saveOrUpdate(_pet);
					Clients.showNotification("Parecer Pedido Com Sucesso", "info", lbx_peticao, "before_center", 4000, true);
					listarPeticao();
		         
				}
			}
		});
		
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickPedirParecer2(final ForwardEvent e) {
		Messagebox.show("Deseja submeter a Parecer da Chefe de Secretaria?", "Parecer",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Peticao _pet = (Peticao) e.getData();
					_pet.setPedeParecer(true);
					_pet.setAdmMaritima(false);
					_pet.setChefeSecretaria(true);
					_pet.setChefeSecretariaParecer(true);
					_pet.setLocalizacao("Chefe de Secretaria (Parecer)");
					_peticaoService.saveOrUpdate(_pet);
					Clients.showNotification("Parecer Pedido Com Sucesso", "info", lbx_peticao, "before_center", 4000, true);
					listarPeticao();
		         
				}
			}
		});
		
	}
	
	public void onClickCinfirmar(ForwardEvent e){
		Peticao _pet = (Peticao) e.getData();
		Map<String, Object> mapContaReceber = new HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet);
		Executions.createComponents("/views/Tesouraria/confirmar_recepcao.zul", win_regPedidoExpediente, mapContaReceber);
	}
	
	private void listarUtente() {
		if(utente==null){
		List<Utente> listu = _utenteService.getAll();
		cbx_utente.setModel(new ListModelList<Utente>(listu));
		}else{
			onClickCompor(null);
			cbx_utente.setValue(utente.getNome()+utente.getApelido());
		}
	}

	private void listarPeticao() {
		// TODO Auto-generated method stub
		//Buscar do user logado
		listPeticao = _peticaoService.getAllOrderDesc();
		lbx_peticao.setModel(new ListModelList<Peticao>(listPeticao));
	}
	
	public void onSelect$cbx_area(){
		cbx_pedido.setRawValue(null);
		cbx_tipoPedido.setRawValue(null);
		cbx_pedido.getItems().clear();
		cbx_tipoPedido.getItems().clear();
		List<TipoPedido> listTP = _tipoPedidoService.findByArea((Area)cbx_area.getSelectedItem().getValue());
		cbx_tipoPedido.setModel(new ListModelList<TipoPedido>(listTP));
		
		/*cbx_subArea.setRawValue(null);
		cbx_subArea.getItems().clear();
		List<SubArea> listSubArea = _subAreaService.findByArea((Area)cbx_area.getSelectedItem().getValue());
		cbx_subArea.setModel(new ListModelList<SubArea>(listSubArea));*/
	}
	
	public void onSelect$cbx_tipoPedido(){
		cbx_pedido.getItems().clear();
		List<Pedido> listP = _pedidoService.findByTipoPedido((TipoPedido) cbx_tipoPedido.getSelectedItem().getValue());
		cbx_pedido.setModel(new ListModelList<Pedido>(listP));;
	}
	
	public void onSelect$cbx_pedido(){
		List<PedidoRequisito> _listPR = _pedidoRequisitoService.findByPedidoVisivelParaUtente((Pedido) cbx_pedido.getSelectedItem().getValue());
		lbx_requisitos.setModel(new ListModelList<PedidoRequisito>(_listPR));
		
		//_pedidoIntrumentoLegalService
		/*List<PedidoEtapa> listPE = _pedidoIntrumentoLegalService.findByPedido((Pedido) cbx_pedido.getSelectedItem().getValue());
	    lbx_instLegalPedido.setModel(new ListModelList<PedidoEtapa>(listPE));*/
		
		//_taxaPedidoService
	    List<TaxaPedido> listTP = _taxaPedidoService.findByPedido((Pedido) cbx_pedido.getSelectedItem().getValue());
	    lbx_taxasPedido.setModel(new ListModelList<TaxaPedido>(listTP));
		
		//_tarefaEtapaService
	  /*  List<TarefaNaEtapa> listTE = _tarefaEtapaService.findByPedido((Pedido) cbx_pedido.getSelectedItem().getValue());
	    lbx_tarefaPedido.setModel(new ListModelList<TarefaNaEtapa>(listTE));*/
	}
	
	public void onClickCompor(final ForwardEvent e){
		if(div_compor.isVisible()){
			div_compor.setVisible(false);
			div_entrada.setVisible(true);
		}else{
			div_entrada.setVisible(false);
			div_compor.setVisible(true);
		}
	}
	
	public void onClick$btn_proximo(){
		gravar();
		Executions.getCurrent().getSession().removeAttribute("ss_utente");
	}
	
	
	private void gravar() {
		if(utente==null)utente = (Utente)cbx_utente.getSelectedItem().getValue();
	//	_peticaoService.gravarRedicionar((Pedido)cbx_pedido.getSelectedItem().getValue(), utente, lbx_requisitos, lbx_taxasPedido, inc_main, div_content_out);
	}

	
	public void onClickDetalhes(ForwardEvent e){
		Peticao pet = (Peticao) e.getData();
		_peticaoService.onClickDetalhes(pet, inc_main, div_content_out);
	
	}
	
	public void onClickVerTramitacao(ForwardEvent e) throws JRException{
		Peticao p = (Peticao) e.getData();
		_peticaoService.onClickVerTramitacao(p, win_regPedidoExpediente);
	}
	
	public void onClickVerFactura(ForwardEvent e) throws JRException{
	    	Peticao p = (Peticao) e.getData();
	    	_peticaoService.onClickVerFactura(p, win_regPedidoExpediente);
	}
	
	private void listarArea() {
		List<Area> listArea = _areaService.getAll();
		cbx_area.setModel(new ListModelList<Area>(listArea));
	}

	
	//*******************************************PESQUISAS*********************************************//
	
	@SuppressWarnings({ "unchecked" })
	public void onClickCancelarProcuar(final ForwardEvent e) {
		listarPeticao();
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtente(final ForwardEvent e) {
		_peticaoService.procurarPeloNomePedido(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtenteValidados(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoValidados(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtentePendente(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoPendentes(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtenteRecusado(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoRecusados(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtentePago(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoPago(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtenteNaoPago(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoNaoPago(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtenteTerminados(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoTerminados(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public void onClickProcuarPeticaoUtenteEntregues(final ForwardEvent e) {
		verificar();
		_peticaoService.procurarPeloNomePedidoEntregues(lbx_peticao, txb_nomefind.getValue(), listPeticao);
	}
	
	
	public void verificar(){
		if(txb_nomefind.getValue()==null && txb_nomefind.getValue()=="")listarPeticao();
	}
	
	
	
	
	
	
	
	//***************************************FIM DAS PESQUISAS***************************************//

		

}
