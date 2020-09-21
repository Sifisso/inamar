package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.entity.TipoCombustivel;
import mz.ciuem.inamar.service.ServicoDestinoService;
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

public class ServicoDestinoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_servicoDest;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regServicoDestino;
	
	Execution ex = Executions.getCurrent();
	
	private ServicoDestino _servicoDestino;
	
	@WireVariable
	private ServicoDestinoService _servicoDestinoService;
	
    private List<ServicoDestino> listServicoDestino = new ArrayList<ServicoDestino>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_servicoDestinoService = (ServicoDestinoService) SpringUtil.getBean("servicoDestinoService");
   		
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
   		
        _servicoDestino.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _servicoDestino.setNome(txb_nome.getValue());
   		
   		_servicoDestinoService.update(_servicoDestino);
   		showNotifications("Servico Destino actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		ServicoDestino _sd = new ServicoDestino();
   	    
   		_sd.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_sd.setNome(txb_nome.getValue());

   		_servicoDestinoService.create(_sd);
   		showNotifications("Servico Destino registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_servicoDest(Event e){
   		_servicoDestino = lbx_servicoDest.getSelectedItem().getValue();
   		txb_nome.setValue(_servicoDestino.getNome());
   	    rbx_actNao.setChecked(!_servicoDestino.isActivo());
   	    rbx_actSim.setChecked(_servicoDestino.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Serviços de Destino");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listServicoDestino, mapaParam, win_regServicoDestino);
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
   		listServicoDestino = _servicoDestinoService.getAll();
   		lbx_servicoDest.setModel(new ListModelList<ServicoDestino>(listServicoDestino));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_servicoDest,"before_center", 4000, true);
   	}

}
