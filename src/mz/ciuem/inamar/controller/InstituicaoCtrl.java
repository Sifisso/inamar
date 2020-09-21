package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.biff.drawing.ComboBox;
import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Conta;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.report.PeticaoDelegacao;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.PaisService;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class InstituicaoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Textbox txb_nuitfind;
	private Button btn_procurar;
	private Listbox lbx_instituicao, lbx_conta;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_codigo;
	private Intbox itx_entidade;
	private Textbox txb_nuit;
	private Textbox txb_bairro;
	private Textbox txb_avenida_rua;
	private Textbox txb_quarteirao_andar, tbx_email, tbx_celular;
	
	private Combobox cbx_provincia;
	
	
	private Button btn_addConta;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regInstituicao;
	
	Execution ex = Executions.getCurrent();
	
	private Instituicao _instituicao;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	@WireVariable
	private InstituicaoService _instituicaoService;
	
	@WireVariable
	private ProvinciaService _provinciaService;
	
	@WireVariable
	private ContaService _contaService;
	
    private List<Instituicao> listInstituicao = new ArrayList<Instituicao>();
    
    
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_instituicaoService =  (InstituicaoService) SpringUtil.getBean("instituicaoService");
   		_provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
   		_contaService = (ContaService) SpringUtil.getBean("contaService");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();

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
   	
	
	public void onClickConfig(ForwardEvent e)  {
		Instituicao _instituicao = (Instituicao) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_instituicao", _instituicao);
		win_regInstituicao.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_delegacao.zul", win_regInstituicao,map);
		
	}


       /*public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                boolean isAdmar = (rbx_SimInamfin.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isActivo, isAdmar);
   		}*/
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
   		_instituicao.setNome(txb_nome.getValue());
   		_instituicao.setCodigo(txb_codigo.getValue());
   		_instituicao.setEntidade(""+itx_entidade.getValue());
   		_instituicao.setNuit(txb_nuit.getValue());
   		_instituicao.setBairro(txb_bairro.getValue());
   		_instituicao.setEmail(tbx_email.getValue());
   		_instituicao.setCelular(tbx_celular.getValue());
   		_instituicao.setAv_rua(txb_avenida_rua.getValue());
   		_instituicao.setQuarteirao_andar(txb_quarteirao_andar.getValue());
   		_instituicao.setProvincia(cbx_provincia.getValue());
   		
   		saveContas(_instituicao);
        
   		_instituicaoService.update(_instituicao);
   		showNotifications("Instituicao actualizada com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		Instituicao _inst = new Instituicao();
   	    
   		_inst.setNome(txb_nome.getValue());
   		_inst.setCodigo(txb_codigo.getValue());
   		_inst.setEntidade(""+itx_entidade.getValue());
   		_inst.setNuit(txb_nuit.getValue());
   		_inst.setBairro(txb_bairro.getValue());
   		_inst.setEmail(tbx_email.getValue());
   		_inst.setCelular(tbx_celular.getValue());
   		_inst.setAv_rua(txb_avenida_rua.getValue());
   		_inst.setQuarteirao_andar(txb_quarteirao_andar.getValue());
   		_inst.setProvincia(cbx_provincia.getValue());
   		_instituicaoService.create(_inst);
   		
   		saveContas(_inst);
   		showNotifications("Instituicao registada com sucesso!", "info");
   		limparCampos();
   	}

   	private void saveContas(Instituicao _instituicao2) {
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
				
				_conta.setInstituicao(_instituicao2);
				
				_contaService.saveOrUpdate(_conta);
				
				lisContas.add(_conta);
				
			}
			
			if(btn_actualizar.isVisible()){
				List<Conta> l = _instituicao2.getContas();
				if(!l.isEmpty()){
					for (Conta conta : l) {
						_contaService.delete(conta);
					}
				}
			}
			
			_instituicao2.setContas(lisContas);
			_instituicaoService.update(_instituicao2);
		}
		
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_instituicao(Event e){
   		lbx_conta.getItems().clear();
   		_instituicao = lbx_instituicao.getSelectedItem().getValue();
   		txb_nome.setValue(_instituicao.getNome());
   		txb_codigo.setValue(_instituicao.getCodigo());
   		itx_entidade.setValue(Integer.parseInt(_instituicao.getEntidade()));
   		txb_nuit.setValue(_instituicao.getNuit());
   		txb_bairro.setValue(_instituicao.getBairro());
   		tbx_email.setValue(_instituicao.getEmail());
   		tbx_celular.setValue(_instituicao.getCelular());
   		txb_avenida_rua.setValue(_instituicao.getAv_rua());
   		txb_quarteirao_andar.setValue(_instituicao.getQuarteirao_andar());
   		cbx_provincia.setValue(_instituicao.getProvincia());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   		
   		List<Conta> listContas = _instituicao.getContas();
   		
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
        mapaParam.put("listNome", "Institui��es");
		MasterRep.imprimir("/reportParam/reportInstituicao.jrxml", listInstituicao, mapaParam, win_regInstituicao);
	

  	}
   	
  	/*public void findByNomeIsAdmar(String nome, boolean isActivo, boolean isAdmar){
   		listArea = _areaService.findByNomeIsActivoIsAdmar(nome, isActivo, isAdmar);
   		lbx_areas.setModel(new ListModelList<Area>(listArea));
   	}*/
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		txb_codigo.setRawValue(null);
   		itx_entidade.setRawValue(null);
   		txb_nuit.setRawValue(null);
   		txb_bairro.setRawValue(null);
   		tbx_email.setRawValue(null);
   		tbx_celular.setRawValue(null);
   		txb_avenida_rua.setRawValue(null);
   		txb_quarteirao_andar.setRawValue(null);
   		cbx_provincia.setRawValue(null);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		lbx_conta.getItems().clear();
   		listar();
   		
   	}
   	
   	private void listar() {
   		listInstituicao = _instituicaoService.getAll();
   		lbx_instituicao.setModel(new ListModelList<Instituicao>(listInstituicao));
   		listarProvincias();
   	}
   	
   	public void listarProvincias(){
   		List<Provincia> listP = _provinciaService.getAll();
   		cbx_provincia.setModel(new ListModelList<Provincia>(listP));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_instituicao,"before_center", 4000, true);
   	}

}
