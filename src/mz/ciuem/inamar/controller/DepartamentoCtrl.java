package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.service.DepartamentoService;
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

public class DepartamentoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_admarSimfin;
	private Radio rbx_admarNaofind;
	private Button btn_procurar;
	private Listbox lbx_departamento;
	private Button btn_imprimir, btn_imprimirP, btn_imprimirC;
	
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
	
	
	private Window win_regDepartamento;
	
	Execution ex = Executions.getCurrent();
	
	private Departamento _departamento;
	
	@WireVariable
	private DepartamentoService _departamentoService;
	
    private List<Departamento> listDep = new ArrayList<Departamento>();
    
    @SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
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
		
	    _departamento.setActivo(rbx_actSim.isChecked() ? true : false);
        _departamento.setAdmar(rbx_admarSim.isChecked() ? true : false);
		
        	    
		_departamento.setNome(txb_nome.getValue());
		_departamento.setSilgla(txb_sigla.getValue());
		
		
		_departamentoService.update(_departamento);
		showNotifications("Departamento actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Departamento dep = new Departamento();
	    
        dep.setActivo(rbx_actSim.isChecked() ? true : false);
        dep.setAdmar(rbx_admarSim.isChecked() ? true : false);
		
        	    
		dep.setNome(txb_nome.getValue());
		dep.setSilgla(txb_sigla.getValue());
		
		
		_departamentoService.create(dep);
		showNotifications("Departamneto registado com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_departamento(Event e){
		_departamento = lbx_departamento.getSelectedItem().getValue();
		txb_nome.setValue(_departamento.getNome());
		txb_sigla.setValue(_departamento.getSilgla());
	    rbx_actNao.setChecked(!_departamento.isAdmar());
	    rbx_actSim.setChecked(_departamento.isAdmar());
	    rbx_admarNao.setChecked(!_departamento.isAdmar());
	    rbx_admarSim.setChecked(_departamento.isAdmar());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Departamentos");
		MasterRep.imprimir("/reportParam/reportDepartamento.jrxml", listDep, mapaParam, win_regDepartamento);
	}
	
	public void onClick$btn_imprimirC(Event e) throws JRException{
		
	    List<Departamento> listDepC = _departamentoService.findByNomeIsAdmar("", false);
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Departamentos");
		MasterRep.imprimir("/reportParam/reportDepartamento.jrxml", listDepC, mapaParam, win_regDepartamento);
	}
	
	
	
	public void findByNomeIsAdmar(String nome, boolean isAdmar){
		listDep = _departamentoService.findByNomeIsAdmar(nome, isAdmar);
		lbx_departamento.setModel(new ListModelList<Departamento>(listDep));
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
		listDep = _departamentoService.getAll();
		lbx_departamento.setModel(new ListModelList<Departamento>(listDep));
	}
	
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_departamento,"before_center", 4000, true);
	}


}
