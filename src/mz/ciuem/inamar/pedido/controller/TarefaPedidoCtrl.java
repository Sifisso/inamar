package mz.ciuem.inamar.pedido.controller;

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

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.dao.imlp.GenericDaoImpl;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.entity.Tarefa;
import mz.ciuem.inamar.entity.TarefaNaEtapa;
import mz.ciuem.inamar.service.InstrumentoLegalService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.TarefaNaEtapaService;
import mz.ciuem.inamar.service.TarefaService;

public class TarefaPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regTarefaPedido;
	private Combobox cbx_tarefa;
	private Listbox lbx_tarefas;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_tarefaPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private Tarefa _selectedTarefa;
	
	@WireVariable
	private TarefaService _tarefaService;
	@WireVariable
	private TarefaNaEtapaService _tarefaNaEtapaService;
	private List<Tarefa> listTarefa, listTarefaAdd = new ArrayList<Tarefa>();
	private ListModelList<Tarefa> listModelTarefaAdd, listModelTarefa;
	private List<TarefaNaEtapa> _listTarefaNaEtapa = new ArrayList<TarefaNaEtapa>();
	

	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_tarefaService =  (TarefaService) SpringUtil.getBean("tarefaService");
		_tarefaNaEtapaService =  (TarefaNaEtapaService) SpringUtil.getBean("tarefaNaEtapaService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherTarefas(_pedido);
		listaTarefasPedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalPraticaPedido.jrxml", _listTarefaNaEtapa, mapaParam, win_regTarefaPedido);
   	}
	
	public void onSelect$cbx_tarefa() {
		
		_selectedTarefa = (Tarefa) cbx_tarefa.getSelectedItem().getValue();
    }

	public void onClick$btn_adicionar() {
		listTarefaAdd.add((Tarefa)cbx_tarefa.getSelectedItem().getValue());
		listModelTarefaAdd = new ListModelList<Tarefa>(listTarefaAdd);
		lbx_tarefas.setModel(listModelTarefaAdd);
		
	
		listTarefa.remove((Tarefa)cbx_tarefa.getSelectedItem().getValue());
		cbx_tarefa.removeChild(cbx_tarefa.getSelectedItem());
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);		
		_selectedTarefa = null;
		cbx_tarefa.setRawValue(null);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		Tarefa insL = (Tarefa) e.getData();
		
		listTarefaAdd.remove(insL);
        listModelTarefaAdd = new ListModelList<Tarefa>(listTarefaAdd);
		lbx_tarefas.setModel(listModelTarefaAdd);
		
		listTarefa.add(insL);
		listModelTarefa = new ListModelList<Tarefa>(listTarefa);
		cbx_tarefa.setModel(listModelTarefa);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_tarefas.getItems()) {

			Tarefa il = (Tarefa) listItem.getValue();

			TarefaNaEtapa _pe = new TarefaNaEtapa();
			
			_pe.setTarefa(il);
			
			_pe.setPedido(_pedido);

			_tarefaNaEtapaService.create(_pe);
		}
		
		listaTarefasPedido(_pedido);
		preencherTarefas(_pedido);
		listTarefaAdd.clear();
		listTarefa.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Tarefas Adicionadas com Sucesso", "info");
	}
	
	private void listaTarefasPedido(Pedido _pedido) {
		//Filtrar
		_listTarefaNaEtapa = _tarefaNaEtapaService.findByPedido(_pedido);
		lbx_tarefaPedido.setModel(new ListModelList<TarefaNaEtapa>(_listTarefaNaEtapa));
	}

	private void preencherTarefas(Pedido _pedido) {
		//Filtrar
		listTarefa = _tarefaService.findNotInPedido(_pedido);
		listModelTarefa = new ListModelList<Tarefa>(listTarefa);
		cbx_tarefa.setModel(listModelTarefa);
		
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_tarefas, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_tarefas.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_tarefaPedido.clearSelection();
		cbx_tarefa.setRawValue(null);
		_selectedTarefa = null;
		listTarefa = new ArrayList<Tarefa>();
		listModelTarefaAdd = new ListModelList<Tarefa>();
   }


}
