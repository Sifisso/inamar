package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.entity.TipoCombustivel;
import mz.ciuem.inamar.service.FluxoService;
import mz.ciuem.inamar.service.TipoCombustivelService;
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

public class TipoCombustivelCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_tipoCombustivel;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regTipoCombustivel;
	
	Execution ex = Executions.getCurrent();
	
	private TipoCombustivel _tipoCombustivel;
	
	@WireVariable
	private TipoCombustivelService _tipoCombustivelService;
	
    private List<TipoCombustivel> listTipoC = new ArrayList<TipoCombustivel>();
    

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_tipoCombustivelService = (TipoCombustivelService) SpringUtil.getBean("tipoCombustivelService");
   		
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
   		
        _tipoCombustivel.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _tipoCombustivel.setNome(txb_nome.getValue());
   		
   		_tipoCombustivelService.update(_tipoCombustivel);
   		showNotifications("Tipo de Combustivel actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		TipoCombustivel _tc = new TipoCombustivel();
   	    
   		_tc.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_tc.setNome(txb_nome.getValue());

   		_tipoCombustivelService.create(_tc);
   		showNotifications("Tipo de Combustivel registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_tipoCombustivel(Event e){
   		_tipoCombustivel = lbx_tipoCombustivel.getSelectedItem().getValue();
   		txb_nome.setValue(_tipoCombustivel.getNome());
   	    rbx_actNao.setChecked(!_tipoCombustivel.isActivo());
   	    rbx_actSim.setChecked(_tipoCombustivel.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Tipos de Combustivel");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listTipoC, mapaParam, win_regTipoCombustivel);
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
   		listTipoC = _tipoCombustivelService.getAll();
   		lbx_tipoCombustivel.setModel(new ListModelList<TipoCombustivel>(listTipoC));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_tipoCombustivel,"before_center", 4000, true);
   	}


}
