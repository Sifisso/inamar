package mz.ciuem.inamar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.entity.Etapa;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.Tarefa;
import mz.ciuem.inamar.entity.TarefaNaEtapa;
import mz.ciuem.inamar.service.EstadoService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.EtapaService;
import mz.ciuem.inamar.service.FluxoService;
import mz.ciuem.inamar.service.TarefaNaEtapaService;
import mz.ciuem.inamar.service.TarefaService;

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

public class TarefaNaEtapaCtrl extends GenericForwardComposer{
	
	//Main Div
	private Listbox lbx_tarefaNaEtapa;
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Button btn_imprimir;
	
	//Modal Div
	private Combobox cbx_tarefa;
	private Textbox txb_descricao;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Label lbl_descricao, lbl_descricao2;
	
	
	private Window win_regTarefaEtapa;
	
	Execution ex = Executions.getCurrent();
	
	private EtapaFluxo _eFluxo;
	
	private TarefaNaEtapa _tarefaNaEtapa; 
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	@WireVariable
	private EtapaFluxoService _etapaFluxoService;
	@WireVariable
	private TarefaService _tarefaService;
	@WireVariable
	private TarefaNaEtapaService _tarefaNaEtapaService;
	
    private List<Tarefa> listTarefa= new ArrayList<Tarefa>();
    private List<TarefaNaEtapa> listTarenaNaEtapa = new ArrayList<TarefaNaEtapa>();
	
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_etapaFluxoService = (EtapaFluxoService) SpringUtil.getBean("etapaFluxoService");
   		_tarefaService = (TarefaService) SpringUtil.getBean("tarefaService");
   		_tarefaNaEtapaService = (TarefaNaEtapaService) SpringUtil.getBean("tarefaNaEtapaService");
   		_eFluxo = (EtapaFluxo) ex.getArg().get("_eFluxo");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   		listarTarefa();
   		preencherCabecalho();
   	}
   	

/*  public void onClickprcurar(ForwardEvent e)  {
        String nome = txb_nomefind.getValue();
        boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
        findByNomeIsActivo(nome,isActivo);
   	}*/
   
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   		_tarefaNaEtapa.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
   		_tarefaNaEtapa.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
   		_tarefaNaEtapa.setTarefa((Tarefa) cbx_tarefa.getSelectedItem().getValue());
   		_tarefaNaEtapa.setEtapaFluxo(_eFluxo);
   		_tarefaNaEtapa.setDescricao(txb_descricao.getValue());

        
   		_tarefaNaEtapaService.update(_tarefaNaEtapa);
   		showNotifications("Tarefa na Etapa actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		TarefaNaEtapa _tne = new TarefaNaEtapa();
   	    
   		_tne.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_tne.setTarefa((Tarefa) cbx_tarefa.getSelectedItem().getValue());
   		_tne.setEtapaFluxo(_eFluxo);
   		_tne.setDescricao(txb_descricao.getValue());
   		
   		_tarefaNaEtapaService.create(_tne);
   		showNotifications("Tarefa na Etapa registada com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_tarefaNaEtapa(Event e){
   		_tarefaNaEtapa = lbx_tarefaNaEtapa.getSelectedItem().getValue();
   		cbx_tarefa.setValue(_tarefaNaEtapa.getTarefa().getDescricao());
   		txb_descricao.setValue(_tarefaNaEtapa.getDescricao());
   	    rbx_actNao.setChecked(!_tarefaNaEtapa.isActivo());
   	    rbx_actSim.setChecked(_tarefaNaEtapa.isActivo());
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
   	}
   	
  	public void findByNomeIsActivo(String nome, boolean isActivo){
   		listEtapaFluxo = _etapaFluxoService.findByNomeActivo(nome, isActivo, _fluxo);
   		lbx_etapaFluxo.setModel(new ListModelList<EtapaFluxo>(listEtapaFluxo));
   	}*/
   	
   	private void limparCampos() {
   		cbx_tarefa.setRawValue(null);
   		txb_descricao.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_eFluxo.getDescricao());
		lbl_descricao2.setValue(_eFluxo.getDescricao());
	}
   	
   	private void listar() {
   		listTarenaNaEtapa = _tarefaNaEtapaService.findByEtapaFluxo(_eFluxo);
   		lbx_tarefaNaEtapa.setModel(new ListModelList<TarefaNaEtapa>(listTarenaNaEtapa));
   	}
   	
   	private void listarTarefa(){
   		listTarefa = _tarefaService.findNotInEtapaFluxo(_eFluxo);
   		cbx_tarefa.setModel(new ListModelList<Tarefa>(listTarefa));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_tarefaNaEtapa,"before_center", 4000, true);
   	}


}
