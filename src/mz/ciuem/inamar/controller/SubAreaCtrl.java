package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.SubAreaService;
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
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class SubAreaCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_SubAreas;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_codigo;
	private Textbox txb_descricao;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Label lbl_descricao, lbl_descricao2;
	
	
	private Window win_regSubArea;
	
	Execution ex = Executions.getCurrent();
	
	private Area _area;
	
	private SubArea _subArea;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	@WireVariable
	private AreaService _areaService;
	
	@WireVariable
	private SubAreaService _subAreaService;
	
    private List<SubArea> listSubArea = new ArrayList<SubArea>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_areaService = (AreaService) SpringUtil.getBean("areaService");
   		_subAreaService = (SubAreaService) SpringUtil.getBean("subAreaService");
   		_area = (Area) ex.getArg().get("_area");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   		preencherCabecalho();
   	}
   	
	public void onClickConfig(ForwardEvent e)  {
		SubArea _subA = (SubArea) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_subArea", _subA);
		map.put("area", _area);
		win_regSubArea.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_taxa.zul", win_regSubArea, map);
		
	}


      public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isActivo);
   		}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   		_subArea.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
   		_subArea.setNome(txb_nome.getValue());
   		_subArea.setCodigo(txb_codigo.getValue());
   		_subArea.setDescricao(txb_descricao.getValue());
   		_subArea.setArea(_area);

   		boolean existe = false;
   		
   		for (SubArea subArea: listSubArea ){
   			
   			if (subArea.getCodigo().equals(_subArea.getCodigo())){
   				existe=true;
   			}
   		}
   		
        if(existe==false){
        	_subAreaService.update(_subArea);
       		showNotifications("Sub-Area actualizada com sucesso!", "info");
       		limparCampos();
        }else{
        	showNotifications("O código introduzido já existe", "error");
        }
   		

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		SubArea _subA = new SubArea();
   	    
   		_subA.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_subA.setNome(txb_nome.getValue());
   		_subA.setCodigo(txb_codigo.getValue());
   		_subA.setDescricao(txb_descricao.getValue());
   		_subA.setArea(_area);
   		
 		boolean existe = false;
   		
   		for (SubArea subArea: listSubArea ){
   			
   			if (subArea.getCodigo().equals(_subA.getCodigo())){
   				existe=true;
   			}
   		}
   		
   		if (existe==false){
   			_subAreaService.create(_subA);
   	   		showNotifications("Sub-Area registada com sucesso!", "info");
   	   		limparCampos();	
   		}else{
   			showNotifications("O código introduzido já existe", "error");
   		}
   		
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_SubAreas(Event e){
   		_subArea = lbx_SubAreas.getSelectedItem().getValue();
   		txb_nome.setValue(_subArea.getNome());
   		txb_codigo.setValue(_subArea.getCodigo());
   		txb_descricao.setValue(_subArea.getDescricao());
   	    rbx_actNao.setChecked(!_subArea.isActivo());
   	    rbx_actSim.setChecked(_subArea.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome",_area.getNome());
   		MasterRep.imprimir("/reportParam/reportSubArea.jrxml", listSubArea, mapaParam, win_regSubArea);
   	}
   	
  	public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listSubArea = _subAreaService.findByNomeActivo(nome, isActivo);
   		lbx_SubAreas.setModel(new ListModelList<SubArea>(listSubArea));
   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		txb_descricao.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_area.getNome());
		lbl_descricao2.setValue(_area.getNome());
	}
   	
   	private void listar() {
   		listSubArea = _subAreaService.findByArea(_area);
   		lbx_SubAreas.setModel(new ListModelList<SubArea>(listSubArea));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_SubAreas,"before_center", 4000, true);
   	}

}
