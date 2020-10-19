package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.entity.Tarefa;
import mz.ciuem.inamar.service.EstadoService;
import mz.ciuem.inamar.service.TarefaService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TarefaCtrl extends GenericForwardComposer{

	//Main Div
		private Textbox txb_nomefind;
		private Radio rbx_Simfin;
		private Radio rbx_Naofind;
		private Button btn_procurar;
		private Listbox lbx_tarefa;
		private Button btn_imprimir;
		
		//Modal Div
		private Textbox txb_nome;
		private Textbox txb_emolumentofind;
		private Radio rbx_actSim;
		private Radio rbx_actNao;
		private Button btn_gravar;
		private Button btn_actualizar;
		private Button btn_cancelar;
		
		
		private Window win_regTarefa;
		
		Execution ex = Executions.getCurrent();
		
		private Tarefa _tarefa;
		
		@WireVariable
		private TarefaService _tarefaService;
		
	    private List<Tarefa> listTarefa = new ArrayList<Tarefa>();
	    
	    
	    @SuppressWarnings("unchecked")
	   	@Override
	   	public void doBeforeComposeChildren(Component comp) throws Exception {
	   		super.doBeforeComposeChildren(comp);
	   		
	   		_tarefaService = (TarefaService) SpringUtil.getBean("tarefaService");
	   		
	   	}

	   	@SuppressWarnings("unchecked")
	   	@Override
	   	public void doAfterCompose(Component comp) throws Exception {
	   		// TODO Auto-generated method stub
	   		super.doAfterCompose(comp);

	   		listar();
	   	}


	 public void onClickprcurar(ForwardEvent e)  {
	        String nome = txb_nomefind.getValue();
	       double emolumento =Double.parseDouble(txb_emolumentofind.getText()); 
	        boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
	        findByNomeIsActivo(nome,isActivo);
	   	}
	 
	   	public void onClick$btn_actualizar() throws InterruptedException {
	   		
	        _tarefa.setActivo(rbx_actSim.isChecked() ? true : false);
	   	    
	        _tarefa.setDescricao(txb_nome.getValue());
	      //  _tarefa.setEmolumento(txb_emolumentofind.getValue());
	        
	   		_tarefaService.update(_tarefa);
	   		showNotifications("Estado actualizado com sucesso!", "info");
	   		limparCampos();
	   	}

	   	public void onClick$btn_gravar(Event e) throws InterruptedException{

	   		Tarefa _taref = new Tarefa();
	   		
	   		_taref.setActivo(rbx_actSim.isChecked() ? true : false);
	   		_taref.setDescricao(txb_nome.getValue());
	   		_taref.setEmolumento(txb_emolumentofind.getValue());
	   		
	   		_tarefaService.create(_taref);
	   		
	   		showNotifications("Tarefa registada com sucesso!", "info");
	   		limparCampos();
	   	}

	   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
	   	
	   	public void onSelect$lbx_tarefa(Event e){
	   		_tarefa = lbx_tarefa.getSelectedItem().getValue();
	   		txb_nome.setValue(_tarefa.getDescricao());
	   	    rbx_actNao.setChecked(!_tarefa.isActivo());
	   	    rbx_actSim.setChecked(_tarefa.isActivo());
	   		btn_actualizar.setVisible(true);
	   		btn_gravar.setVisible(false);
	   	}
	   	
	   	public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
	        mapaParam.put("imagemLogo", inputV);
	        mapaParam.put("listNome", "Lista de Tarefas");
	   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase3.jrxml", listTarefa, mapaParam, win_regTarefa);
	   	}
	   	
	    public void findByNomeIsActivo(String nome, boolean isActivo){
	   		listTarefa = _tarefaService.findByNomeIsActivo(nome, isActivo);
	   		lbx_tarefa.setModel(new ListModelList<Tarefa>(listTarefa));
	   	}
	   	
	   	private void limparCampos() {
	   		txb_nome.setRawValue(null);
	   		rbx_actSim.setChecked(false);
	   	    rbx_actNao.setChecked(true);
	   		btn_gravar.setVisible(true);
	   		btn_actualizar.setVisible(false);
	   		listar();
	   		
	   	}
	   	
	   	private void listar() {
	   		listTarefa = _tarefaService.getAll();
	   		lbx_tarefa.setModel(new ListModelList<Tarefa>(listTarefa));
	   	}
	   	
	   	
	   	public void showNotifications(String message, String type) {
	   		Clients.showNotification(message, type, lbx_tarefa,"before_center", 4000, true);
	   	}



}
