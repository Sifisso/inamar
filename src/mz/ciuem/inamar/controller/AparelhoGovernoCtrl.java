package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.AparelhoGoverno;
import mz.ciuem.inamar.entity.TipoCombustivel;
import mz.ciuem.inamar.service.AparelhoGovernoService;
import mz.ciuem.inamar.service.TipoCombustivelService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class AparelhoGovernoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_aparelhoG;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regAparelhoG;
	
	Execution ex = Executions.getCurrent();
	
	private AparelhoGoverno _aparelhoGoverno;
	
	@WireVariable
	private AparelhoGovernoService _aparelhoGovernoService;
	
    private List<AparelhoGoverno> listAparelhoGoverno = new ArrayList<AparelhoGoverno>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_aparelhoGovernoService =  (AparelhoGovernoService) SpringUtil.getBean("aparelhoGovernoService");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   	}


  /* public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isActivo);
   	}*/
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _aparelhoGoverno.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _aparelhoGoverno.setNome(txb_nome.getValue());
   		
   		_aparelhoGovernoService.update(_aparelhoGoverno);
   		showNotifications("Aparelho do Governo actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		AparelhoGoverno _ag = new AparelhoGoverno();
   	    
   		_ag.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_ag.setNome(txb_nome.getValue());

   		_aparelhoGovernoService.create(_ag);
   		showNotifications("Aparelho do Governo registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_aparelhoG(Event e){
   		_aparelhoGoverno = lbx_aparelhoG.getSelectedItem().getValue();
   		txb_nome.setValue(_aparelhoGoverno.getNome());
   	    rbx_actNao.setChecked(!_aparelhoGoverno.isActivo());
   	    rbx_actSim.setChecked(_aparelhoGoverno.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Aparelhos de Governo");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listAparelhoGoverno, mapaParam, win_regAparelhoG);
   	}
   	
 	/*public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listFluxo = _fluxoService.findByNomeIsActivo(nome, isActivo);
   		lbx_fluxo.setModel(new ListModelList<Fluxo>(listFluxo));
   	}*/
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listAparelhoGoverno = _aparelhoGovernoService.getAll();
   		lbx_aparelhoG.setModel(new ListModelList<AparelhoGoverno>(listAparelhoGoverno));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_aparelhoG,"before_center", 4000, true);
   	}


}
