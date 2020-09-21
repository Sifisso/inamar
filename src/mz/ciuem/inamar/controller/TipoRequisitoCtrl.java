package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.TipoRequisito;
import mz.ciuem.inamar.service.CategoriaService;
import mz.ciuem.inamar.service.TipoRequisitoService;
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

public class TipoRequisitoCtrl extends GenericForwardComposer{
	

	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_tipoRequisito;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_codigo;
	private Radio rbx_actSim, rbx_visUtenteSim, rbx_visUtenteNao;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regTipoRequisito;
	
	Execution ex = Executions.getCurrent();
	
	private TipoRequisito _tipoRequisito;
	
	@WireVariable
	private TipoRequisitoService _tipoRequisitoService;
	
    private List<TipoRequisito> listTipoRequisito = new ArrayList<TipoRequisito>();
    

    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_tipoRequisitoService = (TipoRequisitoService) SpringUtil.getBean("tipoRequisitoService");
   		
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
                findByNomeIsActivo(nome,isActivo);
   		}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _tipoRequisito.setActivo(rbx_actSim.isChecked() ? true : false);
   	    _tipoRequisito.setVisivelUtente(rbx_visUtenteSim.isChecked() ? true : false);
        _tipoRequisito.setNome(txb_nome.getValue());
        _tipoRequisito.setCodigo(txb_codigo.getValue());
   		
   		_tipoRequisitoService.update(_tipoRequisito);
   		showNotifications("Tipo de Requisito actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		TipoRequisito tr = new TipoRequisito();
   	    
        tr.setActivo(rbx_actSim.isChecked() ? true : false);
        tr.setVisivelUtente(rbx_visUtenteSim.isChecked() ? true : false);  	    
   		tr.setNome(txb_nome.getValue());
   		tr.setCodigo(txb_codigo.getValue());
   		
   		
   		_tipoRequisitoService.create(tr);
   		showNotifications("Tipo de Requisito registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_tipoRequisito(Event e){
   		_tipoRequisito = lbx_tipoRequisito.getSelectedItem().getValue();
   		txb_nome.setValue(_tipoRequisito.getNome());
   		txb_codigo.setValue(_tipoRequisito.getCodigo());
   	    rbx_actNao.setChecked(!_tipoRequisito.isActivo());
   	    rbx_actSim.setChecked(_tipoRequisito.isActivo());
   	    rbx_visUtenteSim.setChecked(_tipoRequisito.isVisivelUtente());
   	    rbx_visUtenteNao.setChecked(!_tipoRequisito.isVisivelUtente());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Instituicoes");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listTipoRequisito, mapaParam, win_regTipoRequisito);
   	}
   	
  	public void findByNomeIsActivo(String nome, boolean isActivo){
   		listTipoRequisito = _tipoRequisitoService.findByNomeActivo(nome, isActivo);
   		lbx_tipoRequisito.setModel(new ListModelList<TipoRequisito>(listTipoRequisito));
   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		rbx_actSim.setChecked(false);
   		rbx_visUtenteSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listTipoRequisito = _tipoRequisitoService.getAll();
   		lbx_tipoRequisito.setModel(new ListModelList<TipoRequisito>(listTipoRequisito));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_tipoRequisito,"before_center", 4000, true);
   	}
    
  

}
