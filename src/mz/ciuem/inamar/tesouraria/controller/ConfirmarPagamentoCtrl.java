package mz.ciuem.inamar.tesouraria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.Pagamento;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.PagamentoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.TaxaPedidoService;

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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ConfirmarPagamentoCtrl extends GenericForwardComposer{
	
	private Window win_confirmarRecepcao;
	private Div div_content_out;
	private Include inc_main;
	
	private Doublebox dxb_valor ,dxb_valorImpressao;
	private Datebox dbx_data;
	private Combobox cbx_forma, cbx_conta;
	private Textbox txb_observacoes, txb_valorImpressao;
	
	private Button btn_gravar, btn_actualizar, btn_cancelar;
	
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private PagamentoService _pagamentoService;
	@WireVariable
	
	private Peticao _peticao;
	PeticaoEmbarcacao _peticaoEmbarcacao;
	private Pedido pedido;
	private Pagamento _pagamento;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_pagamentoService = (PagamentoService) SpringUtil.getBean("pagamentoService");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
		
		_pagamento = _peticao.getPagamento();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherCampos(pedido, _peticaoEmbarcacao);
		//listarConta();
	}
	
	public void onClick$btn_gravar(){
		if(_peticao!=null){
			saveOrUpdate(_peticao);
			Clients.showNotification("Pagamento Confirmado", "info", txb_observacoes, "before_center", 4000, true);
			win_confirmarRecepcao.detach();
		}
	}

	private void saveOrUpdate(Peticao pet) {
		if(_pagamento!=null){
			saveOrUpdateP(_pagamento, pet);
		}else{
			_pagamento = new Pagamento();
			saveOrUpdateP(_pagamento, pet);
		}
		
	}

	//pegar as contas
	/*private void listarConta() {
       cbx_conta.setModel(new ListModelList<Conta>(_contaService.getAll()));	
	}*/

	private void saveOrUpdateP(Pagamento pag, Peticao _petic) {

		pag.setDataRecepcaoValor(dbx_data.getValue());
		//_petic.setValorImpressao(dxb_valorImpressao.getValue());
		pag.setFormaRecepcao(cbx_forma.getValue());
		pag.setConta(cbx_conta.getValue());
		pag.setObservacoes(txb_observacoes.getValue());
		_petic.setLocalizacao("Administrador Marítimo 2");
		
		_pagamentoService.saveOrUpdate(pag);
		_petic.setPago(true);
		_petic.setAdmMaritima2(true);
		_petic.setTesouraria(false);
		_petic.setLocalizacao("Administrador Marítimo 2");
		_petic.setPagamento(pag);
		pag.setPeticao(_petic);
		_peticaoService.saveOrUpdate(_peticao);
		_pagamentoService.saveOrUpdate(pag);
	}

	private void preencherCampos(Pedido pedido, PeticaoEmbarcacao _peticaoEmbarcacao) {
		
		if(_peticao!=null){
			dxb_valor.setValue(_peticao.getValorTotal());
			//dxb_valorImpressao.setValue(_peticao.getValorImpressao());
			if(_pagamento!=null){
				//dxb_valor.setValue(_peticao.getPedido().getTotal());
				//dxb_valorImpressao.setValue(_peticao.getValorImpressao());
				dbx_data.setValue(_pagamento.getDataRecepcaoValor());
				cbx_forma.setValue(_pagamento.getFormaRecepcao());
				cbx_conta.setValue(_pagamento.getConta());
				txb_observacoes.setValue(_pagamento.getObservacoes());
			}
		}
	}
	
	

	public void onClickClose(ForwardEvent e){
		win_confirmarRecepcao.detach();
	}


}
