package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.DelegacaoService;
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
public class DelegacaoCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Label lbl_descricao, lbl_descricao2;
	private Textbox txb_nomefind;
	private Combobox cbx_provinciaFind;
	private Button btn_procurar;
	private Listbox lbx_delegacao, lbx_conta;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Intbox ibx_codigo;
	private Intbox ibx_entidade;
	private Radio rbx_entidadeSim;
	private Radio rbx_entidadeNao;
	private Radio rbx_actSimA;
	private Radio rbx_actNaoA;
	private Combobox cbx_provincia;
	private Textbox txb_nuit;
	private Textbox txb_bairro;
	private Textbox txb_avenida_rua;
	private Textbox txb_quarteirao_andar;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	private Button btn_addConta;
	
	private Window win_regDelegacao;
	
	Execution ex = Executions.getCurrent();
	
	private Delegacao _delegacao;
	
	private Instituicao _instituicao;
	
	
	@WireVariable
	private InstituicaoService _instituicaoService;
	
	@WireVariable
	private ProvinciaService _provinciaService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	@WireVariable
	private ContaService _contaService;
	
	private List<Instituicao> listIns = new ArrayList<Instituicao>();
	
	private List<Provincia> listProvin = new ArrayList<Provincia>();
	
	private List<Delegacao> lisDeleg =  new ArrayList<Delegacao>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_instituicaoService = (InstituicaoService) SpringUtil.getBean("instituicaoService");
		_provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
		_contaService = (ContaService) SpringUtil.getBean("contaService");
		
		_instituicao =  (Instituicao) ex.getArg().get("_instituicao");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listar();
		peencherProvincias();
		preencherCabecalho();
	}
	
   	public void onClick$btn_addConta(){
   		final Listitem item = new Listitem();
   		
   		Listcell cellnome = new Listcell();
   		cellnome.setParent(item);
   		
   		Textbox tbx_nome = new Textbox();
   		tbx_nome.setParent(cellnome);
   		tbx_nome.setWidth("99%");
   		tbx_nome.setConstraint("no empty");
   		
   		Listcell cellnr = new Listcell();
   		cellnr.setParent(item);
   		
   		Textbox tbx_nr = new Textbox();
   		tbx_nr.setParent(cellnr);
   		tbx_nr.setWidth("99%");
   		tbx_nr.setConstraint("no empty");
   		
   		Listcell cellnib = new Listcell();
   		cellnib.setParent(item);
   		
   		Textbox tbx_nib = new Textbox();
   		tbx_nib.setParent(cellnib);
   		tbx_nib.setWidth("99%");
   		tbx_nib.setConstraint("no empty");
   		
   		Listcell celapagar = new Listcell();
		celapagar.setParent(item);
		Button btn_apagar = new Button();
		btn_apagar.setZclass("btn btn-danger btn-sm");
		btn_apagar.setIconSclass("fa  fa-times-circle");
		btn_apagar.setParent(celapagar);
		
		btn_apagar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				lbx_conta.removeChild(item);
			}
		});
   		
   		lbx_conta.appendChild(item);
   	}
   	
   	private void saveContas(Delegacao delegacao2) {
   		List<Listitem> listItems = lbx_conta.getItems();
   		List<Conta> lisContas = new ArrayList<Conta>();
		if(!listItems.isEmpty()){
			for (Listitem listitem : listItems) {
				Listcell cellnome = (Listcell) listitem.getFirstChild();
				Textbox tbx_nome = (Textbox) cellnome.getFirstChild();
				
				Listcell cellnr = (Listcell) listitem.getChildren().get(1);
				Textbox tbx_nr = (Textbox) cellnr.getFirstChild();
				
				Listcell cellnib = (Listcell) listitem.getChildren().get(2);
				Textbox tbx_nib = (Textbox) cellnib.getFirstChild();
				
				Conta _conta = new Conta();
				_conta.setNomeBanco(tbx_nome.getValue());
				_conta.setNrConta(tbx_nr.getValue());
				_conta.setNib(tbx_nib.getValue());
				
				_conta.setDelegacao(delegacao2);
				
				_contaService.saveOrUpdate(_conta);
				
				lisContas.add(_conta);
				
			}
			
			if(btn_actualizar.isVisible()){
				List<Conta> l = delegacao2.getContas();
				if(!l.isEmpty()){
					for (Conta conta : l) {
						_contaService.delete(conta);
					}
				}
			}
			
			delegacao2.setContas(lisContas);
			_delegacaoService.update(delegacao2);
		}
		
	}


    public void onClickprcurar(ForwardEvent e)  {
             String nome = txb_nomefind.getValue();
             Provincia provincia = cbx_provinciaFind.getSelectedItem().getValue();
             findByNomeProvincia(nome,provincia);
		}
	
	public void onClickConfig(ForwardEvent e)  {
		Delegacao delegacao = (Delegacao) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_delegacao", delegacao);
		win_regDelegacao.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_delegacaoDepartamento.zul", win_regDelegacao,map);
		
	}
	
	public void onCheckRadioE(ForwardEvent e)  {
	   if(rbx_entidadeSim.isChecked()){
		   preencherCampos(false);
	   }else{
		   preencherCampos(true);
	   }
	}
	
	
	public void onClick$btn_actualizar() throws InterruptedException {
		
        _delegacao.setAdmar(rbx_actSimA.isChecked() ? true : false);
		
        _delegacao.setEntidadePropria(rbx_entidadeSim.isChecked() ? true : false);
	    
        _delegacao.setNome(txb_nome.getValue());
        _delegacao.setCodigo(ibx_codigo.getValue());
        _delegacao.setEntidade(""+ibx_entidade.getValue());
		
        _delegacao.setInstituicao(_instituicao);
        _delegacao.setProvincia((Provincia) cbx_provincia.getSelectedItem().getValue());
		_delegacao.setNuit(txb_nuit.getValue());
        saveContas(_delegacao);
   		_delegacao.setBairro(txb_bairro.getValue());
   		_delegacao.setAv_rua(txb_avenida_rua.getValue());
   		_delegacao.setQuarteirao_andar(txb_quarteirao_andar.getValue());
		
		
		_delegacaoService.update(_delegacao);
		listar();
		showNotifications("Delegacao actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Delegacao del = new Delegacao();
	    
        del.setAdmar(rbx_actSimA.isChecked() ? true : false);
		
		del.setEntidadePropria(rbx_entidadeSim.isChecked() ? true : false);
	    
		del.setNome(txb_nome.getValue());
		del.setCodigo(Integer.parseInt(""+ibx_codigo.getValue()));
		del.setEntidade(""+ibx_entidade.getValue());
		
	    del.setInstituicao(_instituicao);
		del.setProvincia((Provincia) cbx_provincia.getSelectedItem().getValue());
 		del.setNuit(txb_nuit.getValue());
   		del.setBairro(txb_bairro.getValue());
   		del.setAv_rua(txb_avenida_rua.getValue());
   		del.setQuarteirao_andar(txb_quarteirao_andar.getValue());
		
		_delegacaoService.saveOrUpdate(del);
		saveContas(del);
		listar();
		showNotifications("Delegacao registada com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_delegacao(Event e){
		lbx_conta.getItems().clear();
		_delegacao = lbx_delegacao.getSelectedItem().getValue();
		txb_nome.setValue(_delegacao.getNome());
		ibx_codigo.setValue(_delegacao.getCodigo());
		ibx_entidade.setValue(Integer.parseInt(_delegacao.getEntidade()));
	    rbx_actNaoA.setChecked(!_delegacao.isAdmar());
	    rbx_actSimA.setChecked(_delegacao.isAdmar());
	    rbx_entidadeNao.setChecked(!_delegacao.isEntidadePropria());
	    rbx_entidadeSim.setChecked(_delegacao.isEntidadePropria());
	    cbx_provincia.setValue(_delegacao.getProvincia().getDesignacao());
   		txb_nuit.setValue(_delegacao.getNuit());
   		txb_bairro.setValue(_delegacao.getBairro());
   		txb_avenida_rua.setValue(_delegacao.getAv_rua());
   		txb_quarteirao_andar.setValue(_delegacao.getQuarteirao_andar());
   		cbx_provincia.setValue(_delegacao.getProvincia().getDesignacao());
	    
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
		
		preencherContas(_delegacao,_instituicao, true);
	}
	
	private void preencherContas(Delegacao _delegacao2, Instituicao instituicao2, boolean del) {
		lbx_conta.getItems().clear();
		List<Conta> listContas = new ArrayList<Conta>();
		
		if(del){
				listContas = _delegacao2.getContas();
		}else{
			listContas = instituicao2.getContas();
		}
  		
   		
   		for (Conta conta : listContas) {
   			final Listitem item = new Listitem();
   	   		
   	   		Listcell cellnome = new Listcell();
   	   		cellnome.setParent(item);
   	   		
   	   		Textbox tbx_nome = new Textbox();
   	   		tbx_nome.setParent(cellnome);
   	   		tbx_nome.setWidth("99%");
   	   		tbx_nome.setConstraint("no empty");
   	   		
   	   		Listcell cellnr = new Listcell();
   	   		cellnr.setParent(item);
   	   		
   	   		Textbox tbx_nr = new Textbox();
   	   		tbx_nr.setParent(cellnr);
   	   		tbx_nr.setWidth("99%");
   	   		tbx_nr.setConstraint("no empty");
   	   		
   	   		Listcell cellnib = new Listcell();
   	   		cellnib.setParent(item);
   	   		
   	   		Textbox tbx_nib = new Textbox();
   	   		tbx_nib.setParent(cellnib);
   	   		tbx_nib.setWidth("99%");
   	   		tbx_nib.setConstraint("no empty");
   	   		
			tbx_nome.setValue(conta.getNomeBanco());

			tbx_nr.setValue(conta.getNrConta());
			
			tbx_nib.setValue(conta.getNib());
			
	   		Listcell celapagar = new Listcell();
			celapagar.setParent(item);
			Button btn_apagar = new Button();
			btn_apagar.setZclass("btn btn-danger btn-sm");
			btn_apagar.setIconSclass("fa  fa-times-circle");
			btn_apagar.setParent(celapagar);
			
			btn_apagar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					lbx_conta.removeChild(item);
				}
			});
			
			lbx_conta.appendChild(item);
		}
		
	}

	public void onClick$btn_imprimir(Event e) throws JRException{
	
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", _instituicao.getNome());
		MasterRep.imprimir("/reportParam/reportDelegacao.jrxml", lisDeleg, mapaParam, win_regDelegacao);
	}
	
	public void findByNomeProvincia(String nome, Provincia provincia){
		lisDeleg = _delegacaoService.findByNomeProvincia(nome, provincia, _instituicao);
		lbx_delegacao.setModel(new ListModelList<Delegacao>(lisDeleg));
	}
	
	
	private void limparCampos() {
		txb_nome.setRawValue(null);
		ibx_codigo.setRawValue(null);
		ibx_entidade.setRawValue(null);
		rbx_actSimA.setChecked(false);
	    rbx_actNaoA.setChecked(true);
	    rbx_entidadeSim.setChecked(false);
	    rbx_entidadeNao.setChecked(true);
		lbx_delegacao.clearSelection();
   		txb_nuit.setRawValue(null);
   		lbx_conta.getItems().clear();
   		txb_bairro.setRawValue(null);
   		txb_avenida_rua.setRawValue(null);
   		txb_quarteirao_andar.setRawValue(null);
   		cbx_provincia.setRawValue(null);
        habilitarCampos();
   		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		
	}
	
	private void listar() {
		lisDeleg = _delegacaoService.findByInstituicao(_instituicao);
		lbx_delegacao.setModel(new ListModelList<Delegacao>(lisDeleg));
	}
	
	private void peencherProvincias() {
		listProvin = _provinciaService.getAll();
		cbx_provincia.setModel(new ListModelList<Provincia>(listProvin));
		cbx_provinciaFind.setModel(new ListModelList<Provincia>(listProvin));
	}
	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_instituicao.getNome());
		lbl_descricao2.setValue(_instituicao.getNome());
		
	}
	
	public void preencherCampos(boolean preencher){
		if(preencher){
				ibx_entidade.setValue(Integer.parseInt(_instituicao.getEntidade()));
		   		txb_nuit.setValue(_instituicao.getNuit());
		   	    preencherContas(_delegacao, _instituicao, false);
		   		txb_bairro.setValue(_instituicao.getBairro());
		   		txb_avenida_rua.setValue(_instituicao.getAv_rua());
		   		txb_quarteirao_andar.setValue(_instituicao.getQuarteirao_andar());
		   		cbx_provincia.setValue(_instituicao.getProvincia());
		   		desabilitarCampos();
		
		}else{
			lbx_delegacao.clearSelection();
			ibx_entidade.setRawValue(null);
	   		txb_nuit.setRawValue(null);
	   	    lbx_conta.getItems().clear();
	   		txb_bairro.setRawValue(null);
	   		txb_avenida_rua.setRawValue(null);
	   		txb_quarteirao_andar.setRawValue(null);
	   		cbx_provincia.setRawValue(null);
            habilitarCampos();
		}
	}

	private void desabilitarCampos() {
		ibx_entidade.setDisabled(true);
   		txb_nuit.setDisabled(true);
   		//contas
   		txb_bairro.setDisabled(true);
   		txb_avenida_rua.setDisabled(true);
   		txb_quarteirao_andar.setDisabled(true);
   		cbx_provincia.setDisabled(true);
	}
	
	private void habilitarCampos() {
		ibx_entidade.setDisabled(false);
   		txb_nuit.setDisabled(false);
   		//contas
   		txb_bairro.setDisabled(false);
   		txb_avenida_rua.setDisabled(false);
   		txb_quarteirao_andar.setDisabled(false);
   		cbx_provincia.setDisabled(false);
	}

	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_delegacao,"before_center", 4000, true);
	}

}
