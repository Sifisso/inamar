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

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GerirMaritimoCtrl extends GenericForwardComposer{
	
	private Window win_gerirUtente;
	private Include inc_main;
	private Div div_content_out;
	
	private Listbox lbx_utentes;
	private Textbox txb_nomefind;
	private Radio rbx_Simfin, rbx_Naofin;
	
	@WireVariable
	private UtenteService _utenteService;
	
	private List<Utente> listMaritimo = new ArrayList<Utente>();
	
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
		listMaritimo = _utenteService.findUtentesMaritimos();
		lbx_utentes.setModel(new ListModelList<Utente>(listMaritimo));
	}
	
	public void onClickSubmeter(ForwardEvent e){
		Utente u = (Utente) e.getData();
		Executions.getCurrent().getSession().setAttribute("ss_utente", u);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_pedido.zul");
	}
	
	
   	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "MARITIMOS");
   		MasterRep.imprimir("/reportParam/reportUtente.jrxml", listMaritimo, mapaParam, win_gerirUtente);
   	}
	
	@SuppressWarnings("unchecked")
	public void onClickEditar(final ForwardEvent e){
		Messagebox.show("Editar Maritimo?", "Maritimo", Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
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
	
	/*
	@SuppressWarnings({ "unchecked" })
	public void onClickEditar(final ForwardEvent e) {
		Messagebox.show("Deseja Editar Utente?", "Editar Utente",Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				
				if("onYes".equals(event.getName())){
					Utente u = (Utente) e.getData();
					
					String tipoUtente = u.getTipo();
					if(tipoUtente!=null){
					if(tipoUtente.equals("Singular")){
						Executions.getCurrent().getSession().setAttribute("ss_utente", u);
						div_content_out.detach();
						inc_main.setSrc("/views/expediente/registar_utente.zul");
					}else
					     if(tipoUtente.equals("Empresa")){
					    	Executions.getCurrent().getSession().setAttribute("ss_utente", u);
							div_content_out.detach();
							inc_main.setSrc("/views/expediente/registar_empresa.zul");
					}
					}else{
						Executions.getCurrent().getSession().setAttribute("ss_utente", u);
						div_content_out.detach();
						inc_main.setSrc("/views/expediente/registar_utente.zul"); 
					}
					  
				}
			}
		});
		
	}*/


}
