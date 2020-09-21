package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.service.EstadoService;
import mz.ciuem.inamar.service.FluxoService;
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

public class EstadoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_estado;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regFluxo;
	
	Execution ex = Executions.getCurrent();
	
	private Estado _estado;
	
	@WireVariable
	private EstadoService _estadoService;
	
    private List<Estado> listEstado = new ArrayList<Estado>();
    
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_estadoService = (EstadoService) SpringUtil.getBean("estadoService");
   		
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
        boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
        findByNomeIsAdmar(nome,isActivo);
   	}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _estado.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _estado.setNome(txb_nome.getValue());
   		
   		_estadoService.update(_estado);
   		showNotifications("Estado actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Estado _est = new Estado();
   		_est.setActivo(rbx_actSim.isChecked() ? true : false);
   		_est.setNome(txb_nome.getValue());
   		_estadoService.create(_est);
   		
   		showNotifications("Estado registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_estado(Event e){
   		_estado = lbx_estado.getSelectedItem().getValue();
   		txb_nome.setValue(_estado.getNome());
   	    rbx_actNao.setChecked(!_estado.isActivo());
   	    rbx_actSim.setChecked(_estado.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Estados");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listEstado, mapaParam, win_regFluxo);
   	}
   	
 	public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listEstado = _estadoService.findByNomeIsActivo(nome, isActivo);
   		lbx_estado.setModel(new ListModelList<Estado>(listEstado));
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
   		listEstado = _estadoService.getAll();
   		lbx_estado.setModel(new ListModelList<Estado>(listEstado));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_estado,"before_center", 4000, true);
   	}



}
