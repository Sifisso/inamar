package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.service.ClasseEmbarcacaoService;
import mz.ciuem.inamar.service.ContagemService;
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

public class ContagemCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_contagem;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regContagem;
	
	Execution ex = Executions.getCurrent();
	
	private Contagem _contagem;
	
	@WireVariable
	private ContagemService _contagemService;
	
    private List<Contagem> listContagem = new ArrayList<Contagem>();
    

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_contagemService = (ContagemService) SpringUtil.getBean("contagemService");
   		
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
   		
        _contagem.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _contagem.setNome(txb_nome.getValue());
   		
   		_contagemService.update(_contagem);
   		showNotifications("Contagem actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Contagem _co = new Contagem();
   	    
   		_co.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_co.setNome(txb_nome.getValue());

   		_contagemService.create(_co);
   		showNotifications("Contagem registada com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_contagem(Event e){
   		_contagem = lbx_contagem.getSelectedItem().getValue();
   		txb_nome.setValue(_contagem.getNome());
   	    rbx_actNao.setChecked(!_contagem.isActivo());
   	    rbx_actSim.setChecked(_contagem.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Contagens");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listContagem, mapaParam, win_regContagem);
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
   		listContagem = _contagemService.getAll();
   		lbx_contagem.setModel(new ListModelList<Contagem>(listContagem));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_contagem,"before_center", 4000, true);
   	}


}
