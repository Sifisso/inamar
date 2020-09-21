package mz.ciuem.inamar.utente.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.UtenteService;
import net.sf.jasperreports.engine.JRException;
//import mz.ciuem.inamar.vm.*;


import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@SuppressWarnings({ "rawtypes", "serial" })
public class GerirUtenteCtrl extends GenericForwardComposer{
	
	private Window win_gerirUtente;
	private Include inc_main;
	private Div div_content_out;
	
	private Listbox lbx_utentes;
	private Textbox txb_nomefind;
	private Radio rbx_SimMaritimoUtenteFind;
	private Radio rbx_NaoMaritimoUtntenFind;
	
	private Button btn_imprimir;
	
	
	@WireVariable
	private UtenteService _utenteService;
	
	private List<Utente> listUtente = new ArrayList<Utente>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		listUtente();
		session.removeAttribute("ss_utente");
	}

	private void listUtente() {
		listUtente = _utenteService.findUtentesNotMaritimos();
		lbx_utentes.setModel(new ListModelList<Utente>(listUtente));
	}
	
	public void onClickprcurar(ForwardEvent e){
		String nome = txb_nomefind.getValue();
		boolean maritimo = (rbx_NaoMaritimoUtntenFind.isChecked() ? false : true);
		findAllByMaritimoOuUtente(nome, maritimo);
	}
	
	public void findAllByMaritimoOuUtente(String nome, boolean maritimo){
		listUtente = _utenteService.findAllByMaritimoOuUtente(nome, maritimo);
		lbx_utentes.setModel(new ListModelList<Utente>(listUtente));
	}
	
	public void onClickprcurarTodos(ForwardEvent e){
		listUtente();
	}
	
	public void onClickSubmeter(ForwardEvent e){
		Utente u = (Utente) e.getData();
		Executions.getCurrent().getSession().setAttribute("ss_utente", u);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_pedido.zul");
	}
	
	@SuppressWarnings("unchecked")
	public void onClickMaritimo(final ForwardEvent e){
		Messagebox.show("Transformar em Maritimo?", "Maritimo", Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Utente u = (Utente) e.getData();
					
					if(u!=null){
						Executions.getCurrent().getSession().setAttribute("ss_utente", u);
						div_content_out.detach();
						inc_main.setSrc("/views/expediente/registar_maritimo.zul");
					}
				}
			}
		});
		
	}
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "UTENTES");
   		MasterRep.imprimir("/reportParam/reportUtente.jrxml", listUtente, mapaParam, win_gerirUtente);
   	}
	
	@SuppressWarnings({ "unchecked" })
	public void onClickEditar(final ForwardEvent e) {
		Messagebox.show("Deseja Editar Utente?", "Editar Utente",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Utente u = (Utente) e.getData();
					
					boolean tipoUtente = u.isEmpresa();
					if(tipoUtente==true){
						Executions.getCurrent().getSession().setAttribute("ss_utente", u);
						div_content_out.detach();
						inc_main.setSrc("/views/expediente/registar_empresa.zul");
					}else
					     if(tipoUtente==false){
					    	Executions.getCurrent().getSession().setAttribute("ss_utente", u);
							div_content_out.detach();
							inc_main.setSrc("/views/expediente/registar_utente.zul");
					}
					  
				}
			}
		});
		
	}
	
//	@SuppressWarnings({ "unchecked" })
//	public void onClickCancelarProcuar(final ForwardEvent e) {
//		listarPeticao();
//	}
//	
//	@SuppressWarnings({ "unchecked" })
//	public void onClickProcuarPeticaoUtente(final ForwardEvent e) {
//		_peticaoService.procurarPeloNomePedido(lbx_peticao, txb_nomefind.getValue(), listPeticao);
//	}

}
