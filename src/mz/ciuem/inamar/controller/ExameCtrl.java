package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.entity.Exame;
import mz.ciuem.inamar.service.AcontecimentoService;
import mz.ciuem.inamar.service.ExameService;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@SuppressWarnings({ "serial", "rawtypes" })
public class ExameCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_exames;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_exame;
	private Radio rbx_actSimA;
	private Radio rbx_actNaoA;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regExame;
	
	Execution ex = Executions.getCurrent();
	
	private Exame _exame;
	
	@WireVariable
	private ExameService  _exameService;
	
	private List<Exame> listExames = new ArrayList<Exame>();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_exameService = (ExameService) SpringUtil.getBean("exameService");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
	}
	
	private void listar() {
		listExames =  _exameService.getAll();
		lbx_exames.setModel(new ListModelList<Exame>(listExames));
	}
	
	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Exame ex = new Exame();
		ex.setDescricao(txb_exame.getValue());
		
			_exameService.create(ex);			
			listar();
			limparCampos();
			showNotifications("Tipo de Exame registado com sucesso!", "info");
		}
	
	  public void onClickprcurar(ForwardEvent e)  {
          String descricao = txb_nomefind.getValue();
          findBydescricao(descricao);
		}
	  
	  public void findBydescricao(String descricao){
	   		listExames = _exameService.findBydescricao(descricao);
	   		lbx_exames.setModel(new ListModelList<Exame>(listExames));
	   	}
	
	public void onClick$btn_actualizar() throws InterruptedException {
		_exame.setDescricao(txb_exame.getValue());
		
			_exameService.update(_exame);
			listar();
			showNotifications("Tipo de Exame actualizado com sucesso!", "info");
			limparCampos();
	}
	
	public void onSelect$lbx_exames(Event e){
		_exame = lbx_exames.getSelectedItem().getValue();
		txb_exame.setValue(_exame.getDescricao());
	    
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
		
	}
	
	private void limparCampos() {
		txb_exame.setRawValue(null);

		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		
	}
	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
	
	private void habilitarCampos() {
		txb_exame.setDisabled(false);
	}
	

	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_exames,"before_center", 4000, true);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Tipos de Exames");
   		MasterRep.imprimir("/reportParam/reportTipoAcontecimento.jrxml", listExames, mapaParam, win_regExame);
   	}

}
