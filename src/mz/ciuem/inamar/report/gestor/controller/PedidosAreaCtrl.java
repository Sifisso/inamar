package mz.ciuem.inamar.report.gestor.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.TipoPedidoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class PedidosAreaCtrl extends GenericForwardComposer{
	
	@WireVariable
	private AreaService _areaService;
	@WireVariable
	private TipoPedidoService _tipoPedidoService;
	@WireVariable
	private PedidoService _pedidoService;
	
	private Listbox lbx_areas, lbx_TipoPedido, lbx_Pedido;
	private Window win_pedidos_area;
	
    private List<Area> listArea = new ArrayList<Area>();
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_areaService = (AreaService) SpringUtil.getBean("areaService");
   		_tipoPedidoService = (TipoPedidoService) SpringUtil.getBean("tipoPedidoService");
   		_pedidoService = (PedidoService) SpringUtil.getBean("pedidoService");

	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		listar();
	}
	
 	private void listar() {
   		listArea = _areaService.getAll();
   		lbx_areas.setModel(new ListModelList<Area>(listArea));
   	}
 	
 	public void onClickDetalhesArea(ForwardEvent e){
 		lbx_TipoPedido.getItems().clear();
 		lbx_Pedido.getItems().clear();
 		Area a = (Area) e.getData();
 		
 		lbx_TipoPedido.setModel(new ListModelList<TipoPedido>(_tipoPedidoService.findByArea(a)));
 	}
 	
 	
 	public void onClickDetalhesTipoPedido(ForwardEvent e){
 		lbx_Pedido.getItems().clear();
 		TipoPedido tp = (TipoPedido) e.getData();
 		
 		lbx_Pedido.setModel(new ListModelList<Pedido>(_pedidoService.findByTipoPedido(tp)));
 	}
 	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        String realPath = ex.getDesktop().getWebApp().getRealPath("/reporter/");
        String realPath1 = ex.getDesktop().getWebApp().getRealPath("/reporter/");
        mapaParam.put("SUBREPORT_DIR", realPath);
        mapaParam.put("SUBREPORT_DIR1", realPath1);
        mapaParam.put("nome_lista", "CONFIGURAÇÃO DE PEDIDOS POR ÁREA");
   		MasterRep.imprimir("/reporter/configuracao_pedidos.jrxml", listArea, mapaParam, win_pedidos_area);
   	}

}
