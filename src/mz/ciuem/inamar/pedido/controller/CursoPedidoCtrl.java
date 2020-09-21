package mz.ciuem.inamar.pedido.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Curso;
import mz.ciuem.inamar.entity.CursoPedido;
import mz.ciuem.inamar.entity.LocalPratica;
import mz.ciuem.inamar.entity.LocalPraticaPedido;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.service.CursoPedidoService;
import mz.ciuem.inamar.service.CursoService;
import mz.ciuem.inamar.service.LocalPraticaPedidoService;
import mz.ciuem.inamar.service.LocalPraticaService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class CursoPedidoCtrl extends GenericForwardComposer{
	
	//Superior
	private Window win_regCursoPedido;
	private Combobox cbx_curso;
	private Listbox lbx_curso;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_adicionar;
	private Button btn_imprimir;
	
	//Inferior
	private Listbox lbx_cursoPedido;
	
	private Execution ex = Executions.getCurrent();

	private Pedido _pedido;
	private Curso _selectedCurso;
	
	
	
	@WireVariable
	private CursoService _cursoService;
	@WireVariable
	private CursoPedidoService _cursoPedidoService;
	private List<Curso> listCurso, listCursoAdd = new ArrayList<Curso>();
	private ListModelList<Curso> listModelCursoAdd, listModelCurso;
	private List<CursoPedido> _listCursoPedido = new ArrayList<CursoPedido>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_cursoService =  (CursoService) SpringUtil.getBean("cursoService");
		_cursoPedidoService = (CursoPedidoService) SpringUtil.getBean("cursoPedidoService");
		_pedido = (Pedido) ex.getArg().get("_pedido");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		preencherCurso(_pedido);
		listaCursoPedido(_pedido);
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", _pedido.getDescricao());
   		MasterRep.imprimir("/reportParam/reportLocalCursoPedido.jrxml", _listCursoPedido, mapaParam, win_regCursoPedido);
   	}
	
	public void onSelect$cbx_curso() {
		
		_selectedCurso = cbx_curso.getSelectedItem().getValue();
		
    }

	public void onClick$btn_adicionar() {
	  
		listCursoAdd.add(_selectedCurso);
		listModelCursoAdd = new ListModelList<Curso>(listCursoAdd);
		
		lbx_curso.setModel(listModelCursoAdd);
		
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		btn_cancelar.setVisible(true);
		cbx_curso.removeChild(cbx_curso.getSelectedItem());
		cbx_curso.setRawValue(null);
		listCurso.remove(_selectedCurso);
		_selectedCurso = null;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onRemover(ForwardEvent e){
		Curso lpr = (Curso) e.getData();
		
		listCursoAdd.remove(lpr);
        listModelCursoAdd = new ListModelList<Curso>(listCursoAdd);
		lbx_curso.setModel(listModelCursoAdd);
		
		listCurso.add(lpr);
		listModelCurso = new ListModelList<Curso>(listCurso);
		cbx_curso.setModel(listModelCurso);
		
	}
	
	public void onClick$btn_gravar() {

		for (Listitem listItem : lbx_curso.getItems()) {

			Curso cr = (Curso) listItem.getValue();

			CursoPedido _cpp = new CursoPedido();
			_cpp.setCurso(cr);
			
			_cpp.setPedido(_pedido);

			_cursoPedidoService.create(_cpp);
		}
		
		listaCursoPedido(_pedido);
		preencherCurso(_pedido);
		listCursoAdd.clear();
		listCurso.clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		limparCampos();
		showNotifications("Cursos Adicionados com Sucesso", "info");
	}
	
	private void listaCursoPedido(Pedido _pedido) {
		_listCursoPedido = _cursoPedidoService.findByPedido(_pedido);
		lbx_cursoPedido.setModel(new ListModelList<CursoPedido>(_listCursoPedido));
	}

	private void preencherCurso(Pedido _pedido) {
		listCurso = _cursoService.findNotInPedido(_pedido);
		listModelCurso = new ListModelList<Curso>(listCurso);
		cbx_curso.setModel(listModelCurso);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_curso, "before_center",
				4000, true);
	}
	
	private void limparCampos() {
		lbx_curso.getItems().clear();
		btn_gravar.setVisible(false);
		btn_cancelar.setVisible(false);
		btn_actualizar.setVisible(false);
		lbx_cursoPedido.clearSelection();
		cbx_curso.setRawValue(null);
		_selectedCurso = null;
		listCurso = new ArrayList<Curso>();
		listModelCursoAdd = new ListModelList<Curso>();
   }


}
