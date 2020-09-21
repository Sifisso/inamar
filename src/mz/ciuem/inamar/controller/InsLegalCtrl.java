package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Estado;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.service.EstadoService;
import mz.ciuem.inamar.service.InstrumentoLegalService;
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

public class InsLegalCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_insLegal;
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
	
	private InstrumentoLegal _instLegal;
	
	@WireVariable
	private InstrumentoLegalService _instLegalService;
	
    private List<InstrumentoLegal> listInsLegal = new ArrayList<InstrumentoLegal>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_instLegalService = (InstrumentoLegalService) SpringUtil.getBean("instrumentoLegalService");
   		
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
   		
   		_instLegal.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
   		_instLegal.setNome(txb_nome.getValue());
   		
   		_instLegalService.update(_instLegal);
   		showNotifications("Instrumento Legal actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

        InstrumentoLegal _inst = new InstrumentoLegal(); 
        _inst.setActivo(rbx_actSim.isChecked() ? true : false);
        _inst.setNome(txb_nome.getValue());
   		_instLegalService.create(_inst);
   		
   		showNotifications("Instrumento Legal registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_insLegal(Event e){
   		_instLegal = lbx_insLegal.getSelectedItem().getValue();
   		txb_nome.setValue(_instLegal.getNome());
   	    rbx_actNao.setChecked(!_instLegal.isActivo());
   	    rbx_actSim.setChecked(_instLegal.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Instrumentos Legais");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listInsLegal, mapaParam, win_regFluxo);
   	}
   	
   public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listInsLegal = _instLegalService.findByNomeActivo(nome, isActivo);
   		lbx_insLegal.setModel(new ListModelList<InstrumentoLegal>(listInsLegal));
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
   		listInsLegal = _instLegalService.getAll();
   		lbx_insLegal.setModel(new ListModelList<InstrumentoLegal>(listInsLegal));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_insLegal,"before_center", 4000, true);
   	}


}
