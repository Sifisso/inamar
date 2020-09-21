package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.InstrumentoLegal;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.service.InstrumentoLegalService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TaxaService;
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

public class TaxaPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regTaxaPedido;
	private Combobox cbx_taxas, cbx_subArea;
	private Listbox lbx_taxas;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_taxasPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private Taxa _selectedTaxa;
	
	@WireVariable
	private TaxaService _taxaService;
	@WireVariable
	private SubAreaService _subAreaService;
	
	@WireVariable
	private TaxaPedidoService _taxaPedidoService;
	
	private List<Taxa> listTaxa, listTaxaAdd = new ArrayList<Taxa>();
	private ListModelList<Taxa> listModelTaxaAdd, listModelTaxa;
	private List<TaxaPedido> _listTaxaPedido= new ArrayList<TaxaPedido>();
	

	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_taxaService =   (TaxaService) SpringUtil.getBean("taxaService");
		_taxaPedidoService =  (TaxaPedidoService) SpringUtil.getBean("taxaPedidoService");
		_subAreaService = (SubAreaService) SpringUtil.getBean("subAreaService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		//preencherTaxas(_pedido);
		listaLocalTaxasPedido(_pedido);
		preencherSubAreas();
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalPraticaPedido.jrxml", _listTaxaPedido, mapaParam, win_regTaxaPedido);
   	}
	
	public void onSelect$cbx_taxas() {
		_selectedTaxa = (Taxa) cbx_taxas.getSelectedItem().getValue();
    }

	public void onClick$btn_adicionar() {
		listTaxaAdd.add((Taxa)cbx_taxas.getSelectedItem().getValue());
		
		listModelTaxaAdd = new ListModelList<Taxa>(listTaxaAdd);
		lbx_taxas.setModel(listModelTaxaAdd);
	
		listTaxa.remove((Taxa)cbx_taxas.getSelectedItem().getValue());
		cbx_taxas.removeChild(cbx_taxas.getSelectedItem());
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);		
		_selectedTaxa = null;
		cbx_taxas.setRawValue(null);

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		Taxa txp = (Taxa) e.getData();
		
		listTaxaAdd.remove(txp);
        listModelTaxaAdd = new ListModelList<Taxa>(listTaxaAdd);
		lbx_taxas.setModel(listModelTaxaAdd);
		
		listTaxa.add(txp);
		listModelTaxa = new ListModelList<Taxa>(listTaxa);
		cbx_taxas.setModel(listModelTaxa);
		
	}
	
	public void onSelect$cbx_subArea(){
		preencherTaxas((SubArea)cbx_subArea.getSelectedItem().getValue(), _pedido);
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_taxas.getItems()) {

			Taxa tx = (Taxa) listItem.getValue();

			TaxaPedido _txp = new TaxaPedido();
			
			_txp.setTaxa(tx);
			_txp.setPedido(_pedido);

			_taxaPedidoService.create(_txp);
		}
		
		listaLocalTaxasPedido(_pedido);
		//preencherTaxas(_pedido);
		cbx_subArea.setRawValue(null);
		cbx_taxas.setRawValue(null);
		listTaxaAdd.clear();
		listModelTaxa.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Taxas Adicionadas com Sucesso", "info");
	}
	
	private void listaLocalTaxasPedido(Pedido _pedido) {
		//Filtrar
		_listTaxaPedido = _taxaPedidoService.findByPedido(_pedido);
		lbx_taxasPedido.setModel(new ListModelList<TaxaPedido>(_listTaxaPedido));
	}

	private void preencherTaxas(SubArea _subArea, Pedido _pedido) {
		listTaxa = _taxaService.findNotInPedidoInSubSrea(_subArea, _pedido);
		listModelTaxa = new ListModelList<Taxa>(listTaxa);
		cbx_taxas.setModel(listModelTaxa);
	}
	
	private void preencherSubAreas() {
		//Filtrar Atraves da Subarea selecionada
		List<SubArea> listSubArea = _subAreaService.findByArea(_pedido.getTipoPedido().getArea());
		cbx_subArea.setModel(new ListModelList<SubArea>(listSubArea));
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_taxas, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_taxas.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_taxasPedido.clearSelection();
		cbx_taxas.setRawValue(null);
		_selectedTaxa = null;
		listTaxa= new ArrayList<Taxa>();
		listModelTaxaAdd = new ListModelList<Taxa>();
   }

}



