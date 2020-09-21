package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Sectorr;
import mz.ciuem.inamar.entity.Sectorr;
import mz.ciuem.inamar.service.SectorrService;
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

public class SectorrCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_admarSimfin;
	private Radio rbx_admarNaofind;
	private Button btn_procurar;
	private Listbox lbx_Sectorr;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_sigla;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Radio rbx_admarSim;
	private Radio rbx_admarNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regSector;
	
	Execution ex = Executions.getCurrent();
	
	private Sectorr _Sectorr;
	
	@WireVariable
	private SectorrService _sectorService;
	
    private List<Sectorr> listSector = new ArrayList<Sectorr>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_sectorService = (SectorrService) SpringUtil.getBean("sectorrService");
   		
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
                boolean isAdmar = (rbx_admarNaofind.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isAdmar);
   		}
       
       public void onClickprcurarTodos(ForwardEvent e)  {
          listar();
		}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   	    _Sectorr.setActivo(rbx_actSim.isChecked() ? true : false);
           _Sectorr.setAdmar(rbx_admarSim.isChecked() ? true : false);
   		
           	    
   		_Sectorr.setNome(txb_nome.getValue());
   		_Sectorr.setSilgla(txb_sigla.getValue());
   		
   		
   		_sectorService.update(_Sectorr);
   		showNotifications("Sector actualizado com sucesso!", "info");
   		limparCampos();

   			}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Sectorr dep = new Sectorr();
   	    
           dep.setActivo(rbx_actSim.isChecked() ? true : false);
           dep.setAdmar(rbx_admarSim.isChecked() ? true : false);
   		
           	    
   		dep.setNome(txb_nome.getValue());
   		dep.setSilgla(txb_sigla.getValue());
   		
   		
   		_sectorService.create(dep);
   		showNotifications("Sector registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_Sectorr(Event e){
   		_Sectorr = lbx_Sectorr.getSelectedItem().getValue();
   		txb_nome.setValue(_Sectorr.getNome());
   		txb_sigla.setValue(_Sectorr.getSilgla());
   	    rbx_actNao.setChecked(!_Sectorr.isAdmar());
   	    rbx_actSim.setChecked(_Sectorr.isAdmar());
   	    rbx_admarNao.setChecked(!_Sectorr.isAdmar());
   	    rbx_admarSim.setChecked(_Sectorr.isAdmar());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Sectores");
		MasterRep.imprimir("/reportParam/reportDepartamento.jrxml", listSector, mapaParam, win_regSector);
	

   	}
   	
   	public void findByNomeIsAdmar(String nome, boolean isAdmar){
   		listSector = _sectorService.findByNomeIsAdmar(nome, isAdmar);
   		lbx_Sectorr.setModel(new ListModelList<Sectorr>(listSector));
   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		txb_sigla.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   	    rbx_admarSim.setChecked(false);
   	    rbx_admarNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listSector = _sectorService.getAll();
   		lbx_Sectorr.setModel(new ListModelList<Sectorr>(listSector));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_Sectorr,"before_center", 4000, true);
   	}

}
