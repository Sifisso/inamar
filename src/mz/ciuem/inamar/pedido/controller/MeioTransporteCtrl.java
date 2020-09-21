package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.MeioTransporte;
import mz.ciuem.inamar.entity.MeioTransportePedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;
import mz.ciuem.inamar.service.MeioTransportePedidoService;
import mz.ciuem.inamar.service.MeioTransporteService;
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

public class MeioTransporteCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regMeioTransporte;
	private Combobox cbx_meioTransporte;
	private Listbox lbx_meioTransporte;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_meioTransportePedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private MeioTransporte _selectedmeioTransporte;
	
	
	
	@WireVariable
	private MeioTransporteService _meioTransporteService;
	@WireVariable
	private MeioTransportePedidoService _meioTransportePedidoService;
	private List<MeioTransporte> listLocalMeioTransporte, listMeioTransporteAdd = new ArrayList<MeioTransporte>();
	private ListModelList<MeioTransporte> listModelMeioTransporteAdd, listModelMeioTransporte;
	private List<MeioTransportePedido> _listLocalMTransportePedido = new ArrayList<MeioTransportePedido>();
	

	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_meioTransporteService =  (MeioTransporteService) SpringUtil.getBean("meioTransporteService");
		_meioTransportePedidoService = (MeioTransportePedidoService) SpringUtil.getBean("meioTransportePedidoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherMeioTransporte(_pedido);
		listaMeioTransportePedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportMeioTransportePedido.jrxml", _listLocalMTransportePedido, mapaParam, win_regMeioTransporte);
   	}
	
	public void onSelect$cbx_meioTransporte() {
		_selectedmeioTransporte = cbx_meioTransporte.getSelectedItem().getValue();
		
    }

	public void onClick$btn_adicionar() {
	  
		listMeioTransporteAdd.add(_selectedmeioTransporte);
		listModelMeioTransporteAdd = new ListModelList<MeioTransporte>(listMeioTransporteAdd);
		
		lbx_meioTransporte.setModel(listModelMeioTransporteAdd);
		
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);
		cbx_meioTransporte.removeChild(cbx_meioTransporte.getSelectedItem());
		cbx_meioTransporte.setRawValue(null);
		listLocalMeioTransporte.remove(_selectedmeioTransporte);
		_selectedmeioTransporte = null;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		MeioTransporte mtr = (MeioTransporte) e.getData();
		
		listMeioTransporteAdd.remove(mtr);
        listModelMeioTransporteAdd = new ListModelList<MeioTransporte>(listMeioTransporteAdd);
		lbx_meioTransporte.setModel(listModelMeioTransporteAdd);
		
		listLocalMeioTransporte.add(mtr);
		listModelMeioTransporte = new ListModelList<MeioTransporte>(listLocalMeioTransporte);
		cbx_meioTransporte.setModel(listModelMeioTransporte);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_meioTransporte.getItems()) {

			MeioTransporte mt = (MeioTransporte) listItem.getValue();

			MeioTransportePedido _mtp = new MeioTransportePedido();
			
			_mtp.setMeioTransporte(mt);
			
			_mtp.setPedido(_pedido);

			_meioTransportePedidoService.create(_mtp);
		}
		
		listaMeioTransportePedido(_pedido);
		preencherMeioTransporte(_pedido);
		listMeioTransporteAdd.clear();
		listLocalMeioTransporte.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Meios de Transporte Adicionados com Sucesso", "info");
	}
	
	private void listaMeioTransportePedido(Pedido _pedido) {
		_listLocalMTransportePedido = _meioTransportePedidoService.findByPedido(_pedido);
		lbx_meioTransportePedido.setModel(new ListModelList<MeioTransportePedido>(_listLocalMTransportePedido));
	}

	private void preencherMeioTransporte(Pedido _pedido) {
		listLocalMeioTransporte = _meioTransporteService.findNotInPedido(_pedido);
		listModelMeioTransporte = new ListModelList<MeioTransporte>(listLocalMeioTransporte);
		cbx_meioTransporte.setModel(listModelMeioTransporte);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_meioTransporte, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_meioTransporte.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_meioTransportePedido.clearSelection();
		cbx_meioTransporte.setRawValue(null);
		_selectedmeioTransporte = null;
		listLocalMeioTransporte = new ArrayList<MeioTransporte>();
		listModelMeioTransporteAdd = new ListModelList<MeioTransporte>();
   }

}
