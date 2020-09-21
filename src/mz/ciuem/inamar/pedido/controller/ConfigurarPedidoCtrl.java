package mz.ciuem.inamar.pedido.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Li;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.dao.imlp.GenericDaoImpl;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.PedidoService;

public class ConfigurarPedidoCtrl  extends GenericForwardComposer{
	

	// ----------------------Especifica------------------------------------------
	private Li li_locaisPratica;
	private Li li_rotas;
	private Li li_meioTrasnporte;
	private Li li_tipoEmbarcacao;
	private Li li_equipamento;
	private Li li_curso;
	private Li li_estadoTramitacao;
	private Li li_multas;
	private Li id_estadoLicenca;
	private Li li_contagem;
	private Li li_tipoVistoria;
	
    // --- Configuracao Geral   
	private Li li_requisitos;
	private Li li_insLegais;
	private Li li_taxas;
	private Li li_tarefasEtapa;
	
	
	private Pedido _pedido;
	private boolean _geral=false;
	
	private Div div_configurar;
	
	@SuppressWarnings("unused")
	private Label lbl_descricao;

	Window win_configPedido;

	Execution ex = Executions.getCurrent();
	
	private List<Pedido> listPedido;
	private ListModelList<Pedido> listModelPedido;
	
	@WireVariable
	private PedidoService _pedidoService;

	@SuppressWarnings("unchecked")
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_pedidoService = (PedidoService) SpringUtil.getBean("pedidoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
		_geral = (boolean) ex.getArg().get("_geral");
	
		
	}

	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		preencherCabecalho();
		if(_geral){
			onClickRequisitos(null);
		}else{
		      onClickLocaisPratica(null);
		}
	}
	
	
	public void onClickLocaisPratica(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/local_pratica.zul", div_configurar, mapEst);
	
	}
	
	public void onClickRotas(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/rotas_pedido.zul", div_configurar, mapEst);
	
	}
	
	public void onClickMeioTransporte(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/meioTransporte_pedido.zul", div_configurar, mapEst);
	
	}
	
	public void onClickTipoEmbarcacao(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/tipoEmbarcacao_pedido.zul", div_configurar, mapEst);
	
	}
	
	public void onClickEquipamento(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/equipamento_pedido.zul", div_configurar, mapEst);
	
	}
	
	public void onClickCurso(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/curso_pedido.zul", div_configurar, mapEst);
	
	}
	
	public void onClickEstadoTramitacao(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/estadoTramitacao_pedido.zul", div_configurar, mapEst);
	
	}
	
	public void onClickContagem(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/contagem_pedido.zul", div_configurar, mapEst);
	}
	
	
	
	//----------------Gerais-------------------
	public void onClickRequisitos(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/requisitos_pedido.zul", div_configurar, mapEst);
	}
	
	
	public void onClickInsLegais(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/insLegais_pedido.zul", div_configurar, mapEst);
	}
	
	public void onClickTaxas(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/taxas_pedido.zul", div_configurar, mapEst);
	}
	
	public void onClickTarefaEtapa(ForwardEvent e) {

		Map<String, Object> mapEst = new HashMap<String, Object>();
		mapEst.put("_pedido", _pedido);
		div_configurar.getChildren().clear();
		Executions.createComponents("/views/Pedido/tarefaEtapa_pedido.zul", div_configurar, mapEst);
	}
	
	
	public void preencherCabecalho() {
		lbl_descricao.setValue(_pedido.getDescricao());
	}
	
	
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbl_descricao, "before_center",
				4000, true);
	}


}
