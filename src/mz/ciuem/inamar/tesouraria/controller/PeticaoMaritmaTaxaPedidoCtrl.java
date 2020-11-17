package mz.ciuem.inamar.tesouraria.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.ItensPeticao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.service.ItensPeticaoService;
import mz.ciuem.inamar.service.PagamentoService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TaxaService;

@SuppressWarnings({ "serial", "rawtypes" })
public class PeticaoMaritmaTaxaPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_itensPeticaoReg;
	
	private Label lbl_pedido, lbl_nome, lbl_custo, lbl_descricao;
	private Listbox lbx_addItens;
	
	private Listbox lbx_itens; 
	
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
	
	private PeticaoMaritimoTaxaPedido peticaoMaritimoTaxaPedido;
	
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
	private TaxaPedido selected_pmtx;
	
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
		//listarPeticao();
		preecherTaxas();
		retorno();
		
	}
	
	public void listarPeticao(){
		
		
		_peticaoMaritimoTaxaPedidos = _peticaoMaritimoTaxaPedidoService.findByPeticao(_peticao);
		lbx_itens.setModel(new ListModelList<PeticaoMaritimoTaxaPedido>(_peticaoMaritimoTaxaPedidos));
		
		double total=0;
		for(PeticaoMaritimoTaxaPedido valor:_peticaoMaritimoTaxaPedidos){
			total = total+valor.getTaxaPedido().getTaxa().getValor()+valor.getTaxaPedido().getTaxa().getEmolumento();
		}
		lbl_custo.setValue(""+total+"0"+"MT");
		//Messagebox.show("VALOR="+peticaoMaritimoTaxaPedido.getValor());

		}
	
	public double retorno(){
		
		_peticaoMaritimoTaxaPedidos = _peticaoMaritimoTaxaPedidoService.findByPeticao(_peticao);
		lbx_itens.setModel(new ListModelList<PeticaoMaritimoTaxaPedido>(_peticaoMaritimoTaxaPedidos));
		
		double total=0;
		for(PeticaoMaritimoTaxaPedido valor:_peticaoMaritimoTaxaPedidos){
			total = total+valor.getTaxaPedido().getTaxa().getValor()+valor.getTaxaPedido().getTaxa().getEmolumento();
		}
		lbl_custo.setValue(""+total+"0"+"MT");

		return total;
	}
	
	public void preecherTaxas(){
		_listTaxaPedido = _taxaPedidoService.findByPedidoTaxaPedido(_pedido);
		cbx_taxaPedido.setModel(new ListModelList<TaxaPedido>(_listTaxaPedido));
	}
	
	public void onSelect$cbx_taxaPedido() {
		selected_pmtx = (TaxaPedido) cbx_taxaPedido.getSelectedItem().getValue();
    }
	
	
	private void preencherCampos() {
		if(_peticao!=null){
			lbl_nome.setValue(_peticao.getUtente());
			lbl_pedido.setValue(_peticao.getDescricao());
			_pedido=_peticao.getPedido();
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
		
		boolean existe = false;
		
		for(PeticaoMaritimoTaxaPedido peticaoMTP: _peticaoMaritimoTaxaPedidos) {
			
			if(peticaoMTP.getTaxaPedido().getId()==(pmtp.getTaxaPedido().getId())) {
				existe=true;
			}
			
		}
		
		if(existe == false) {
			_peticaoMaritimoTaxaPedidoService.create(pmtp);
			_listTaxaPedido.remove((TaxaPedido)cbx_taxaPedido.getSelectedItem().getValue());
			cbx_taxaPedido.removeChild(cbx_taxaPedido.getSelectedItem());
			
			selected_pmtx = null;
			cbx_taxaPedido.setRawValue(null);
			
			limparCampos();
			listarPeticao();
			showNotifications("Taxa Adicionada com Sucesso", "info");
		}else {
			showNotifications("Não pode adicionar a mesma taxa mais de uma vez", "error");
		}
		
		
		
		
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
