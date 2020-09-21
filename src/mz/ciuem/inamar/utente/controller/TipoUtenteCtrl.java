package mz.ciuem.inamar.utente.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

public class TipoUtenteCtrl extends GenericForwardComposer{
	
	private Combobox cbx_tipoUtente;
	private Include inc_main;
	private Div div_content_out;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		session.removeAttribute("ss_utente");
	}
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);

	}
	
	
	public void onSelect$cbx_tipoUtente(){
		String tipoUtente = cbx_tipoUtente.getValue();
		if(tipoUtente.equals("Singular")){
			div_content_out.detach();
			inc_main.setSrc("/views/expediente/registar_utente.zul");
		}else
		     if(tipoUtente.equals("Empresa")){
		 		div_content_out.detach();
				inc_main.setSrc("/views/expediente/registar_empresa.zul");
		}
	}
	

}
