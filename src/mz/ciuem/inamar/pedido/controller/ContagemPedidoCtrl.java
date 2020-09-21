package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.ContagemPedido;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.ContagemPedidoService;
import mz.ciuem.inamar.service.ContagemService;
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

public class ContagemPedidoCtrl extends GenericForwardComposer{
	
	    //Superior
		private Window win_regContagemPedido;
		private Combobox cbx_contagem;
		private Listbox lbx_contagem;
		
		private Button btn_cancelar;
		private Button btn_gravar;
		private Button btn_actualizar;
		private Button btn_adicionar;
		private Button btn_imprimir;
		
		//Inferior
		private Listbox lbx_contagemPedido;
		
		private Execution ex = Executions.getCurrent();

		private Pedido _pedido;
		private Contagem _selectedContagem;
		
		@WireVariable
		private ContagemService _contagemService;
		@WireVariable
		private ContagemPedidoService _contagemPedidoService;
		
		private List<Contagem> listContagem, listContagemAdd = new ArrayList<Contagem>();
		private ListModelList<Contagem> listModelContagemAdd, listModelContagem;
		private List<ContagemPedido> _listContagemPedido = new ArrayList<ContagemPedido>();
		
		
		@SuppressWarnings("unchecked")
		@Override
		public void doBeforeComposeChildren(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doBeforeComposeChildren(comp);
			
			_contagemService =  (ContagemService) SpringUtil.getBean("contagemService");
			_contagemPedidoService = (ContagemPedidoService) SpringUtil.getBean("contagemPedidoService");
			_pedido = (Pedido) ex.getArg().get("_pedido");
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(comp);
			
			preencherContagem(_pedido);
			listaContagemPedido(_pedido);
		}
		
	   	public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
	        mapaParam.put("imagemLogo", inputV);
	        mapaParam.put("listNome", _pedido.getDescricao());
	   		MasterRep.imprimir("/reportParam/reportContagemPedido.jrxml", _listContagemPedido, mapaParam, win_regContagemPedido);
	   	}
		
		public void onSelect$cbx_contagem() {
			
			_selectedContagem = cbx_contagem.getSelectedItem().getValue();
			
	    }

		public void onClick$btn_adicionar() {
		  
			listContagemAdd.add(_selectedContagem);
			listModelContagemAdd = new ListModelList<Contagem>(listContagemAdd);
			
			lbx_contagem.setModel(listModelContagemAdd);
			
			btn_actualizar.setVisible(false);
			btn_gravar.setVisible(true);
			btn_cancelar.setVisible(true);
			cbx_contagem.removeChild(cbx_contagem.getSelectedItem());
			cbx_contagem.setRawValue(null);
			listContagem.remove(_selectedContagem);
			_selectedContagem = null;

		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void onRemover(ForwardEvent e){
			Contagem ctg = (Contagem) e.getData();
			
			listContagemAdd.remove(ctg);
	        listModelContagemAdd = new ListModelList<Contagem>(listContagemAdd);
			lbx_contagem.setModel(listModelContagemAdd);
			
			listContagem.add(ctg);
			listModelContagem = new ListModelList<Contagem>(listContagem);
			cbx_contagem.setModel(listModelContagem);
			
		}
		
		public void onClick$btn_gravar() {

			for (Listitem listItem : lbx_contagem.getItems()) {

				Contagem cn = (Contagem) listItem.getValue();

				ContagemPedido _ctg = new ContagemPedido();
				_ctg.setContagem(cn);
				
				_ctg.setPedido(_pedido);

				_contagemPedidoService.create(_ctg);
			}
			
			listaContagemPedido(_pedido);
			preencherContagem(_pedido);
			listContagemAdd.clear();
			listContagem.clear();
			btn_gravar.setVisible(false);
			btn_cancelar.setVisible(false);
			limparCampos();
			showNotifications("Contagens Adicionadas com Sucesso", "info");
		}
		
		private void listaContagemPedido(Pedido _pedido) {
			_listContagemPedido = _contagemPedidoService.findByPedido(_pedido);
			lbx_contagemPedido.setModel(new ListModelList<ContagemPedido>(_listContagemPedido));
		}

		private void preencherContagem(Pedido _pedido) {
			listContagem = _contagemService.findNotInPedido(_pedido);
			listModelContagem = new ListModelList<Contagem>(listContagem);
			cbx_contagem.setModel(listModelContagem);
		}
		
		public void showNotifications(String message, String type) {
			Clients.showNotification(message, type, lbx_contagem, "before_center",
					4000, true);
		}
		
		private void limparCampos() {
			lbx_contagem.getItems().clear();
			btn_gravar.setVisible(false);
			btn_cancelar.setVisible(false);
			btn_actualizar.setVisible(false);
			lbx_contagemPedido.clearSelection();
			cbx_contagem.setRawValue(null);
			_selectedContagem = null;
			listContagem = new ArrayList<Contagem>();
			listModelContagemAdd = new ListModelList<Contagem>();
	   }

		


}
