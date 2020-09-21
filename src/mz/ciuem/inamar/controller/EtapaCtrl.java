package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.entity.Etapa;
import mz.ciuem.inamar.service.EstadoService;
import mz.ciuem.inamar.service.EtapaService;
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

public class EtapaCtrl extends GenericForwardComposer{
	
	//Main Div
		private Textbox txb_nomefind;
		private Radio rbx_Simfin;
		private Radio rbx_Naofind;
		private Button btn_procurar;
		private Listbox lbx_etapa;
		private Button btn_imprimir;
		
		//Modal Div
		private Textbox txb_nome;
		private Radio rbx_actSim;
		private Radio rbx_actNao;
		private Button btn_gravar;
		private Button btn_actualizar;
		private Button btn_cancelar;
		
		
		private Window win_regEtapa;
		
		Execution ex = Executions.getCurrent();
		
		private Etapa _etapa;
		
		@WireVariable
		private EtapaService _etapaService;
		
	    private List<Etapa> listEtapa = new ArrayList<Etapa>();
	    
	    
	    @SuppressWarnings("unchecked")
	   	@Override
	   	public void doBeforeComposeChildren(Component comp) throws Exception {
	   		super.doBeforeComposeChildren(comp);
	   		
	   		_etapaService = (EtapaService) SpringUtil.getBean("etapaService");
	   		
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
	        findByNomeIsActivo(nome,isActivo);
	   	}
	   	
	   	public void onClick$btn_actualizar() throws InterruptedException {
	   		
	        _etapa.setActivo(rbx_actSim.isChecked() ? true : false);
	   	    
	        _etapa.setNome(txb_nome.getValue());
	   		
	   		_etapaService.update(_etapa);
	   		showNotifications("Etapa actualizada com sucesso!", "info");
	   		limparCampos();

	   	}

	   	public void onClick$btn_gravar(Event e) throws InterruptedException{

	   		Etapa _et = new Etapa();
	   		_et.setActivo(rbx_actSim.isChecked() ? true : false);
	   		_et.setNome(txb_nome.getValue());
	   		_etapaService.create(_et);
	   		
	   		showNotifications("Etapa registada com sucesso!", "info");
	   		limparCampos();
	   	}

	   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
	   	
	   	public void onSelect$lbx_etapa(Event e){
	   		_etapa = lbx_etapa.getSelectedItem().getValue();
	   		txb_nome.setValue(_etapa.getNome());
	   	    rbx_actNao.setChecked(!_etapa.isActivo());
	   	    rbx_actSim.setChecked(_etapa.isActivo());
	   		btn_actualizar.setVisible(true);
	   		btn_gravar.setVisible(false);
	   	}
	   	
	   	public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
	        mapaParam.put("imagemLogo", inputV);
	        mapaParam.put("listNome", "Lista de Etapas");
	   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listEtapa, mapaParam, win_regEtapa);
	   	}
	   	
	    public void  findByNomeIsActivo(String nome, boolean isActivo){
	   		listEtapa = _etapaService.findByNomeIsActivo(nome, isActivo);
	   		lbx_etapa.setModel(new ListModelList<Etapa>(listEtapa));
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
	   		listEtapa = _etapaService.getAll();
	   		lbx_etapa.setModel(new ListModelList<Etapa>(listEtapa));
	   	}
	   	
	   	
	   	public void showNotifications(String message, String type) {
	   		Clients.showNotification(message, type, lbx_etapa,"before_center", 4000, true);
	   	}



}
