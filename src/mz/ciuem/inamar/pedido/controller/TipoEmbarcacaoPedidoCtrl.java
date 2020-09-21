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
import mz.ciuem.inamar.entity.TipoEmbarcacao;
import mz.ciuem.inamar.entity.TipoEmbarcacaoPedido;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;
import mz.ciuem.inamar.service.TipoEmbarcacaoPedidoService;
import mz.ciuem.inamar.service.TipoEmbarcacaoService;
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

public class TipoEmbarcacaoPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regTipoEmbarcacaoPedido;
	private Combobox cbx_tipoEmbarcacao;
	private Listbox lbx_tipoEmbarcacao;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_tipoEmbarcacaoPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private TipoEmbarcacao _selectedTipoEmbarcacao;
	
	
	
	@WireVariable
	private TipoEmbarcacaoService _tipoEmbarcacaoService;
	@WireVariable
	private TipoEmbarcacaoPedidoService _tipoEmbarcacaoPedidoService;
	private List<TipoEmbarcacao> listTipoEmbarcacao, listTipoEmbarcacaoAdd = new ArrayList<TipoEmbarcacao>();
	private ListModelList<TipoEmbarcacao> listModelTipoEmbarcacaoAdd, listModelTipoEmbarcacao;
	private List<TipoEmbarcacaoPedido> _listTipoEmbarcacaoPedido = new ArrayList<TipoEmbarcacaoPedido>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_tipoEmbarcacaoService =  (TipoEmbarcacaoService) SpringUtil.getBean("tipoEmbarcacaoService");
		_tipoEmbarcacaoPedidoService = (TipoEmbarcacaoPedidoService) SpringUtil.getBean("tipoEmbarcacaoPedidoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherTipoEmbarcacao(_pedido);
		listaTipoEmbarcacaoPedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportTipoEmbarcacaoPedido.jrxml", _listTipoEmbarcacaoPedido, mapaParam, win_regTipoEmbarcacaoPedido);
   	}
	
	public void onSelect$cbx_tipoEmbarcacao() {
		
		_selectedTipoEmbarcacao = cbx_tipoEmbarcacao.getSelectedItem().getValue();
		
    }

	public void onClick$btn_adicionar() {
	  
		listTipoEmbarcacaoAdd.add(_selectedTipoEmbarcacao);
		listModelTipoEmbarcacaoAdd = new ListModelList<TipoEmbarcacao>(listTipoEmbarcacaoAdd);
		
		lbx_tipoEmbarcacao.setModel(listModelTipoEmbarcacaoAdd);
		
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);
		cbx_tipoEmbarcacao.removeChild(cbx_tipoEmbarcacao.getSelectedItem());
		cbx_tipoEmbarcacao.setRawValue(null);
		listTipoEmbarcacao.remove(_selectedTipoEmbarcacao);
		_selectedTipoEmbarcacao = null;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		TipoEmbarcacao tem = (TipoEmbarcacao) e.getData();
		
		listTipoEmbarcacaoAdd.remove(tem);
        listModelTipoEmbarcacaoAdd = new ListModelList<TipoEmbarcacao>(listTipoEmbarcacaoAdd);
		lbx_tipoEmbarcacao.setModel(listModelTipoEmbarcacaoAdd);
		
		listTipoEmbarcacao.add(tem);
		listModelTipoEmbarcacao = new ListModelList<TipoEmbarcacao>(listTipoEmbarcacao);
		cbx_tipoEmbarcacao.setModel(listModelTipoEmbarcacao);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_tipoEmbarcacao.getItems()) {

			TipoEmbarcacao te = (TipoEmbarcacao) listItem.getValue();

			TipoEmbarcacaoPedido _tep = new TipoEmbarcacaoPedido();
			_tep.setTipoEmbarcacao(te);
			
			_tep.setPedido(_pedido);

			_tipoEmbarcacaoPedidoService.create(_tep);
		}
		
		listaTipoEmbarcacaoPedido(_pedido);
		preencherTipoEmbarcacao(_pedido);
		listTipoEmbarcacaoAdd.clear();
		listTipoEmbarcacao.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Tipos de Embarcação Adicionados com Sucesso", "info");
	}
	
	private void listaTipoEmbarcacaoPedido(Pedido _pedido) {
		_listTipoEmbarcacaoPedido = _tipoEmbarcacaoPedidoService.findByPedido(_pedido);
		lbx_tipoEmbarcacaoPedido.setModel(new ListModelList<TipoEmbarcacaoPedido>(_listTipoEmbarcacaoPedido));
	}

	private void preencherTipoEmbarcacao(Pedido _pedido) {
		listTipoEmbarcacao = _tipoEmbarcacaoService.findNotInPedido(_pedido);
		listModelTipoEmbarcacao = new ListModelList<TipoEmbarcacao>(listTipoEmbarcacao);
		cbx_tipoEmbarcacao.setModel(listModelTipoEmbarcacao);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_tipoEmbarcacao, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_tipoEmbarcacao.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_tipoEmbarcacaoPedido.clearSelection();
		cbx_tipoEmbarcacao.setRawValue(null);
		_selectedTipoEmbarcacao = null;
		listTipoEmbarcacao = new ArrayList<TipoEmbarcacao>();
		listModelTipoEmbarcacaoAdd = new ListModelList<TipoEmbarcacao>();
   }

	

}
