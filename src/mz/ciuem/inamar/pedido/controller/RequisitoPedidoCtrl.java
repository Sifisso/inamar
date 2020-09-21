package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.entity.TipoRequisito;
import mz.ciuem.inamar.service.InstrumentoLegalService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class RequisitoPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regRequisitosPedido;
	private Combobox cbx_requisitos;
	private Listbox lbx_requisitos;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_RequisitosPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private TipoRequisito _selectedTipoRequisito;
	
	@WireVariable
	private TipoRequisitoService _tipoRequisitoService;
	@WireVariable
	private PedidoRequisitoService _pedidoRequisitoService;
	private List<TipoRequisito> listTipoRequisito, listTipoRequisitoAdd = new ArrayList<TipoRequisito>();
	private ListModelList<TipoRequisito> listModelTipoRequisitoAdd, listModelTipoRequisito;
	private List<PedidoRequisito> _listPedidoRequisito = new ArrayList<PedidoRequisito>();
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_tipoRequisitoService =  (TipoRequisitoService) SpringUtil.getBean("tipoRequisitoService");
		_pedidoRequisitoService = (PedidoRequisitoService) SpringUtil.getBean("pedidoRequisitoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherTipoRequisito(_pedido);
		listaRequisitoPedido(_pedido);
	}
	
 	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalPraticaPedido.jrxml", _listPedidoRequisito, mapaParam, win_regRequisitosPedido);
   	}
	
	public void onSelect$cbx_requisitos() {
		_selectedTipoRequisito = (TipoRequisito) cbx_requisitos.getSelectedItem().getValue();
    }

	public void onClick$btn_adicionar() {
		listTipoRequisitoAdd.add((TipoRequisito)cbx_requisitos.getSelectedItem().getValue());
		listModelTipoRequisitoAdd = new ListModelList<TipoRequisito>(listTipoRequisitoAdd);
		lbx_requisitos.setModel(listModelTipoRequisitoAdd);
	
		listTipoRequisito.remove((TipoRequisito)cbx_requisitos.getSelectedItem().getValue());
		cbx_requisitos.removeChild(cbx_requisitos.getSelectedItem());
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);		
		_selectedTipoRequisito = null;
		cbx_requisitos.setRawValue(null);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		TipoRequisito insL = (TipoRequisito) e.getData();
		
		listTipoRequisitoAdd.remove(insL);
        listModelTipoRequisitoAdd = new ListModelList<TipoRequisito>(listTipoRequisitoAdd);
		lbx_requisitos.setModel(listModelTipoRequisitoAdd);
		
		listTipoRequisito.add(insL);
		listModelTipoRequisito = new ListModelList<TipoRequisito>(listTipoRequisito);
		cbx_requisitos.setModel(listModelTipoRequisito);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onActivo(ForwardEvent e){
		 PedidoRequisito pedidoRequisito = (PedidoRequisito) e.getData();
		 
		 pedidoRequisito.setActivo(false);
		 
		 _pedidoRequisitoService.update(pedidoRequisito);
		 
		 listaRequisitoPedido(_pedido);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onInactivo(ForwardEvent e){
		 PedidoRequisito pedidoRequisito = (PedidoRequisito) e.getData();
		 
		 pedidoRequisito.setActivo(true);
		 
		 _pedidoRequisitoService.update(pedidoRequisito);
		 
		 listaRequisitoPedido(_pedido);		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_requisitos.getItems()) {

			TipoRequisito tr = (TipoRequisito) listItem.getValue();

			PedidoRequisito _pr = new PedidoRequisito();
			
			_pr.setTipoRequisito(tr);
			_pr.setPedido(_pedido);

			_pedidoRequisitoService.create(_pr);
		}
		
		listaRequisitoPedido(_pedido);
		listaRequisitoPedido(_pedido);
		listModelTipoRequisitoAdd.clear();
		listTipoRequisito.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Requitisos Adicionados com Sucesso", "info");
	}
	
	private void listaRequisitoPedido(Pedido _pedido) {
		//Filtrar
		_listPedidoRequisito = _pedidoRequisitoService.findByPedido(_pedido);
		lbx_RequisitosPedido.setModel(new ListModelList<PedidoRequisito>(_listPedidoRequisito));
	}

	private void preencherTipoRequisito(Pedido _pedido) {
		//Filtrar
		listTipoRequisito = _tipoRequisitoService.findNotInPedido(_pedido);
		listModelTipoRequisito = new ListModelList<TipoRequisito>(listTipoRequisito);
		cbx_requisitos.setModel(listModelTipoRequisito);
		
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_requisitos, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_requisitos.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_RequisitosPedido.clearSelection();
		cbx_requisitos.setRawValue(null);
		_selectedTipoRequisito= null;
		listTipoRequisito= new ArrayList<TipoRequisito>();
		listModelTipoRequisitoAdd = new ListModelList<TipoRequisito>();
   }

}
