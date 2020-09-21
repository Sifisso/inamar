package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class LocalPraticaPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regLocalPraticaPedido;
	private Combobox cbx_localPratica;
	private Listbox lbx_localPratica;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_loacalPPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private LocalPratica _selectedLocalPratica;
	
	
	
	@WireVariable
	private LocalPraticaService _localPraticaService;
	@WireVariable
	private LocalPraticaPedidoService _localPraticaPedidoService;
	private List<LocalPratica> listLocalPratica, listLocalPraticaAdd = new ArrayList<LocalPratica>();
	private ListModelList<LocalPratica> listModelLocalPraticaAdd, listModelLocalPratica;
	private List<LocalPraticaPedido> _listLocalPPedido = new ArrayList<LocalPraticaPedido>();
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_localPraticaService =  (LocalPraticaService) SpringUtil.getBean("localPraticaService");
		_localPraticaPedidoService = (LocalPraticaPedidoService) SpringUtil.getBean("localPraticaPedidoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherLocalPratica(_pedido);
		listaLocalPraticaPedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalPraticaPedido.jrxml", _listLocalPPedido, mapaParam, win_regLocalPraticaPedido);
   	}
	
	public void onSelect$cbx_localPratica() {
		
		_selectedLocalPratica = cbx_localPratica.getSelectedItem().getValue();
		
    }

	public void onClick$btn_adicionar() {
	  
		listLocalPraticaAdd.add(_selectedLocalPratica);
		listModelLocalPraticaAdd = new ListModelList<LocalPratica>(listLocalPraticaAdd);
		
		lbx_localPratica.setModel(listModelLocalPraticaAdd);
		
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);
		cbx_localPratica.removeChild(cbx_localPratica.getSelectedItem());
		cbx_localPratica.setRawValue(null);
		listLocalPratica.remove(_selectedLocalPratica);
		_selectedLocalPratica = null;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		LocalPratica lpr = (LocalPratica) e.getData();
		
		listLocalPraticaAdd.remove(lpr);
        listModelLocalPraticaAdd = new ListModelList<LocalPratica>(listLocalPraticaAdd);
		lbx_localPratica.setModel(listModelLocalPraticaAdd);
		
		listLocalPratica.add(lpr);
		listModelLocalPratica = new ListModelList<LocalPratica>(listLocalPratica);
		cbx_localPratica.setModel(listModelLocalPratica);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_localPratica.getItems()) {

			LocalPratica lp = (LocalPratica) listItem.getValue();

			LocalPraticaPedido _lpp = new LocalPraticaPedido();
			_lpp.setLocalPratica(lp);
			
			_lpp.setPedido(_pedido);

			_localPraticaPedidoService.create(_lpp);
		}
		
		listaLocalPraticaPedido(_pedido);
		preencherLocalPratica(_pedido);
		listLocalPraticaAdd.clear();
		listLocalPratica.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Locais de Pratica Adicionados com Sucesso", "info");
	}
	
	private void listaLocalPraticaPedido(Pedido _pedido) {
		_listLocalPPedido = _localPraticaPedidoService.findByPedido(_pedido);
		lbx_loacalPPedido.setModel(new ListModelList<LocalPraticaPedido>(_listLocalPPedido));
	}

	private void preencherLocalPratica(Pedido _pedido) {
		listLocalPratica = _localPraticaService.findNotInPedido(_pedido);
		listModelLocalPratica = new ListModelList<LocalPratica>(listLocalPratica);
		cbx_localPratica.setModel(listModelLocalPratica);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_localPratica, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_localPratica.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_loacalPPedido.clearSelection();
		cbx_localPratica.setRawValue(null);
		_selectedLocalPratica = null;
		listLocalPratica = new ArrayList<LocalPratica>();
		listModelLocalPraticaAdd = new ListModelList<LocalPratica>();
   }

}
