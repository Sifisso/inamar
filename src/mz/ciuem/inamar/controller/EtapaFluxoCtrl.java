package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.entity.Etapa;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.EstadoService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.EtapaService;
import mz.ciuem.inamar.service.FluxoService;
import mz.ciuem.inamar.service.SubAreaService;
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
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EtapaFluxoCtrl extends GenericForwardComposer{
	
	//Main Div
		private Textbox txb_nomefind;
		private Radio rbx_Simfin;
		private Radio rbx_Naofind;
		private Button btn_procurar;
		private Listbox lbx_etapaFluxo;
		private Button btn_imprimir;
		
		//Modal Div
		private Combobox cbx_etapa, cbx_estado;
		private Textbox txb_descricao;
		private Radio rbx_actSim;
		private Radio rbx_actNao;
		private Button btn_gravar;
		private Button btn_actualizar;
		private Button btn_cancelar;
		
		private Label lbl_descricao, lbl_descricao2;
		
		
		private Window win_regEtapaFluxo;
		
		Execution ex = Executions.getCurrent();
		
		private Fluxo _fluxo;
		
		private EtapaFluxo _etapaFluxo;
		
		@Wire("#mainlayout")
		private Div target;
		
		@Wire("#breadcrumb")
		private Ol ol;
		
		
		@WireVariable
		private FluxoService _fluxoService;
		@WireVariable
		private EtapaFluxoService _etapaFluxoService;
		@WireVariable
		private EstadoService _estadoService;
		@WireVariable
		private EtapaService _etapaService;
		
	    private List<EtapaFluxo> listEtapaFluxo = new ArrayList<EtapaFluxo>();
	    private List<Etapa> listEtapa = new ArrayList<Etapa>();
	    private List<Estado> listEstado = new ArrayList<Estado>();
	    
	    @SuppressWarnings("unchecked")
	   	@Override
	   	public void doBeforeComposeChildren(Component comp) throws Exception {
	   		super.doBeforeComposeChildren(comp);
	   		
	   		_fluxoService = (FluxoService) SpringUtil.getBean("fluxoService");
	   		_etapaFluxoService = (EtapaFluxoService) SpringUtil.getBean("etapaFluxoService");
	   		_estadoService = (EstadoService) SpringUtil.getBean("estadoService");
	   		_etapaService = (EtapaService) SpringUtil.getBean("etapaService");
	   		_fluxo = (Fluxo) ex.getArg().get("_fluxo");
	   		
	   	}

	   	@SuppressWarnings("unchecked")
	   	@Override
	   	public void doAfterCompose(Component comp) throws Exception {
	   		// TODO Auto-generated method stub
	   		super.doAfterCompose(comp);

	   		listar();
	   		preencherCabecalho();
	   	}
	   	

	   public void onClickprcurar(ForwardEvent e)  {
            String nome = txb_nomefind.getValue();
            boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
            findByNomeIsActivo(nome,isActivo);
	   	}
	   
		public void onClickConfig(ForwardEvent e)  {
			EtapaFluxo eFluxo = (EtapaFluxo) e.getData();
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("target", target);
			map.put("breadcrumb", ol);
			map.put("_eFluxo", eFluxo);
			win_regEtapaFluxo.getChildren().clear();
			Executions.createComponents("/views/Parametrizacao/registar_tarefaNaEtapa.zul", win_regEtapaFluxo, map); 
			
		}
	   	
	   	public void onClick$btn_actualizar() throws InterruptedException {
	   		
	   		_etapaFluxo.setActivo(rbx_actSim.isChecked() ? true : false);
       	    
	   		_etapaFluxo.setEstado((Estado)cbx_estado.getSelectedItem().getValue());
	   		_etapaFluxo.setEtapa((Etapa)cbx_etapa.getSelectedItem().getValue());
	   		_etapaFluxo.setDescricao(txb_descricao.getValue());
	   		_etapaFluxo.setFluxo(_fluxo);

	        
	   		_etapaFluxoService.update(_etapaFluxo);
	   		showNotifications("Etapa do Fluxo actualizada com sucesso!", "info");
	   		limparCampos();

	   	}

	   	public void onClick$btn_gravar(Event e) throws InterruptedException{

	   		EtapaFluxo _etF = new EtapaFluxo();
	   	    
	   		_etF.setActivo(rbx_actSim.isChecked() ? true : false);
	           	    
	   		_etF.setEstado((Estado)cbx_estado.getSelectedItem().getValue());
	   		_etF.setEtapa((Etapa)cbx_etapa.getSelectedItem().getValue());
	   		_etF.setDescricao(txb_descricao.getValue());
	   		_etF.setFluxo(_fluxo);
	   		
	   		_etapaFluxoService.create(_etF);
	   		showNotifications("Etapa do Fluxo registada com sucesso!", "info");
	   		limparCampos();
	   	}

	   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
	   	
	   	public void onSelect$lbx_etapaFluxo(Event e){
	   		_etapaFluxo = lbx_etapaFluxo.getSelectedItem().getValue();
	   		cbx_estado.setValue(_etapaFluxo.getEstado().getNome());
	   		cbx_etapa.setValue(_etapaFluxo.getEtapa().getNome());
	   		txb_descricao.setValue(_etapaFluxo.getDescricao());
	   	    rbx_actNao.setChecked(!_etapaFluxo.isActivo());
	   	    rbx_actSim.setChecked(_etapaFluxo.isActivo());
	   		btn_actualizar.setVisible(true);
	   		btn_gravar.setVisible(false);
	   	}
	   	
	  /* 	public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
	           mapaParam.put("imagemLogo", inputV);
	           mapaParam.put("listNome",_e.getNome());
	   		MasterRep.imprimir("/reportParam/reportSubArea.jrxml", listSubArea, mapaParam, win_regSubArea);
	   	}*/
	   	
	  	public void findByNomeIsActivo(String nome, boolean isActivo){
	   		listEtapaFluxo = _etapaFluxoService.findByNomeActivo(nome, isActivo, _fluxo);
	   		lbx_etapaFluxo.setModel(new ListModelList<EtapaFluxo>(listEtapaFluxo));
	   	}
	   	
	   	private void limparCampos() {
	   		cbx_estado.setRawValue(null);
	   		cbx_etapa.setRawValue(null);
	   		txb_descricao.setRawValue(null);
	   		rbx_actSim.setChecked(false);
	   	    rbx_actNao.setChecked(true);
	   		btn_gravar.setVisible(true);
	   		btn_actualizar.setVisible(false);
	   		listar();
	   		
	   	}
	   	
		private void preencherCabecalho() {
			lbl_descricao.setValue(_fluxo.getNome());
			lbl_descricao2.setValue(_fluxo.getNome());
		}
	   	
	   	private void listar() {
	   		//Filtrar
	   		listEtapaFluxo = _etapaFluxoService.findByFluxo(_fluxo);
	   		lbx_etapaFluxo.setModel(new ListModelList<EtapaFluxo>(listEtapaFluxo));
	   		listarEtapaEstado();
	   	}
	   	
	   	private void listarEtapaEstado(){
	   		listEtapa = _etapaService.getAll();
	   		cbx_etapa.setModel(new ListModelList<Etapa>(listEtapa));
	   		listEstado = _estadoService.getAll();
	   		cbx_estado.setModel(new ListModelList<Estado>(listEstado));
	   	}
	   	
	   	
	   	public void showNotifications(String message, String type) {
	   		Clients.showNotification(message, type, lbx_etapaFluxo,"before_center", 4000, true);
	   	}


}
