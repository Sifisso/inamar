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
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.entity.Sectorr;
import mz.ciuem.inamar.service.DelegacaoDepartamentoSectorService;
import mz.ciuem.inamar.service.DelegacaoDepartamentoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.SectorrService;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class DelegacaoDepartamentoSectorCtrl extends GenericForwardComposer{
	
	
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
	private Listbox lbx_delegacaoDepSectorSector;
	private Button btn_procurar;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Chosenbox chxSect;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regDelegacaoDepSector;
	
	
	Execution ex = Executions.getCurrent();
	
	private Sectorr _sectorr;
	
	private DelegacaoDepartamento _delegacaoDep;
	
	private DelegacaoDepartamentoSector _delegSepSector;
	
	
	@WireVariable
	private DelegacaoDepartamentoService _delegacaoDepService;
	
	@WireVariable
	private DelegacaoDepartamentoSectorService _delegacaoDepartamentoSectorService;
	
	@WireVariable
	private SectorrService _sectorrService;
	
	private ListModelList <Sectorr> listSectorr;
	
	private List<DelegacaoDepartamentoSector> listDelegaDepSector = new ArrayList<DelegacaoDepartamentoSector>();
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_delegacaoDepService =  (DelegacaoDepartamentoService) SpringUtil.getBean("delegacaoDepartamentoService");
		_delegacaoDepartamentoSectorService = (DelegacaoDepartamentoSectorService) SpringUtil.getBean("delegacaoDepartamentoSectorService");
		_sectorrService = (SectorrService) SpringUtil.getBean("sectorrService");
		
		_delegacaoDep =  (DelegacaoDepartamento) ex.getArg().get("_delegacaoDep");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
		peencherSectores();
		preencherCabecalho();
	}


        public void onClickprcurar(ForwardEvent e)  {
    	     boolean activo = rbx_actSimfin.isChecked() ? true : false;
             String nome = txb_nomefind.getValue();
             findByNomeActivo(nome,activo);
		}
	
	/*public void onClickConfig(ForwardEvent e)  {
		DelegacaoDepartamento delegacaoDep = (DelegacaoDepartamento) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_delegacaoDep", delegacaoDep);
		win_regDelegacaoDepSector.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_delegacaoDepartamentoSector.zul", win_regDelegacaoDepSector,map);
		
	}*/
	
	public void onClick$btn_actualizar() throws InterruptedException {
		
		
		Set<Sectorr> setSectores = chxSect.getSelectedObjects();
		
		if(!setSectores.isEmpty()){
			for (Sectorr sector : setSectores) {
				
				_delegSepSector.setActivo(rbx_actSim.isChecked() ? true : false);
				_delegSepSector.setSectorr(sector);
				_delegSepSector.setDelegacaoDepartamento(_delegacaoDep);
				
				_delegacaoDepartamentoSectorService.update(_delegSepSector);
				
			}
			showNotifications("Sector actualizado com sucesso!", "info");
			limparCampos();
		}

	}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		
		Set<Sectorr> setSectores = chxSect.getSelectedObjects();
		
		if(!setSectores.isEmpty()){
			for (Sectorr sector : setSectores) {
				
				DelegacaoDepartamentoSector delDepSect = new DelegacaoDepartamentoSector();
				delDepSect.setActivo(rbx_actSim.isChecked() ? true : false);
				delDepSect.setSectorr(sector);
				delDepSect.setDelegacaoDepartamento(_delegacaoDep);
				
				_delegacaoDepartamentoSectorService.create(delDepSect);
				
			}
			showNotifications("Sectores registados com sucesso!", "info");
			limparCampos();
		}

	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{
		limparCampos();
	}
	
	public void onSelect$lbx_delegacaoDepSectorSector(Event e){
		_delegSepSector = lbx_delegacaoDepSectorSector.getSelectedItem().getValue();
	    rbx_actSim.setChecked(_delegSepSector.isActivo());
	    rbx_actNao.setChecked(!_delegSepSector.isActivo());
	    listSectorr.add(_delegSepSector.getSectorr());
	    chxSect.setModel(listSectorr);
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome",_delegacaoDep.getDepartamento().getNome()+"("+_delegacaoDep.getDelegacao().getNome()+")");
		MasterRep.imprimir("/reportParam/reportDepartamentoDelegacaoSector.jrxml", listDelegaDepSector, mapaParam, win_regDelegacaoDepSector);
	}
	
	public void findByNomeActivo(String nome, boolean activo){
		listDelegaDepSector = _delegacaoDepartamentoSectorService.findByNomeActivo(nome, activo);
		lbx_delegacaoDepSectorSector.setModel(new ListModelList<DelegacaoDepartamentoSector>(listDelegaDepSector));
	}
	
	
	private void limparCampos() {
		chxSect.clearSelection();
		rbx_actSim.setChecked(false);
	    rbx_actNao.setChecked(true);
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		listar();
		peencherSectores();
		
	}
	
	private void listar() {
		listDelegaDepSector = _delegacaoDepartamentoSectorService.findByDelegacaoDepartamento(_delegacaoDep);
		lbx_delegacaoDepSectorSector.setModel(new ListModelList<DelegacaoDepartamentoSector>(listDelegaDepSector));
	}
	
	private void peencherSectores() {
		listSectorr = new ListModelList<Sectorr>(_sectorrService.findByDelegacaoDep(_delegacaoDep, _delegacaoDep.getDelegacao().isAdmar()));
		chxSect.setModel(listSectorr);
	}
	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_delegacaoDep.getDepartamento().getNome()+"("+_delegacaoDep.getDelegacao().getNome()+")");
		lbl_descricao2.setValue(_delegacaoDep.getDepartamento().getNome()+"("+_delegacaoDep.getDelegacao().getNome()+")");
	}

	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_delegacaoDepSectorSector,"before_center", 4000, true);
	}


}
