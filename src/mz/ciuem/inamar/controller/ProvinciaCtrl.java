package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.service.PaisService;
import mz.ciuem.inamar.service.ProvinciaService;
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

public class ProvinciaCtrl extends GenericForwardComposer {

	private Textbox txb_designacao;
	private Provincia _provincia;
	private Combobox cbx_paises;
	private Listbox lbx_provincias;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private PaisService _paisService;
	
	@WireVariable
	private ProvinciaService _provinciaSevice;
	
	private List<Provincia> listProvin = new ArrayList<Provincia>();

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_provinciaSevice = (ProvinciaService) SpringUtil.getBean("provinciaService");
		_paisService = (PaisService) SpringUtil.getBean("paisService");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
		listarPaises();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		cbx_paises.setRawValue(null);
		lbx_provincias.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void listar() {
		listProvin =  _provinciaSevice.getAll();
		lbx_provincias.setModel(new ListModelList<Provincia>(listProvin));
	}
	
	private void listarPaises(){
		cbx_paises.setModel(new ListModelList<Pais>(_paisService.getAll()));
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_provincia.setDesignacao(txb_designacao.getValue());
		_provinciaSevice.update(_provincia);
		listar();
		showNotifications("Provincia actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Provincia pr = new Provincia();
		pr.setDesignacao(txb_designacao.getValue());
		pr.setPais((Pais)cbx_paises.getSelectedItem().getValue());
		_provinciaSevice.create(pr);
		listar();
		showNotifications("Provincia registada com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_provincias(Event e){
		
		_provincia = (Provincia) lbx_provincias.getSelectedItem().getValue();
		txb_designacao.setValue(_provincia.getDesignacao());
		cbx_paises.setValue(_provincia.getPais().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Provincias");
		MasterRep.imprimir("/reportParam/reportParametrizacaoBase2.jrxml", listProvin, mapaParam, win);
	}

}
