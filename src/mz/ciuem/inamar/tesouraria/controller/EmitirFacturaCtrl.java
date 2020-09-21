package mz.ciuem.inamar.tesouraria.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Pagamento;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.TipoRequisito;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.ItensPeticaoService;
import mz.ciuem.inamar.service.PagamentoService;
import mz.ciuem.inamar.service.PeticaoService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.lowagie.text.pdf.AcroFields.Item;
import com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm;

public class EmitirFacturaCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_confirmarPeticao;
	
	private Label lbl_pedido, lbl_nome, lbl_custo, lbl_descricao;
	private Listbox lbx_addItens;
	
	private Listbox lbx_itens; 
	
	private Doublebox dxb_custo;
	private Textbox txb_descricaoI;
	
	private Button btn_gravar;
	private Button btn_actualizar; 
	private Button btn_cancelar;
	
	@WireVariable
	private ItensPeticaoService _ItensPeticaoService;
	
	private List<ItensPeticao> listItemPeticao, listItemPeticaoAdd = new ArrayList<ItensPeticao>();
	private ListModelList<ItensPeticao> listModelItemPeticaoAdd, listModelItemPeticao;
	private List<ItensPeticao> _listItensPeticao= new ArrayList<ItensPeticao>();
	
	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private PagamentoService _pagamentoService;
	
	private List<ItensPeticao> _ItensPeticao = new ArrayList<ItensPeticao>();
	private List<Peticao> _peticaoList = new ArrayList<Peticao>();
	
	
	private Peticao _peticao;
	private Pedido _pedido;
	private ItensPeticao _selectedItemPeticao;
	private ItensPeticao _itensPeticao;
	private double Total=0.0;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_ItensPeticaoService =(ItensPeticaoService) SpringUtil.getBean("itensPeticaoService");
		
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
		listar();
		
	}
	
	private void listar(){
		
		_ItensPeticao = _ItensPeticaoService.findByPeticaoID(_peticao);
		lbx_itens.setModel(new ListModelList<ItensPeticao>(_ItensPeticao));
		
		double total=0.0; 
		
		for(ItensPeticao valor:_ItensPeticao){
			total= total+valor.getCusto();				
		}
		
		lbl_custo.setValue(""+total+"Mtn");
		
	}
	
	private void preencherCampos() {
		if(_peticao!=null){
			lbl_nome.setValue(_peticao.getUtente());
			lbl_pedido.setValue(_peticao.getDescricao());
			//lbl_custo.setValue(" "+_itensPeticao.getCusto());
			
			//dxb_custo.setValue(_peticao.getValor());
			
		}
	}
	
	public void onClick$btn_adicionar() {
		
		ItensPeticao ip = new ItensPeticao();
		
		ip.setDescricao(txb_descricaoI.getValue());
		ip.setCusto(dxb_custo.getValue());
		listItemPeticaoAdd.add(ip);
		
		
		listModelItemPeticaoAdd = new ListModelList<ItensPeticao>(listItemPeticaoAdd);
		lbx_addItens.setModel(listModelItemPeticaoAdd);
	
		
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);		
		_selectedItemPeticao = null;
		txb_descricaoI.setRawValue(null);
		dxb_custo.setRawValue(null);

	}
	
	public void onRemover(ForwardEvent e){
		ItensPeticao ip = (ItensPeticao) e.getData();
		
		if(listItemPeticaoAdd.contains(ip)){
			listItemPeticaoAdd.remove(ip);
			listModelItemPeticaoAdd = new ListModelList<ItensPeticao>(listItemPeticaoAdd);
			lbx_addItens.setModel(listModelItemPeticaoAdd);
		}
	}
	
	
	public void onClick$btn_gravar() {
		
		ItensPeticao itensPeticao = new ItensPeticao();
		
		//double total = 0;
		
		for (Listitem listitem : lbx_addItens.getItems()){
						
			ItensPeticao ip =(ItensPeticao) listitem.getValue();
			itensPeticao.setDescricao(ip.getDescricao());
			itensPeticao.setCusto(ip.getCusto());
						
		   }
					
		 itensPeticao.setPeticao(_peticao);
		
		_ItensPeticaoService.create(itensPeticao);
		
		txb_descricaoI.setRawValue(null);
		dxb_custo.setRawValue(null);
		listItemPeticaoAdd.clear();
		
		
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		listar();
		showNotifications("Taxas Adicionadas com Sucesso", "info");
		
	}
	
	public void limparCampos(){
		lbx_addItens.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_itens.clearSelection();
		txb_descricaoI.setRawValue(null);
		dxb_custo.setRawValue(null);
		_selectedItemPeticao = null;
		listItemPeticao = new ArrayList<ItensPeticao>();
		listModelItemPeticaoAdd = new ListModelList<ItensPeticao>();
		
		
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_addItens, "before_center",
				4000, true);
	}

	public void onClickClose(ForwardEvent e){
		win_confirmarPeticao.detach();
	}


}
