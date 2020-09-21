package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.DelegacaoDepartamentoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.ProvinciaService;
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
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class DelegacaoDepartamentoCtrl extends GenericForwardComposer{
	
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	private Label lbl_descricao;
	private Label lbl_descricao2;
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_actSimfin;
	private Radio rbx_actNaofind;
	private Listbox lbx_delegacaoDep;
	private Button btn_procurar;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Chosenbox chxDep;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regDelegacaoDep;
	
	
	Execution ex = Executions.getCurrent();
	
	private Delegacao _delegacao;
	
	private Departamento _departamento;
	
	private DelegacaoDepartamento _delegacaoDep;
	
	
	@WireVariable
	private DelegacaoDepartamentoService _delegacaoDepService;
	
	@WireVariable
	private DepartamentoService _departamentoService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	private ListModelList <Departamento> listDep;
	
	private List<DelegacaoDepartamento> listDelegaDep = new ArrayList<DelegacaoDepartamento>();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_delegacaoDepService =  (DelegacaoDepartamentoService) SpringUtil.getBean("delegacaoDepartamentoService");
		_departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
		
		_delegacao =  (Delegacao) ex.getArg().get("_delegacao");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
		peencherDepartamentos();
		preencherCabecalho();
	}


    public void onClickprcurar(ForwardEvent e)  {
    	     boolean activo = rbx_actSimfin.isChecked() ? true : false;
             String nome = txb_nomefind.getValue();
             findByNomeProvincia(nome,activo);
		}
	
	public void onClickConfig(ForwardEvent e)  {
		DelegacaoDepartamento delegacaoDep = (DelegacaoDepartamento) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_delegacaoDep", delegacaoDep);
		win_regDelegacaoDep.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_delegacaoDepartamentoSector.zul", win_regDelegacaoDep,map);
		
	}
	
	public void onClick$btn_actualizar() throws InterruptedException {
		
		
		Set<Departamento> setDeps = chxDep.getSelectedObjects();
		
		if(!setDeps.isEmpty()){
			for (Departamento departamento : setDeps) {
				
				_delegacaoDep.setActivo(rbx_actSim.isChecked() ? true : false);
				_delegacaoDep.setDelegacao(_delegacao);
				_delegacaoDep.setDepartamento(departamento);
				
				_delegacaoDepService.update(_delegacaoDep);
				
			}
			
			
			showNotifications("Departamento Actualizado com sucesso!", "info");
			limparCampos();
		}

	}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		
		Set<Departamento> setDeps = chxDep.getSelectedObjects();
		
		if(!setDeps.isEmpty()){
			for (Departamento departamento : setDeps) {
				
				DelegacaoDepartamento delDep = new DelegacaoDepartamento();
				delDep.setActivo(rbx_actSim.isChecked() ? true : false);
				delDep.setDelegacao(_delegacao);
				delDep.setDepartamento(departamento);
				
				_delegacaoDepService.create(delDep);
				
			}
			
			
			showNotifications("Departamentos registados com sucesso!", "info");
			limparCampos();
		}

	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_delegacaoDep(Event e){
		_delegacaoDep = lbx_delegacaoDep.getSelectedItem().getValue();
	    rbx_actSim.setChecked(_delegacaoDep.isActivo());
	    rbx_actNao.setChecked(!_delegacaoDep.isActivo());
	    listDep.add(_delegacaoDep.getDepartamento());
	    chxDep.setModel(listDep);
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", _delegacao.getNome());
		MasterRep.imprimir("/reportParam/reportDepartamentoDelegacao.jrxml", listDelegaDep, mapaParam, win_regDelegacaoDep);
	}
	
	public void findByNomeProvincia(String nome, boolean activo){
		listDelegaDep = _delegacaoDepService.findByNomeActivo(nome, activo);
		lbx_delegacaoDep.setModel(new ListModelList<DelegacaoDepartamento>(listDelegaDep));
	}
	
	
	private void limparCampos() {
		chxDep.clearSelection();
		rbx_actSim.setChecked(false);
	    rbx_actNao.setChecked(true);
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		listar();
		peencherDepartamentos();
		
	}
	
	private void listar() {
		listDelegaDep = _delegacaoDepService.findByDelegacao(_delegacao);
		lbx_delegacaoDep.setModel(new ListModelList<DelegacaoDepartamento>(listDelegaDep));
	}
	
	private void peencherDepartamentos() {
		listDep = new ListModelList<Departamento>(_departamentoService.findByDelegacao(_delegacao, _delegacao.isAdmar()));
		chxDep.setModel(listDep);
	}
	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_delegacao.getNome());
		lbl_descricao2.setValue(_delegacao.getNome());
	}

	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_delegacaoDep,"before_center", 4000, true);
	}


}
