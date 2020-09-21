package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.CategoriaService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TipoPedidoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TipoPedidoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomeAreafind;
	private Textbox txb_nomeCatfind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_TipoPedido;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_descricao, txb_codigo;
	private Combobox cbx_categoria;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Label lbl_descricao, lbl_descricao2;
	
	
	private Window win_regTipoPedido;
	
	Execution ex = Executions.getCurrent();
	
	private Area _area;
	
	private TipoPedido _tipoPedido;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	@WireVariable
	private CategoriaService _categoriaService;
	
	@WireVariable
	private TipoPedidoService _tipoPedidoService;
	
    private List<TipoPedido> listTipoPedido = new ArrayList<TipoPedido>();
    private List<Categoria> listCategoria = new ArrayList<Categoria>();
    
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_categoriaService = (CategoriaService) SpringUtil.getBean("categoriaService");
   		_tipoPedidoService = (TipoPedidoService) SpringUtil.getBean("tipoPedidoService");
   		_area = (Area) ex.getArg().get("_area");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   		preencherCabecalho();
   	}
   	
   public void onClickConfig(ForwardEvent e)  {
		TipoPedido _tp = (TipoPedido) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_tipoPedido", _tp);
		win_regTipoPedido.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_pedido.zul", win_regTipoPedido, map);
		
	}


     public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomeCatfind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsAdmar(nome, _area, isActivo);
   	 }
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   		_tipoPedido.setActivo(rbx_actSim.isChecked() ? true : false);
   		_tipoPedido.setArea(_area);
   		_tipoPedido.setCategoria((Categoria)cbx_categoria.getSelectedItem().getValue());
   		_tipoPedido.setNome(txb_descricao.getValue());
   		_tipoPedido.setCodigo(txb_codigo.getValue());

   		
   		_tipoPedidoService.update(_tipoPedido);
   		showNotifications("Tipo de Pedido actualizado com sucesso!", "info");
   		limparCampos();
   		
   		showNotifications("O código introduzido já existe", "error");
   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		TipoPedido _tp = new TipoPedido();
   	    
   		_tp.setActivo(rbx_actSim.isChecked() ? true : false);
   		_tp.setArea(_area);
   		_tp.setCategoria((Categoria)cbx_categoria.getSelectedItem().getValue());
   		_tp.setNome(txb_descricao.getValue());
   		_tp.setCodigo(txb_codigo.getValue());
   		
   		_tipoPedidoService.create(_tp);
   		showNotifications("Tipo de Pedido registado com sucesso!", "info");
   		limparCampos();
   		
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_TipoPedido(Event e){
   		_tipoPedido = lbx_TipoPedido.getSelectedItem().getValue();
   		txb_descricao.setValue(_tipoPedido.getNome());
   		txb_codigo.setValue(_tipoPedido.getCodigo());
   		cbx_categoria.setValue(_tipoPedido.getCategoria().getNome());
   	    rbx_actNao.setChecked(!_tipoPedido.isActivo());
   	    rbx_actSim.setChecked(_tipoPedido.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _area.getNome());
   		MasterRep.imprimir("/reportParam/reportTipoPedido.jrxml", listTipoPedido, mapaParam, win_regTipoPedido);
   	}
   	
  	public void findByNomeIsAdmar(String nome,Area area,  boolean isActivo){
   		listTipoPedido= _tipoPedidoService.findByNomeCategoriaAreaIsActivo(nome, area, isActivo);
   		lbx_TipoPedido.setModel(new ListModelList<TipoPedido>(listTipoPedido));
   	}
   	
   	private void limparCampos() {
   		cbx_categoria.setRawValue(null);
   		txb_descricao.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_area.getNome());
		lbl_descricao2.setValue(_area.getNome());
		txb_nomeAreafind.setValue(_area.getNome());
	}
   	
   	private void listar() {
   		listTipoPedido = _tipoPedidoService.findByArea(_area);
   		lbx_TipoPedido.setModel(new ListModelList<TipoPedido>(listTipoPedido));
   		listarCategoria();
   	}
   	
   	public void listarCategoria(){
   		//listCategoria = _categoriaService.findByNotInPedido(_area);
   		listCategoria = _categoriaService.getAll();
   		cbx_categoria.setModel(new ListModelList<Categoria>(listCategoria));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_TipoPedido,"before_center", 4000, true);
   	}


}
