package mz.ciuem.inamar.seccaoTecnica.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.Pagamento;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.service.ContaService;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TratarPeticaoCtrl extends GenericForwardComposer{
	
	private Window win_confirmarRecepcao;
	private Div div_content_out;
	private Include inc_main;

	private Textbox txb_observacoes, txb_parecer;
	
	private Button btn_gravar, btn_actualizar, btn_cancelar;
	
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private PagamentoService _pagamentoService;
	
	private Peticao _peticao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_pagamentoService = (PagamentoService) SpringUtil.getBean("pagamentoService");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
		//listarConta();
	}
	
	public void onClick$btn_gravar(){
		if(_peticao!=null){
			saveOrUpdate(_peticao);
			Clients.showNotification("Pedido Tratado com Sucesso", "info", txb_observacoes, "before_center", 4000, true);
			win_confirmarRecepcao.detach();
		}
	}

	private void saveOrUpdate(Peticao pet) {
			saveOrUpdateP( pet);
	}

	//pegar as contas
	/*private void listarConta() {
       cbx_conta.setModel(new ListModelList<Conta>(_contaService.getAll()));	
	}
	
			_petic.setTratada(true);
		_petic.setChefeSecretaria(false);
		_petic.setAdmMaritima(true);
		_petic.setPedeParecer(false);
		_petic.setChefeSecretariaParecer(false);*/

	private void saveOrUpdateP(Peticao _petic) {
		_petic.setParecer(txb_parecer.getValue());
//		_petic.setObservacao(txb_observacoes.getValue());
		_petic.setLocalizacao("Administrador Maritimo");
		_petic.setAutorizada(true);
		_petic.setTemRespostaSTecnica(true);
		_petic.setSeccaoTecnica(false);
		_petic.setAdmMaritima(true);
		 _petic.setPedeParecer(false);
		_petic.setSeccaoTecnicaParecer(false);
       
		_peticaoService.saveOrUpdate(_petic);
	}

	private void preencherCampos() {
		if(_peticao!=null){
//			txb_observacoes.setValue(_peticao.getObservacao());
			txb_parecer.setValue(_peticao.getParecer());
		}
	}
	
	

	public void onClickClose(ForwardEvent e){
		win_confirmarRecepcao.detach();
	}


}
