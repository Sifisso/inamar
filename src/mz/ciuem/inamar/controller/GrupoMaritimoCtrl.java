package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.TipoCombustivel;
import mz.ciuem.inamar.service.Grupo_MaritimoService;
import mz.ciuem.inamar.service.TipoCombustivelService;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GrupoMaritimoCtrl extends GenericForwardComposer{
	
	//Main Div
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Button btn_procurar;
	private Listbox lbx_grupoMaritimo;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Radio rbx_actSim;
	private Radio rbx_actNao;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regGrupoMaritimo;
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	Execution ex = Executions.getCurrent();
	
	private GrupoMaritimo _grupoMaritimo;
	
	@WireVariable
	private Grupo_MaritimoService _grupo_MaritimoService;
	
    private List<GrupoMaritimo> listGrupoMaritimo= new ArrayList<GrupoMaritimo>();
    
    @SuppressWarnings("unchecked")
   	@Override
   	public void doBeforeComposeChildren(Component comp) throws Exception {
   		super.doBeforeComposeChildren(comp);
   		
   		_grupo_MaritimoService =  (Grupo_MaritimoService) SpringUtil.getBean("grupo_MaritimoService");
   		
   	}

   	@SuppressWarnings("unchecked")
   	@Override
   	public void doAfterCompose(Component comp) throws Exception {
   		// TODO Auto-generated method stub
   		super.doAfterCompose(comp);

   		listar();
   	}


  /* public void onClickprcurar(ForwardEvent e)  {
                String nome = txb_nomefind.getValue();
                boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
                findByNomeIsAdmar(nome,isActivo);
   	}*/
   	
	public void onClickConfig(ForwardEvent e)  {
		GrupoMaritimo _grupoMaritimo =  (GrupoMaritimo) e.getData();
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("_grupoMaritimo", _grupoMaritimo);
		win_regGrupoMaritimo.getChildren().clear();
		Executions.createComponents("/views/Parametrizacao/registar_classeMaritimo.zul", win_regGrupoMaritimo, map);
		
	}
   	
   	public void onClick$btn_actualizar() throws InterruptedException {
   		
        _grupoMaritimo.setActivo(rbx_actSim.isChecked() ? true : false);
   	    
        _grupoMaritimo.setNome(txb_nome.getValue());
   		
   		_grupo_MaritimoService.update(_grupoMaritimo);
   		showNotifications("Grupo Maritimo actualizado com sucesso!", "info");
   		limparCampos();

   	}

   	public void onClick$btn_gravar(Event e) throws InterruptedException{

   		GrupoMaritimo _gm = new GrupoMaritimo();
   	    
   		_gm.setActivo(rbx_actSim.isChecked() ? true : false);
           	    
   		_gm.setNome(txb_nome.getValue());

   		_grupo_MaritimoService.create(_gm);
   		showNotifications("Grupo Maritimo registado com sucesso!", "info");
   		limparCampos();
   	}

   	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
   	
   	public void onSelect$lbx_grupoMaritimo(Event e){
   		_grupoMaritimo = lbx_grupoMaritimo.getSelectedItem().getValue();
   		txb_nome.setValue(_grupoMaritimo.getNome());
   	    rbx_actNao.setChecked(!_grupoMaritimo.isActivo());
   	    rbx_actSim.setChecked(_grupoMaritimo.isActivo());
   		btn_actualizar.setVisible(true);
   		btn_gravar.setVisible(false);
   	}
   	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Grupos Maritimos");
   		MasterRep.imprimir("/reportParam/reportParametrizacaoBase.jrxml", listGrupoMaritimo, mapaParam, win_regGrupoMaritimo);
   	}
   	
 	/*public void findByNomeIsAdmar(String nome, boolean isActivo){
   		listFluxo = _fluxoService.findByNomeIsActivo(nome, isActivo);
   		lbx_fluxo.setModel(new ListModelList<Fluxo>(listFluxo));
   	}*/
   	
   	private void limparCampos() {
   		txb_nome.setRawValue(null);
   		rbx_actSim.setChecked(false);
   	    rbx_actNao.setChecked(true);
   		btn_gravar.setVisible(true);
   		btn_actualizar.setVisible(false);
   		listar();
   		
   	}
   	
   	private void listar() {
   		listGrupoMaritimo = _grupo_MaritimoService.getAll();
   		lbx_grupoMaritimo.setModel(new ListModelList<GrupoMaritimo>(listGrupoMaritimo));
   	}
   	
   	
   	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, lbx_grupoMaritimo,"before_center", 4000, true);
   	}



}
