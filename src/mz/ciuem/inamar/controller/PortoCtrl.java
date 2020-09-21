package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.ActividadeZonaMaritima;
import mz.ciuem.inamar.entity.Categoria;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.entity.EmbarcacaoAcontecimento;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Porto;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.AcontecimentoService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.PortoService;
import mz.ciuem.inamar.service.ProvinciaService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


@SuppressWarnings({ "serial", "rawtypes" })
public class PortoCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_porto;
	private Button btn_imprimir;
	
	//Modal Div
		private Textbox txb_porto;
		private Radio rbx_actSimA;
		private Radio rbx_actNaoA;
		
		private Button btn_gravar;
		private Button btn_actualizar;
		private Button btn_cancelar;
		
		private Window win_regPorto;
		
		Execution ex = Executions.getCurrent();
		
		private Porto _porto;
		
		@WireVariable
		private PortoService  _portoService;
		private List<Porto> listporto = new ArrayList<Porto>();
		
		@SuppressWarnings("unchecked")
		@Override
		public void doBeforeComposeChildren(Component comp) throws Exception {
			super.doBeforeComposeChildren(comp);
			
			_portoService = (PortoService) SpringUtil.getBean("portoService");
			
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(comp);

		  listar();
		}
		
		 public void onClickprcurarTodos(ForwardEvent e)  {
	         listar();
		 }
		
		private void listar() {
			listporto =  _portoService.getAll();
			lbx_porto.setModel(new ListModelList<Porto>(listporto));
		}
		
		public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
		
		public void onSelect$lbx_porto(Event e){
			_porto = lbx_porto.getSelectedItem().getValue();
			rbx_actSimA.setChecked(_porto.isActivo());
			txb_porto.setValue(_porto.getDescricao());
		    
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
			
		}
		
		public void onClick$btn_gravar(Event e) throws InterruptedException{

			Porto fac = new Porto();
			fac.setActivo(rbx_actSimA.isChecked()? true : false);
			fac.setDescricao(txb_porto.getValue());
			
				_portoService.create(fac);
				listar();
				limparCampos();
				showNotifications("Porto registado com sucesso!", "info");
			}

		private void limparCampos() {
			rbx_actSimA.setChecked(false);
		    rbx_actNaoA.setChecked(true);
			txb_porto.setRawValue(null);

			btn_gravar.setVisible(true);
			btn_actualizar.setVisible(false);
			
		}
		public void onClick$btn_actualizar() throws InterruptedException {
			_porto.setActivo(rbx_actSimA.isChecked() ? true : false);
			_porto.setDescricao(txb_porto.getValue());
			
				_portoService.update(_porto);
				listar();
				showNotifications("Porto actualizado com sucesso!", "info");
				limparCampos();
		}
		public void onClickprcurar(ForwardEvent e)  {
	          String descricao = txb_nomefind.getValue();
	          boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
	          findBydescricaoActivo(descricao,isActivo);
			}
		
		public void findBydescricaoActivo(String descricao, boolean isActivo){
			listporto = _portoService.findBydescricaoActivo(descricao, isActivo);
	   		lbx_porto.setModel(new ListModelList<Porto>(listporto));
	   	}
		
		private void habilitarCampos() {
			txb_porto.setDisabled(false);
		}
		
		public void showNotifications(String message, String type) {
			Clients.showNotification(message, type, lbx_porto,"before_center", 4000, true);
		}
}
