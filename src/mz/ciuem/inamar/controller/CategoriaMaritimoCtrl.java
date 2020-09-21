package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.ClasseMaritimoService;
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

public class CategoriaMaritimoCtrl extends GenericForwardComposer{
	
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
	private Listbox lbx_categoriaMaritimo;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regCategoriaMaritimo;
	
	Execution ex = Executions.getCurrent();
	
	private ClasseMaritimo _classeMaritimo;
	
	private CategoriaMaritimo _categoriaMaritimo;
	
	@WireVariable
	private CategoriaMaritimoService _categoriaMaritimoService;
	
	private List<CategoriaMaritimo> listCategoriaMaritimo = new ArrayList<CategoriaMaritimo>();
	
	 @SuppressWarnings("unchecked")
	   	@Override
	   	public void doBeforeComposeChildren(Component comp) throws Exception {
	   		super.doBeforeComposeChildren(comp);
	   		
	   		_categoriaMaritimoService = (CategoriaMaritimoService) SpringUtil.getBean("categoriaMaritimoService");
	   		
	   		_classeMaritimo =  (ClasseMaritimo) ex.getArg().get("_classeMaritimo");
	   		
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
			win_regCategoriaMaritimo.getChildren().clear();
			Executions.createComponents("/views/Parametrizacao/registar_categoriaMaritima.zul", win_regCategoriaMaritimo,map);
			
		}


	 /*  public void onClickprcurar(ForwardEvent e)  {
	                String nome = txb_nomefind.getValue();
	                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
	                findByNomeIsActivo(nome,isActivo);
	   	}*/
	   	
		public void onClick$btn_actualizar() throws InterruptedException {
	   		
	        _categoriaMaritimo.setActivo(rbx_actSim.isChecked() ? true : false);
	   	    
	        _categoriaMaritimo.setNome(txb_nome.getValue());
	        
	        _categoriaMaritimo.setClasseMaritimo(_classeMaritimo);
	   		
	   		_categoriaMaritimoService.update(_categoriaMaritimo);
	   		showNotifications("Categoria Maritima actualizada com sucesso!", "info");
	   		limparCampos();

	   	}

	   	public void onClick$btn_gravar(Event e) throws InterruptedException{

	   		CategoriaMaritimo _cm  =  new CategoriaMaritimo();
	   	    
	   		_cm.setActivo(rbx_actSim.isChecked() ? true : false);
	           	    
	   		_cm.setNome(txb_nome.getValue());
	   		
	   		_cm.setClasseMaritimo(_classeMaritimo);

	   		_categoriaMaritimoService.create(_cm);
	   		showNotifications("Categoria Maritima registada com sucesso!", "info");
	   		limparCampos();
	   	}

	   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
	   	
	   	public void onSelect$lbx_categoriaMaritimo(Event e){
	   		_categoriaMaritimo = lbx_categoriaMaritimo.getSelectedItem().getValue();
	   		txb_nome.setValue(_categoriaMaritimo.getNome());
	   	    rbx_actNao.setChecked(!_categoriaMaritimo.isActivo());
	   	    rbx_actSim.setChecked(_categoriaMaritimo.isActivo());
	   		btn_actualizar.setVisible(true);
	   		btn_gravar.setVisible(false);
	   	}
	   	
	   	public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
	        mapaParam.put("imagemLogo", inputV);
	        mapaParam.put("listNome", "Lista de Categorias de Maritimos");
	   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listCategoriaMaritimo, mapaParam, win_regCategoriaMaritimo);
	   	}
	   	
	 /*	public void findByNomeIsActivo(String nome, boolean isActivo){
	   		listClasseMaritimo = _classeMaritimoService.finByNomeActivo(nome, isActivo);
	   		lbx_classeMaritimo.setModel(new ListModelList<ClasseMaritimo>(listClasseMaritimo));
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
	   		listCategoriaMaritimo = _categoriaMaritimoService.findByClasseMaritimo(_classeMaritimo);
	   		lbx_categoriaMaritimo.setModel(new ListModelList<CategoriaMaritimo>(listCategoriaMaritimo));
	   	}
	   	
		private void preencherCabecalho() {
			lbl_descricao.setValue(_classeMaritimo.getNome());
			lbl_descricao2.setValue(_classeMaritimo.getNome());
			
		}
	   	
	   	public void showNotifications(String message, String type) {
	   		Clients.showNotification(message, type, lbx_categoriaMaritimo,"before_center", 4000, true);
	   	}

	


}
