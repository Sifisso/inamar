package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.Taxa;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TaxaService;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class VisualizarTaxaCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_taxa;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Doublebox dbx_valor;
	private Textbox txb_descricao;
	private Doublebox txb_emolumentos;
	
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	private Label lbl_descricao, lbl_descricao2;
	
	
	private Window win_regTaxa;
	
	Execution ex = Executions.getCurrent();
	
	private Taxa _taxa;
	
	private SubArea _subArea;
	
	private Area _area;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
    @WireVariable
    private TaxaService _taxaService;

    private List<Taxa> listTaxa = new ArrayList<Taxa>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_taxaService = (TaxaService) SpringUtil.getBean("taxaService");
   		_subArea = (SubArea) ex.getArg().get("_subArea");
   		_area = (Area) ex.getArg().get("area");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   		//preencherCabecalho();
   	}
   	
/*	public void onClickConfig(ForwardEvent e)  {
		SubArea _subA = (SubArea) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_subArea", _subA);
		win_regSubArea.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_taxa.zul", win_regSubArea, map);
		
	}*/


    public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsActivo(nome, isActivo);
   	}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		saveOrUpdate(_taxa);
   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{
   		Taxa _tax = new Taxa();
   	    saveOrUpdate(_tax);
   	}
   	
   	public void saveOrUpdate(Taxa t){
   		
   		t.setActivo(rbx_actSim.isChecked() ? true : false);
 		t.setNome(txb_nome.getValue());
 		t.setEmolumento((txb_emolumentos.getValue()));
   		t.setValor(dbx_valor.getValue()+0);
   		t.setDescricao(txb_descricao.getValue());
   		t.setSubArea(_subArea);
   		

        
   		_taxaService.saveOrUpdate(t);
   		showNotifications("Taxa actualizada com sucesso!", "info");
   		limparCampos();
   	}
   	
   	
   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_taxa(Event e){
   		_taxa = lbx_taxa.getSelectedItem().getValue();
   		txb_nome.setValue(_taxa.getNome());
   		txb_emolumentos.setValue(_taxa.getEmolumento());
   		dbx_valor.setValue(_taxa.getValor());
   		txb_descricao.setValue(_taxa.getDescricao());
   	    rbx_actNao.setChecked(!_taxa.isActivo());
   	    rbx_actSim.setChecked(_taxa.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Taxas");
        //mapaParam.put("subArea",_subArea.getNome());
   		MasterRep.imprimir("/reportParam/reporteVisualizarTaxas.jrxml", listTaxa, mapaParam, win_regTaxa);
   	
   }
   	
  	public void findByNomeIsActivo(String nome, boolean isActivo){
   		listTaxa = _taxaService.findByNomeActivo(nome, isActivo);
   		lbx_taxa.setModel(new ListModelList<Taxa>(listTaxa));
   	}
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		dbx_valor.setRawValue(null);
   		txb_emolumentos.setRawValue(null);
   		txb_descricao.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   	}
   	
	private void preencherCabecalho() {
		lbl_descricao.setValue(_subArea.getNome());
		lbl_descricao2.setValue(_subArea.getNome());
	}
   	
   	private void listar() {
   		listTaxa= _taxaService.finBySubArea(_subArea);
   		listTaxa= _taxaService.getAll();
   		lbx_taxa.setModel(new ListModelList<Taxa>(listTaxa));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_taxa,"before_center", 4000, true);
   	}

}
