package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.CategoriaService;
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

public class AreaCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Radio rbx_SimInamfin;
	private Radio rbx_NaoInamfin;
	private Button btn_procurar;
	private Listbox lbx_areas;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_codigo;
	private Textbox txb_descricao;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Radio rbx_SimInam;
	private Radio rbx_NaoInam;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regArea;
	
	Execution ex = Executions.getCurrent();
	
	private Area _area;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	@WireVariable
	private AreaService _areaService;
	
    private List<Area> listArea = new ArrayList<Area>();
    
    

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_areaService = (AreaService) SpringUtil.getBean("areaService");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   	}
   	
	public void onClickConfig(ForwardEvent e)  {
		Area area = (Area) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_area", area);
		win_regArea.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_subArea.zul", win_regArea,map);
		
	}
	
	public void onClickConfigTipoPedido(ForwardEvent e)  {
		Area area = (Area) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_area", area);
		win_regArea.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_tipoPedido.zul", win_regArea,map);
		
	}


       public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                boolean isAdmar = (rbx_SimInamfin.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isActivo, isAdmar);
   		}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _area.setActivo(rbx_actSim.isChecked() ? true : false);
        _area.setAdmar(rbx_SimInam.isChecked() ? false : true);
           	    
        _area.setNome(txb_nome.getValue());
        _area.setCodigo(txb_codigo.getValue());
        _area.setDescricao(txb_descricao.getValue());
        
			_areaService.update(_area);
	   		showNotifications("Área actualizada com sucesso!", "info");
	   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Area _ar = new Area();
   	    
   		_ar.setActivo(rbx_actSim.isChecked() ? true : false);
   		_ar.setAdmar(rbx_SimInam.isChecked() ? false : true);
   		
   		_ar.setNome(txb_nome.getValue());
   		_ar.setCodigo(txb_codigo.getValue());
   		_ar.setDescricao(txb_descricao.getValue());

   		
   		boolean existe = false;
   		
   		for (Area area: listArea ){
   			
   			if (area.getCodigo().equals(_ar.getCodigo())){
   				existe=true;
   			}
   		}
   		
   		
   		if (existe==false){
		   		_areaService.create(_ar);
		   		showNotifications("Área registada com sucesso!", "info");
		   		limparCampos();
   		}else{
   				showNotifications("O código introduzido já existe", "error");
   		}
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_areas(Event e){
   		_area = lbx_areas.getSelectedItem().getValue();
   		txb_nome.setValue(_area.getNome());
   		txb_codigo.setValue(_area.getCodigo());
   		txb_descricao.setValue(_area.getDescricao());
   	    rbx_actNao.setChecked(!_area.isActivo());
   	    rbx_actSim.setChecked(_area.isActivo());
   	    rbx_NaoInam.setChecked(_area.isAdmar());
   	    rbx_SimInam.setChecked(!_area.isAdmar());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de �reas");
   		MasterRep.imprimir("/reportParam/reportArea.jrxml", listArea, mapaParam, win_regArea);
   	}
   	
  	public void findByNomeIsAdmar(String nome, boolean isActivo, boolean isAdmar){
   		listArea = _areaService.findByNomeIsActivoIsAdmar(nome, isActivo, isAdmar);
   		lbx_areas.setModel(new ListModelList<Area>(listArea));
   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		txb_descricao.setRawValue(null);
   		rbx_SimInam.setChecked(false);
   		rbx_NaoInam.setChecked(true);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listArea = _areaService.getAll();
   		lbx_areas.setModel(new ListModelList<Area>(listArea));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_areas,"before_center", 4000, true);
   	}

}
