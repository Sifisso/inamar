package mz.ciuem.inamar.chefesecretaria.controller;

import mz.ciuem.inamar.entity.Peticao;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class RecusarPedidoCtrl extends GenericForwardComposer{
	
	private Window win_recusarPedido;
	private Div div_content_out;
	private Include inc_main;

	private Textbox txb_observacoes, txb_recusar;
	
	private Button btn_gravar, btn_actualizar, btn_cancelar;
	
	@WireVariable
	private PeticaoService _peticaoService;
	
	private Peticao _peticao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_peticaoService =(PeticaoService) SpringUtil.getBean("peticaoService");
		_peticao = (Peticao) Executions.getCurrent().getArg().get("peticao");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
	}
	
	public void onClick$btn_gravar(final ForwardEvent e){
		if(_peticao!=null){
			saveOrUpdate(_peticao);
			Clients.showNotification("Pedido recusado", "error", txb_observacoes, "before_center", 4000, true);
			win_recusarPedido.detach();
		}
		
		win_recusarPedido.detach();
		onClickClose(e);
	}

	private void saveOrUpdate(Peticao pet) {
			saveOrUpdateP( pet);
	}

	//pegar as contas
	/*private void listarConta() {
       cbx_conta.setModel(new ListModelList<Conta>(_contaService.getAll()));	
	}*/

	private void saveOrUpdateP(Peticao _petic) {
		_petic.setMotivoRecusa(txb_recusar.getValue());
		_petic.setSecretaria2(true);
		_petic.setRecusado(true);
		_petic.setLocalizacao("Secretaria2");
		_peticaoService.saveOrUpdate(_petic);
	}

	private void preencherCampos() {
		if(_peticao!=null){
			txb_recusar.setValue(_peticao.getMotivoRecusa());
		}
	}
	
	

	public void onClickClose(ForwardEvent e){
		win_recusarPedido.detach();
		Messagebox.show("motivo"+_peticao.getMotivoRecusa());
	}


}
