package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoConfigReport;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.service.CategoriaService;
import mz.ciuem.inamar.service.FluxoService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TipoPedidoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class PedidoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Textbox txb_nomeFluxofind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_Pedido;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_descricao;
	private Textbox txb_codigo;
	private Label lbl_codigo;
	private Combobox cbx_fluxo;
	private Radio rbx_actSim, rbx_mostraSim, rbx_mostraNao;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Label lbl_descricao, lbl_descricao2;
	
	
	private Window win_regPedido;
	
	Execution ex = Executions.getCurrent();
	
	private Pedido _pedido;
	
	private TipoPedido _tipoPedido;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	private boolean tipoLicenca=false;
	
	
	@WireVariable
	private FluxoService _fluxoService;
	
	@WireVariable
	private PedidoService _pedidoService;
	@WireVariable
	private PedidoRequisitoService _pedidoRequisitoService;
	@WireVariable
	private TaxaPedidoService _taxaPedidoService;
	@WireVariable
	private PedidoEtapaService _pedidoEtapaService;
	
	
    private List<Fluxo> listFluxo = new ArrayList<Fluxo>();
    private List<Pedido> listPedido = new ArrayList<Pedido>();
    
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_fluxoService = (FluxoService) SpringUtil.getBean("fluxoService");
   		_pedidoService = (PedidoService) SpringUtil.getBean("pedidoService");
   		_pedidoRequisitoService = (PedidoRequisitoService) SpringUtil.getBean("pedidoRequisitoService");
   		_taxaPedidoService = (TaxaPedidoService) SpringUtil.getBean("taxaPedidoService");
   		_pedidoEtapaService = (PedidoEtapaService) SpringUtil.getBean("pedidoEtapaService");
   		
   		_tipoPedido = (TipoPedido) ex.getArg().get("_tipoPedido");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   		preencherCabecalho();
   		verTipoPedido();
   	}
   	
   public void verTipoPedido(){
		if(_tipoPedido.getArea().getId()==5){
   			tipoLicenca=true;
   		}else{
   			tipoLicenca=false;
   		}
   		
   }
   	
   public void onClickConfig(ForwardEvent e)  {
	   if(tipoLicenca){
		Pedido _pedido = (Pedido) e.getData();
		boolean geral = false;
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_pedido", _pedido);
		map.put("_geral", geral);
		win_regPedido.getChildren().clear();
		Executions.createComponents("/views/Pedido/configurar_pedido.zul", win_regPedido, map);
	   }else{
		   Messagebox.show("A configuracao Especifica ainda nao esta disponivel para pedidos desta area");
	   }
	}
   
   public void onClickConfigGEral(ForwardEvent e)  {
		Pedido _pedido = (Pedido) e.getData();
		boolean geral = true;
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_pedido", _pedido);
		map.put("_geral", geral);
		win_regPedido.getChildren().clear();
		Executions.createComponents("/views/Pedido/configurar_pedidoGeral.zul", win_regPedido, map);
	}
   
   
   public void onClickPrint(ForwardEvent e) throws JRException  {
		Pedido _pedido = (Pedido) e.getData();
		PedidoConfigReport pcr = new PedidoConfigReport();
		_pedido.copyToPedidoResport(pcr, _pedidoRequisitoService, _taxaPedidoService, _pedidoEtapaService);
	    List<PedidoConfigReport> listPedidoPrint = new ArrayList<PedidoConfigReport>();
	    listPedidoPrint.add(pcr);
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        String realPath = ex.getDesktop().getWebApp().getRealPath("/reportParam/");
        mapaParam.put("SUBREPORT_DIR", realPath);      
		mapaParam.put("listNome", _tipoPedido.getNome());
   		MasterRep.imprimir("/reportParam/reportPedidoConfigurado.jrxml", listPedidoPrint, mapaParam, win_regPedido);
		
	}


     public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                String nomeFluxo = txb_nomeFluxofind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomenNomeNomeFluxoIsActivo(nome, nomeFluxo, isActivo, _tipoPedido);
   	 }
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   		_pedido.setActivo(rbx_actSim.isChecked() ? true : false);
   		_pedido.setMostraFactura(rbx_mostraSim.isChecked() ? true : false);
   		_pedido.setTipoPedido(_tipoPedido);
   		_pedido.setFluxo((Fluxo) cbx_fluxo.getSelectedItem().getValue());
   		_pedido.setDescricao(txb_descricao.getValue());
   		_pedido.setCodigo(lbl_codigo.getValue()+""+txb_codigo.getValue());

        	_pedidoService.update(_pedido);
       		showNotifications("Pedido actualizado com sucesso!", "info");
       		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e){

   		Pedido _p = new Pedido();
   	    
   		_p.setActivo(rbx_actSim.isChecked() ? true : false);
   		_p.setMostraFactura(rbx_mostraSim.isChecked() ? true : false);
   		_p.setTipoPedido(_tipoPedido);
   		_p.setFluxo((Fluxo) cbx_fluxo.getSelectedItem().getValue());
   		_p.setDescricao(txb_descricao.getValue());
   		_p.setCodigo(lbl_codigo.getValue()+""+txb_codigo.getValue());
   		
   		boolean existe = false;
   		
   		for (Pedido pedido: listPedido ){
   			
   			if (pedido.getCodigo().equals(_p.getCodigo())){
   				existe=true;
   			}
   		}
   		
   		if(existe==false){
   			_pedidoService.saveOrUpdate(_p);
   	   		showNotifications("Pedido registado com sucesso!", "info");
   	   		limparCampos();
   		}else{
   			showNotifications("O código introduzido já existe!", "error");
   		}
   		
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_Pedido(Event e){
   		_pedido = lbx_Pedido.getSelectedItem().getValue();
   		txb_descricao.setValue(_pedido.getDescricao());
   		String cod1 = ""+_pedido.getCodigo().charAt(2);
   		String cod2 = ""+_pedido.getCodigo().charAt(3);
   		txb_codigo.setValue(cod1+cod2);
   		cbx_fluxo.setValue(_pedido.getFluxo().getNome());
   	    rbx_actNao.setChecked(!_pedido.isActivo());
   	    rbx_actSim.setChecked(_pedido.isActivo());
   	    rbx_mostraSim.setChecked(_pedido.isMostraFactura());
   	    rbx_mostraNao.setChecked(!_pedido.isMostraFactura());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{

   	    List<PedidoConfigReport> listPedidoPrint = new ArrayList<PedidoConfigReport>();
   		
   	    for (Pedido _pedido: listPedido) {
	   	 	PedidoConfigReport pcr = new PedidoConfigReport();
			_pedido.copyToPedidoResport(pcr, _pedidoRequisitoService, _taxaPedidoService, _pedidoEtapaService);
		    listPedidoPrint.add(pcr);
		}
	
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        String realPath = ex.getDesktop().getWebApp().getRealPath("/reportParam/");
        mapaParam.put("SUBREPORT_DIR", realPath);      
		mapaParam.put("listNome", _tipoPedido.getNome());
   		MasterRep.imprimir("/reportParam/reportPedidoConfigurado.jrxml", listPedidoPrint, mapaParam, win_regPedido);
   	}
   	
  	public void findByNomenNomeNomeFluxoIsActivo(String nome, String nomeFluxo,  boolean isActivo, TipoPedido tipoPedido){
   		listPedido = _pedidoService.findByNomenNomeNomeFluxoIsActivo(nome, nomeFluxo, isActivo, tipoPedido);
   		lbx_Pedido.setModel(new ListModelList<Pedido>(listPedido));
   	}
   	
   	private void limparCampos() {
   		cbx_fluxo.setRawValue(null);
   		txb_descricao.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		rbx_actSim.setChecked(false);
   		rbx_mostraSim.setChecked(true);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_tipoPedido.getNome());
		lbl_descricao2.setValue(_tipoPedido.getNome());
		lbl_codigo.setValue(_tipoPedido.getArea().getCodigo());
	}
   	
   	private void listar() {
   		listPedido = _pedidoService.findByTipoPedido(_tipoPedido);
   		lbx_Pedido.setModel(new ListModelList<Pedido>(listPedido));
   		listarFluxo();
   	}
   	
   	public void listarFluxo(){
   		//listFluxo = _fluxoService.finByNotInPedido(_tipoPedido);
   		listFluxo = _fluxoService.getAll();
   		cbx_fluxo.setModel(new ListModelList<Fluxo>(listFluxo));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_Pedido,"before_center", 4000, true);
   	}


}
