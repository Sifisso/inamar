package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.Sectorr;
import mz.ciuem.inamar.service.CategoriaService;
import mz.ciuem.inamar.service.SectorrService;
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

public class CategoriaCtrl extends GenericForwardComposer{
	
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_categoria;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_codigo;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regCategoria;
	
	Execution ex = Executions.getCurrent();
	
	private Categoria _categoria;
	
	@WireVariable
	private CategoriaService _categoriaService;
	
    private List<Categoria> listCategoria = new ArrayList<Categoria>();
    
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_categoriaService = (CategoriaService) SpringUtil.getBean("categoriaService");
   		
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
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _categoria.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _categoria.setNome(txb_nome.getValue());
        _categoria.setCodigo(txb_codigo.getValue());
   		
   		_categoriaService.update(_categoria);
   		showNotifications("Categoria actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Categoria cat = new Categoria();
   	    
        cat.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		cat.setNome(txb_nome.getValue());
   		cat.setCodigo(txb_codigo.getValue());
   		
   		
   		_categoriaService.create(cat);
   		showNotifications("Categoria registada com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_categoria(Event e){
   		_categoria = lbx_categoria.getSelectedItem().getValue();
   		txb_nome.setValue(_categoria.getNome());
   		txb_codigo.setValue(_categoria.getCodigo());
   	    rbx_actNao.setChecked(!_categoria.isActivo());
   	    rbx_actSim.setChecked(_categoria.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Categorias");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listCategoria, mapaParam, win_regCategoria);
   	}
   	
  	public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listCategoria = _categoriaService.findByNomeActivo(nome, isActivo);
   		lbx_categoria.setModel(new ListModelList<Categoria>(listCategoria));
   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listCategoria = _categoriaService.getAll();
   		lbx_categoria.setModel(new ListModelList<Categoria>(listCategoria));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_categoria,"before_center", 4000, true);
   	}

}
