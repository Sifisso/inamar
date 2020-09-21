package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.service.InstrumentoLegalService;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;
import mz.ciuem.inamar.service.PedidoEtapaService;
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

public class InstLegalPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regInstLegalPedido;
	private Combobox cbx_instLegal;
	private Listbox lbx_instLegal;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_instLegalPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private InstrumentoLegal _selectedInstrumentoLegal;
	
	@WireVariable
	private InstrumentoLegalService _instrumentoLegalService;
	@WireVariable
	private PedidoEtapaService _pedidoEtapaServiceService;
	private List<InstrumentoLegal> listInstrumentoLegal, listInstrumentoLegalAdd = new ArrayList<InstrumentoLegal>();
	private ListModelList<InstrumentoLegal> listModelInstrumentoLegalAdd, listModelInstrumentoLegal;
	private List<PedidoEtapa> _listPedidoEtapa = new ArrayList<PedidoEtapa>();
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_instrumentoLegalService =  (InstrumentoLegalService) SpringUtil.getBean("instrumentoLegalService");
		_pedidoEtapaServiceService = (PedidoEtapaService) SpringUtil.getBean("pedidoEtapaService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherInstrumentoLega(_pedido);
		listaIntrLegalPedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalPraticaPedido.jrxml", _listPedidoEtapa, mapaParam, win_regInstLegalPedido);
   	}
	
	public void onSelect$cbx_localPratica() {
		
		_selectedInstrumentoLegal = (InstrumentoLegal) cbx_instLegal.getSelectedItem().getValue();
    }

	public void onClick$btn_adicionar() {
		listInstrumentoLegalAdd.add((InstrumentoLegal)cbx_instLegal.getSelectedItem().getValue());
		listModelInstrumentoLegalAdd = new ListModelList<InstrumentoLegal>(listInstrumentoLegalAdd);
		lbx_instLegal.setModel(listModelInstrumentoLegalAdd);
		
	
		listInstrumentoLegal.remove((InstrumentoLegal)cbx_instLegal.getSelectedItem().getValue());
		cbx_instLegal.removeChild(cbx_instLegal.getSelectedItem());
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);		
		_selectedInstrumentoLegal = null;
		cbx_instLegal.setRawValue(null);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		InstrumentoLegal insL = (InstrumentoLegal) e.getData();
		
		listInstrumentoLegalAdd.remove(insL);
        listModelInstrumentoLegalAdd = new ListModelList<InstrumentoLegal>(listInstrumentoLegalAdd);
		lbx_instLegal.setModel(listModelInstrumentoLegalAdd);
		
		listInstrumentoLegal.add(insL);
		listModelInstrumentoLegal = new ListModelList<InstrumentoLegal>(listInstrumentoLegal);
		cbx_instLegal.setModel(listModelInstrumentoLegal);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_instLegal.getItems()) {

			InstrumentoLegal il = (InstrumentoLegal) listItem.getValue();

			PedidoEtapa _pe = new PedidoEtapa();
			
			_pe.setInstrumentoLegal(il);
			
			_pe.setPedido(_pedido);

			_pedidoEtapaServiceService.create(_pe);
		}
		
		listaIntrLegalPedido(_pedido);
		preencherInstrumentoLega(_pedido);
		listInstrumentoLegalAdd.clear();
		listInstrumentoLegal.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Locais de Pratica Adicionados com Sucesso", "info");
	}
	
	private void listaIntrLegalPedido(Pedido _pedido) {
		_listPedidoEtapa = _pedidoEtapaServiceService.findByPedido(_pedido);
		lbx_instLegalPedido.setModel(new ListModelList<PedidoEtapa>(_listPedidoEtapa));
	}

	private void preencherInstrumentoLega(Pedido _pedido) {
		listInstrumentoLegal = _instrumentoLegalService.findNotInPedido(_pedido);
		listModelInstrumentoLegal = new ListModelList<InstrumentoLegal>(listInstrumentoLegal);
		cbx_instLegal.setModel(listModelInstrumentoLegal);
		
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_instLegal, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_instLegal.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_instLegalPedido.clearSelection();
		cbx_instLegal.setRawValue(null);
		_selectedInstrumentoLegal= null;
		listInstrumentoLegal= new ArrayList<InstrumentoLegal>();
		listModelInstrumentoLegalAdd = new ListModelList<InstrumentoLegal>();
   }

}
