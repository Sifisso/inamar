package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.TipoCombustivel;
import mz.ciuem.inamar.service.ClasseMaritimoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.TipoCombustivelService;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ClasseMaritimoCtrl extends GenericForwardComposer{
	
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Label lbl_descricao, lbl_descricao2;
	private Textbox txb_nomefind;
	private Radio rbx_Simfind;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_classeMaritimo;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regClasseMaritimo;
	
	Execution ex = Executions.getCurrent();
	
	private ClasseMaritimo _classeMaritimo;
	
	private GrupoMaritimo _grupoMaritimo;
	
	@WireVariable
	private ClasseMaritimoService _classeMaritimoService;
	
	private List<ClasseMaritimo> listClasseMaritimo = new ArrayList<ClasseMaritimo>();
	

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_classeMaritimoService = (ClasseMaritimoService) SpringUtil.getBean("classeMaritimoService");
   		
   		_grupoMaritimo =  (GrupoMaritimo) ex.getArg().get("_grupoMaritimo");
   		
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
		ClasseMaritimo _classeMaritimo = (ClasseMaritimo) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_classeMaritimo", _classeMaritimo);
		win_regClasseMaritimo.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_categoriaMaritima.zul", win_regClasseMaritimo,map);
		
	}


   public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsActivo(nome,isActivo);
   	}
   	
	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _classeMaritimo.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _classeMaritimo.setNome(txb_nome.getValue());
        
        _classeMaritimo.setGrupoMaritimo(_grupoMaritimo);
   		
   		_classeMaritimoService.update(_classeMaritimo);
   		showNotifications("Classe Maritima actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		ClasseMaritimo _cm = new ClasseMaritimo();
   	    
   		_cm.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_cm.setNome(txb_nome.getValue());
   		
   		_cm.setGrupoMaritimo(_grupoMaritimo);

   		_classeMaritimoService.create(_cm);
   		showNotifications("Classe Maritima registada com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_classeMaritimo(Event e){
   		_classeMaritimo = lbx_classeMaritimo.getSelectedItem().getValue();
   		txb_nome.setValue(_classeMaritimo.getNome());
   	    rbx_actNao.setChecked(!_classeMaritimo.isActivo());
   	    rbx_actSim.setChecked(_classeMaritimo.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Classes Maritimas");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listClasseMaritimo, mapaParam, win_regClasseMaritimo);
   	}
   	
 	public void findByNomeIsActivo(String nome, boolean isActivo){
   		listClasseMaritimo = _classeMaritimoService.finByNomeActivo(nome, isActivo);
   		lbx_classeMaritimo.setModel(new ListModelList<ClasseMaritimo>(listClasseMaritimo));
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
   		listClasseMaritimo = _classeMaritimoService.findByGrupoMaritimo(_grupoMaritimo);
   		lbx_classeMaritimo.setModel(new ListModelList<ClasseMaritimo>(listClasseMaritimo));
   	}
   	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_grupoMaritimo.getNome());
		lbl_descricao2.setValue(_grupoMaritimo.getNome());
		
	}
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_classeMaritimo,"before_center", 4000, true);
   	}



}
