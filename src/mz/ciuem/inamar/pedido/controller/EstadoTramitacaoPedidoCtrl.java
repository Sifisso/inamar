package mz.ciuem.inamar.pedido.controller;

import java.util.ArrayList;
import java.util.List;

import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class EstadoTramitacaoPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regEstadoTramitacaoPedido;
	private Combobox cbx_estadoTramitacao;
	private Listbox lbx_estadoTramitacao;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	
	//Inferior
	private Listbox lbx_estadoTramitacaoPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	//private Estado _selectedLocalPratica;
	
	
	
	@WireVariable
	private LocalPraticaService _localPraticaService;
	@WireVariable
	private LocalPraticaPedidoService _localPraticaPedidoService;
	private List<LocalPratica> listLocalPratica, listLocalPraticaAdd = new ArrayList<LocalPratica>();
	private ListModelList<LocalPratica> listModelLocalPraticaAdd, listModelLocalPratica;
	private List<LocalPraticaPedido> _listLocalPPedido = new ArrayList<LocalPraticaPedido>();

}
