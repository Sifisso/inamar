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
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.AcontecimentoService;
import mz.ciuem.inamar.service.InstituicaoService;
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
public class AcontecimentoCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_acontecimentos;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_acontecimento;
	private Radio rbx_actSimA;
	private Radio rbx_actNaoA;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Window win_regAcontecimento;
	
	Execution ex = Executions.getCurrent();
	
	private Acontecimento _acontecimento;
	
	@WireVariable
	private AcontecimentoService  _acontecimentoService;
	
	private List<Acontecimento> listAcontecimentos = new ArrayList<Acontecimento>();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_acontecimentoService = (AcontecimentoService) SpringUtil.getBean("acontecimentoService");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
	}
	
	private void listar() {
		listAcontecimentos =  _acontecimentoService.getAll();
		lbx_acontecimentos.setModel(new ListModelList<Acontecimento>(listAcontecimentos));
	}
	
	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Acontecimento fac = new Acontecimento();
		fac.setActivo(rbx_actSimA.isChecked()? true : false);
		fac.setDescricao(txb_acontecimento.getValue());
		
			_acontecimentoService.saveOrUpdate(fac);
			listar();
			limparCampos();
			showNotifications("Acontecimento registado com sucesso!", "info");
		}
	
	  public void onClickprcurar(ForwardEvent e)  {
          String descricao = txb_nomefind.getValue();
          boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
          findBydescricaoActivo(descricao,isActivo);
		}
	  
	  public void findBydescricaoActivo(String descricao, boolean isActivo){
	   		listAcontecimentos = _acontecimentoService.findBydescricaoActivo(descricao, isActivo);
	   		lbx_acontecimentos.setModel(new ListModelList<Acontecimento>(listAcontecimentos));
	   	}
	
	public void onClick$btn_actualizar() throws InterruptedException {
		_acontecimento.setActivo(rbx_actSimA.isChecked() ? true : false);
		_acontecimento.setDescricao(txb_acontecimento.getValue());
		
			_acontecimentoService.update(_acontecimento);
			listar();
			showNotifications("Acontecimento actualizado com sucesso!", "info");
			limparCampos();
	}
	
	public void onSelect$lbx_acontecimentos(Event e){
		_acontecimento = lbx_acontecimentos.getSelectedItem().getValue();
		rbx_actSimA.setChecked(_acontecimento.isActivo());
		txb_acontecimento.setValue(_acontecimento.getDescricao());
	    
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
		
	}
	
	private void limparCampos() {
		rbx_actSimA.setChecked(false);
	    rbx_actNaoA.setChecked(true);
		txb_acontecimento.setRawValue(null);

		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		
	}
	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
	
	private void habilitarCampos() {
		txb_acontecimento.setDisabled(false);
	}
	

	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_acontecimentos,"before_center", 4000, true);
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Tipos de Ocorrências");
   		MasterRep.imprimir("/reportParam/reportTipoAcontecimento.jrxml", listAcontecimentos, mapaParam, win_regAcontecimento);
   	}

}
