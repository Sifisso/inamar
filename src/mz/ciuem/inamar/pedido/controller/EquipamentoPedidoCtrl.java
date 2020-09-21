package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Equipamento;
import mz.ciuem.inamar.entity.EquipamentoPedido;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.EquipamentoPedidoService;
import mz.ciuem.inamar.service.EquipamentoService;
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

public class EquipamentoPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regLocalEquipamentoPedido;
	private Combobox cbx_equipamento;
	private Listbox lbx_equipamento;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_equipamentoPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private Equipamento _selectedEquipamento;
	
	
	
	@WireVariable
	private EquipamentoService _equipamentoService;
	@WireVariable
	private EquipamentoPedidoService _equipamentoPedidoService;
	private List<Equipamento> listEquipamento, listEquipamentoAdd = new ArrayList<Equipamento>();
	private ListModelList<Equipamento> listModelEquipamentoAdd, listModelEquipamento;
	private List<EquipamentoPedido> _listEquipamentoPedido = new ArrayList<EquipamentoPedido>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_equipamentoService =  (EquipamentoService) SpringUtil.getBean("equipamentoService");
		_equipamentoPedidoService = (EquipamentoPedidoService) SpringUtil.getBean("equipamentoPedidoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherEquipamento(_pedido);
		listaEquipamentoPedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportEquipamentoPedido.jrxml", _listEquipamentoPedido, mapaParam, win_regLocalEquipamentoPedido);
   	}
	
	public void onSelect$cbx_equipamento() {
		
		_selectedEquipamento = cbx_equipamento.getSelectedItem().getValue();
		
    }

	public void onClick$btn_adicionar() {
	  
		listEquipamentoAdd.add(_selectedEquipamento);
		listModelEquipamentoAdd = new ListModelList<Equipamento>(listEquipamentoAdd);
		
		lbx_equipamento.setModel(listModelEquipamentoAdd);
		
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);
		cbx_equipamento.removeChild(cbx_equipamento.getSelectedItem());
		cbx_equipamento.setRawValue(null);
		listEquipamento.remove(_selectedEquipamento);
		_selectedEquipamento = null;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		Equipamento equi = (Equipamento) e.getData();
		
		listEquipamentoAdd.remove(equi);
        listModelEquipamentoAdd = new ListModelList<Equipamento>(listEquipamentoAdd);
		lbx_equipamento.setModel(listModelEquipamentoAdd);
		
		listEquipamento.add(equi);
		listModelEquipamento = new ListModelList<Equipamento>(listEquipamento);
		cbx_equipamento.setModel(listModelEquipamento);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_equipamento.getItems()) {

			Equipamento eq = (Equipamento) listItem.getValue();

			EquipamentoPedido _eqp = new EquipamentoPedido();
			_eqp.setEquipamento(eq);
			
			_eqp.setPedido(_pedido);

			_equipamentoPedidoService.create(_eqp);
		}
		
		listaEquipamentoPedido(_pedido);
		preencherEquipamento(_pedido);
		listEquipamentoAdd.clear();
		listEquipamento.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Equipamentos Adicionados com Sucesso", "info");
	}
	
	private void listaEquipamentoPedido(Pedido _pedido) {
		_listEquipamentoPedido = _equipamentoPedidoService.findByPedido(_pedido);
		lbx_equipamentoPedido.setModel(new ListModelList<EquipamentoPedido>(_listEquipamentoPedido));
	}

	private void preencherEquipamento(Pedido _pedido) {
		listEquipamento = _equipamentoService.findNotInPedido(_pedido);
		listModelEquipamento = new ListModelList<Equipamento>(listEquipamento);
		cbx_equipamento.setModel(listModelEquipamento);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_equipamento, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_equipamento.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_equipamentoPedido.clearSelection();
		cbx_equipamento.setRawValue(null);
		_selectedEquipamento = null;
		listEquipamento = new ArrayList<Equipamento>();
		listModelEquipamentoAdd = new ListModelList<Equipamento>();
   }
	

}
