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
import mz.ciuem.inamar.entity.Rota;
import mz.ciuem.inamar.entity.RotaPedido;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;
import mz.ciuem.inamar.service.RotaPedidoService;
import mz.ciuem.inamar.service.RotaService;
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

public class RotaPedidoCtrl extends GenericForwardComposer{
	
	
	//Superior
	private Window win_regRotaPedido;
	private Combobox cbx_localRota;
	private Listbox lbx_rota;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_rotaPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private Rota _selectedRota;
	
	
	
	@WireVariable
	private RotaService _rotaService;
	@WireVariable
	private RotaPedidoService _rotaPedidoService;
	
	private List<Rota> listRota, listRotaAdd = new ArrayList<Rota>();
	private ListModelList<Rota> listModelRotaAdd, listModelRota;
	private List<RotaPedido> _listRotaPedido = new ArrayList<RotaPedido>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_rotaService =  (RotaService) SpringUtil.getBean("rotaService");
		_rotaPedidoService = (RotaPedidoService) SpringUtil.getBean("rotaPedidoService");
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
   		MasterRep.imprimir("/reportParam/reportRotaPedido.jrxml", _listRotaPedido, mapaParam, win_regRotaPedido);
   	}
	
	public void onSelect$cbx_localRota() {
		
		_selectedRota = cbx_localRota.getSelectedItem().getValue();
		
    }

	public void onClick$btn_adicionar() {
	  
		listRotaAdd.add(_selectedRota);
		listModelRotaAdd = new ListModelList<Rota>(listRotaAdd);
		lbx_rota.setModel(listModelRotaAdd);
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);
		cbx_localRota.removeChild(cbx_localRota.getSelectedItem());
		cbx_localRota.setRawValue(null);
		listRota.remove(_selectedRota);
		_selectedRota = null;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		Rota r = (Rota) e.getData();
		
		listRotaAdd.remove(r);
        listModelRotaAdd = new ListModelList<Rota>(listRotaAdd);
		lbx_rota.setModel(listModelRotaAdd);
		
		listRota.add(r);
		listModelRota = new ListModelList<Rota>(listRota);
		cbx_localRota.setModel(listModelRota);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_rota.getItems()) {

			Rota r = (Rota) listItem.getValue();

			RotaPedido _rp = new RotaPedido();
			_rp.setRota(r);
			
			_rp.setPedido(_pedido);

			_rotaPedidoService.create(_rp);
		}
		
		listaLocalPraticaPedido(_pedido);
		preencherLocalPratica(_pedido);
		listRotaAdd.clear();
		listRota.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Rotas Adicionadas com Sucesso", "info");
	}
	
	private void listaLocalPraticaPedido(Pedido _pedido) {
		_listRotaPedido = _rotaPedidoService.findByPedido(_pedido);
		lbx_rotaPedido.setModel(new ListModelList<RotaPedido>(_listRotaPedido));
	}

	private void preencherLocalPratica(Pedido _pedido) {
		listRota = _rotaService.findNotInPedido(_pedido);
		listModelRota = new ListModelList<Rota>(listRota);
		cbx_localRota.setModel(listModelRota);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_rota, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_rota.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_rotaPedido.clearSelection();
		cbx_localRota.setRawValue(null);
		_selectedRota = null;
		listRota = new ArrayList<Rota>();
		listModelRotaAdd = new ListModelList<Rota>();
   }


}
