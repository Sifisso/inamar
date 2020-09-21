package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.report.PeticaoDelegacao;
import mz.ciuem.inamar.service.DistritoService;
import mz.ciuem.inamar.service.ProvinciaService;

public class DistritoCtrl extends GenericForwardComposer{
	
	private Textbox txb_designacao;
	private Combobox cbx_provincia;
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_listar;
	private Button btn_nova;
	private Button btn_imprimir;
	private Listbox lbx_distritos;
	
	@WireVariable
	private DistritoService _distritoService;
	
	@WireVariable
	private ProvinciaService _provinciaService;
	
	private Window win;
	
	Provincia _selectedProvincia;
	
	ListModelList<Distrito> _listModelDistrito;
	ListModelList<Provincia> _listModelProvincia;
	private List<Distrito> listDist = new ArrayList<Distrito>();
	Distrito _selectedDistrito;

	public void doAfterCompose (Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		
		preencherProvincia();
		preencherDistrito();
	}
	
	public void doBeforeComposeChildren(Component comp) throws Exception {
        super.doBeforeComposeChildren(comp);
        
        _distritoService = (DistritoService) SpringUtil.getBean("distritoService");
        _provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");

	}
	
	public void onClick$btn_cancelar(Event e){
		
		limparCampos();
		
	}
	
	public void onClick$btn_gravar(Event e){
		
		Distrito distrito= new Distrito();
		distrito.setDesignacao(txb_designacao.getText());
		distrito.setProvincia((Provincia)cbx_provincia.getSelectedItem().getValue());
		
		_distritoService.create(distrito);
		
		 showNotification("Distrito registado com sucesso", "info");

		
		preencherDistrito();
		limparCampos();
	}
	
	private void showNotification(String message, String type) {
		 Clients.showNotification(message, type, win, "middle_center", 4000);
		
	}

	public void onClick$btn_actualizar(Event e){
		
		_selectedDistrito.setDesignacao(txb_designacao.getText());
		_selectedDistrito.setProvincia((Provincia)cbx_provincia.getSelectedItem().getValue());
		
		_distritoService.update(_selectedDistrito);
		
		 showNotification("Distrito actualizado com sucesso", "info");
		
		
		limparCampos();
		preencherDistrito();

	}
	
	public void onSelect$lbx_distritos(Event e){
		if(_listModelDistrito.isEmpty())
			_selectedDistrito = null;
		else{
			_selectedDistrito = _listModelDistrito.getSelection().iterator().next();
			txb_designacao.setText(_selectedDistrito.getDesignacao());
			cbx_provincia.setText(_selectedDistrito.getProvincia().getDesignacao());
		}
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	

	public void limparCampos() {
		txb_designacao.setRawValue(null);
		cbx_provincia.setRawValue(null);
		lbx_distritos.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}
	
	public void preencherDistrito(){
		listDist = _distritoService.getAll();
		_listModelDistrito = new ListModelList<Distrito>(listDist);
		lbx_distritos.setModel(_listModelDistrito);
	}
	
	public void preencherProvincia(){
		_listModelProvincia = new ListModelList<Provincia>(_provinciaService.getAll());
		cbx_provincia.setModel(_listModelProvincia);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		List<PeticaoDelegacao> listDist = new ArrayList<PeticaoDelegacao>();
		List<Object[]> listDis = _distritoService.buscarTodosDoDistritos();
		for (Object[] d : listDis){
			PeticaoDelegacao cc = new PeticaoDelegacao();
//			cc.setDesignacao(String.valueOf(d[1]));
//			cc.setDesignacao1(String.valueOf(d[0]));
			listDist.add(cc);
		}
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Distritos");
		MasterRep.imprimir("/reportParam/reportDistritos.jrxml", listDist, mapaParam, win);
	}
}
