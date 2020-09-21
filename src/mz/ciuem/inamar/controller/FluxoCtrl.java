package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.Fluxo;
import mz.ciuem.inamar.service.CategoriaService;
import mz.ciuem.inamar.service.FluxoService;
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

public class FluxoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_fluxo;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	private Window win_regFluxo;
	
	Execution ex = Executions.getCurrent();
	
	private Fluxo _fluxo;
	
	@WireVariable
	private FluxoService _fluxoService;
	
    private List<Fluxo> listFluxo = new ArrayList<Fluxo>();
    
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_fluxoService = (FluxoService) SpringUtil.getBean("fluxoService");
   		
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
   
	public void onClickConfig(ForwardEvent e)  {
		Fluxo fluxo = (Fluxo) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_fluxo", fluxo);
		win_regFluxo.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_etapaFluxo.zul", win_regFluxo, map);
		
	}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _fluxo.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _fluxo.setNome(txb_nome.getValue());
   		
   		_fluxoService.update(_fluxo);
   		showNotifications("Fluxo actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Fluxo _fl = new Fluxo();
   	    
   		_fl.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_fl.setNome(txb_nome.getValue());

   		_fluxoService.create(_fl);
   		showNotifications("Fluxo registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_fluxo(Event e){
   		_fluxo = lbx_fluxo.getSelectedItem().getValue();
   		txb_nome.setValue(_fluxo.getNome());
   	    rbx_actNao.setChecked(!_fluxo.isActivo());
   	    rbx_actSim.setChecked(_fluxo.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Fluxos");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listFluxo, mapaParam, win_regFluxo);
   	}
   	
 	public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listFluxo = _fluxoService.findByNomeIsActivo(nome, isActivo);
   		lbx_fluxo.setModel(new ListModelList<Fluxo>(listFluxo));
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
   		listFluxo = _fluxoService.getAll();
   		lbx_fluxo.setModel(new ListModelList<Fluxo>(listFluxo));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_fluxo,"before_center", 4000, true);
   	}


}
