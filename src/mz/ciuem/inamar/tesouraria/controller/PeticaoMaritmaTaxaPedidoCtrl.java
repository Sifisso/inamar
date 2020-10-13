package mz.ciuem.inamar.tesouraria.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Pagamento;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.TipoRequisito;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.ItensPeticaoService;
import mz.ciuem.inamar.service.PagamentoService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TaxaService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.lowagie.text.pdf.AcroFields.Item;
import com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm;

public class PeticaoMaritmaTaxaPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_itensPeticaoReg;
	
	private Label lbl_pedido, lbl_nome, lbl_custo, lbl_descricao;
	private Listbox lbx_addItens;
	
	private Listbox lbx_itens, lbx_taxasPedido; 
	
	private Combobox cbx_taxaPedido;
	
	private Button btn_gravar;
	private Button btn_actualizar; 
	private Button btn_cancelar;
	
	private Execution ex = Executions.getCurrent();
	
	@WireVariable
	private ItensPeticaoService _ItensPeticaoService;
	
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	
	@WireVariable
	private TaxaPedidoService _taxaPedidoService;
	
	@WireVariable
	private TaxaService _taxaService;
	
	@WireVariable
	private PedidoService _pedidoService;
	
	private Pedido _pedido;
	private TaxaPedido _taxaPedido;
	
	private List<PeticaoMaritimoTaxaPedido> listpmtp = new ArrayList<PeticaoMaritimoTaxaPedido>();
	private List<ItensPeticao> listItemPeticao, listItemPeticaoAdd = new ArrayList<ItensPeticao>();
	private ListModelList<ItensPeticao> listModelItemPeticaoAdd, listModelItemPeticao;
	private List<ItensPeticao> _listItensPeticao= new ArrayList<ItensPeticao>();
	
	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private PagamentoService _pagamentoService;
	
	private List<ItensPeticao> _ItensPeticao = new ArrayList<ItensPeticao>();
	private List<PeticaoMaritimoTaxaPedido> _peticaoMaritimoTaxaPedidos = new ArrayList<PeticaoMaritimoTaxaPedido>();
	private List<TaxaPedido> _listTaxaPedido= new ArrayList<TaxaPedido>();
	private List<Peticao> _peticaoList = new ArrayList<Peticao>();
	
	
	private Peticao _peticao;
	private ItensPeticao _selectedItemPeticao;
	private ItensPeticao _itensPeticao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_ItensPeticaoService =(ItensPeticaoService) SpringUtil.getBean("itensPeticaoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_taxaService =   (TaxaService) SpringUtil.getBean("taxaService");
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_pedidoService = (PedidoService) SpringUtil.getBean("pedidoService");
		_taxaPedidoService =  (TaxaPedidoService) SpringUtil.getBean("taxaPedidoService");
		_taxaPedido = (TaxaPedido)ex.getArg().get("_taxaPedido");

		_pedido = (Pedido) ex.getArg().get("_pedido");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		preencherCampos();
		listarPeticao();
		//listaLocalTaxasPedido(_pedido);
		//preecherTaxa(_pedido);
		preecherTaxas();
		
	}
	
	public void listarPeticao(){
		_peticaoMaritimoTaxaPedidos = _peticaoMaritimoTaxaPedidoService.findByPeticao(_peticao);
		lbx_itens.setModel(new ListModelList<PeticaoMaritimoTaxaPedido>(_peticaoMaritimoTaxaPedidos));
		
		double total=0;
		for(PeticaoMaritimoTaxaPedido valor:_peticaoMaritimoTaxaPedidos){
			total = total+valor.getTaxaPedido().getTaxa().getValor();
		}
		//Messagebox.show("valor="+total);
		lbl_custo.setValue(""+total+"Mtn");
	}
	private void listaLocalTaxasPedido(Pedido pedido) {
		//Filtrar
		_listTaxaPedido = _taxaPedidoService.findByPedido(_pedido);
		lbx_taxasPedido.setModel(new ListModelList<TaxaPedido>(_listTaxaPedido));
		cbx_taxaPedido.setModel(new ListModelList<TaxaPedido>(_listTaxaPedido));
	}
	
	public void preecherTaxas(){
		_listTaxaPedido = _taxaPedidoService.findTaxaPedidoByPedido(_pedido);
		cbx_taxaPedido.setModel(new ListModelList<TaxaPedido>(_listTaxaPedido));
	}
	
	public void preecherTaxa(Pedido pedido){
		_peticaoMaritimoTaxaPedidos = _peticaoMaritimoTaxaPedidoService.findByTaxaPedido(_pedido);
		cbx_taxaPedido.setModel(new ListModelList<PeticaoMaritimoTaxaPedido>(_peticaoMaritimoTaxaPedidos));
		
	}
	
	private void preencherCampos() {
		if(_peticao!=null){
			lbl_nome.setValue(_peticao.getUtente());
			lbl_pedido.setValue(_peticao.getDescricao());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(final ForwardEvent e){
		
		Messagebox.show("Deseja remover a taxa selecionada?", "Remoção da taxa na petição",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
		
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					PeticaoMaritimoTaxaPedido ip = (PeticaoMaritimoTaxaPedido) e.getData();
					
					if(_peticaoMaritimoTaxaPedidos.contains(ip)){
						//_peticaoMaritimoTaxaPedidos.remove(ip);
						
						_peticaoMaritimoTaxaPedidoService.delete(ip);
						
						listarPeticao();
					}
					showNotifications("Taxa Removida", "warning");
				}
			}
		});
	}	
	
	
	public void onClick$btn_gravar() {
		
		PeticaoMaritimoTaxaPedido pmtp = new PeticaoMaritimoTaxaPedido();
		
		
		pmtp.setTaxaPedido((TaxaPedido)cbx_taxaPedido.getSelectedItem().getValue());
		pmtp.setPeticao(_peticao);
		_peticaoMaritimoTaxaPedidoService.create(pmtp);
		_listTaxaPedido.remove((TaxaPedido)cbx_taxaPedido.getSelectedItem().getValue());
		cbx_taxaPedido.removeChild(cbx_taxaPedido.getSelectedItem());
		
		limparCampos();
		listarPeticao();
		showNotifications("Taxa Adicionada com Sucesso", "info");
		
	}
	
	public void limparCampos(){
		cbx_taxaPedido.setRawValue(null);
		
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_addItens, "before_center",
				4000, true);
	}

	public void onClickClose(ForwardEvent e){
		win_itensPeticaoReg.detach();
	}


}
