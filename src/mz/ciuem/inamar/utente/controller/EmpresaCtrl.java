package mz.ciuem.inamar.utente.controller;

import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.DistritoService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EmpresaCtrl extends GenericForwardComposer{
	
	private Button btn_proximo;
	private Window win_regEmpresa;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_celular;
	private Textbox tbx_celular2;
	private Textbox tbx_fixoFax;
	private Textbox tbx_email;
	private Textbox tbx_certidao;
	private Textbox tbx_registoNotarial;
	private Textbox tbx_objectoSocial;
	
	@WireVariable
	private UtenteService _utenteService;
	private Session _session;
	
	Utente _utente;
	private User _loggedUser;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		preencherCampos(_utente);
	}
	
	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void proximo(){
		saveOrUpdade();
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_empresaDetalhes.zul");
	}
	
	public void saveOrUpdade(){
		if(_utente==null){
			_utente = new Utente();
			gravar(_utente);
		}else{
			gravar(_utente);
		}
	}
	
	public void gravar(Utente u){
		u.setCelular(tbx_celular.getValue());
		u.setCelularAlternativo(tbx_celular2.getValue());
		u.setEmail(tbx_email.getValue());
		u.setFixoFax(tbx_fixoFax.getValue());
		u.setCertidao_comercial(tbx_certidao.getValue());
		u.setRegisto_notarial(tbx_registoNotarial.getValue());
		u.setObjecto_social(tbx_objectoSocial.getValue());
		u.setEmpresa(true);
		_utenteService.saveOrUpdate(u);
	}
	
	public void preencherCampos(Utente u){
		if(u!=null){
			tbx_celular.setValue(u.getCelular());
			tbx_celular2.setValue(u.getCelularAlternativo());
			tbx_email.setValue(u.getEmail());
			tbx_fixoFax.setValue(u.getFixoFax());
			tbx_certidao.setValue(u.getCertidao_comercial());
			tbx_registoNotarial.setValue(u.getRegisto_notarial());
			tbx_objectoSocial.setValue(u.getObjecto_social());
		}
	}
	
}
