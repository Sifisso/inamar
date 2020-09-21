package mz.ciuem.inamar.utente.controller;

import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EmpresaDetalhesCtrl extends GenericForwardComposer{
	
	private Button btn_proximo,btn_anterior;
	private Window win_regEmpresaDetalhes;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_nome;
	private Textbox tbx_nomeRepresentante;
	private Textbox tbx_nomeProprietario;
	private Textbox tbx_celular;
	private Textbox tbx_celular2;
	private Textbox tbx_email;
	private Textbox tbx_gerencia;
	private Textbox tbx_capital;

	@WireVariable
	private UtenteService _utenteService;
	
	Utente _utente;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos(_utente);
	}
	
	
	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void onClick$btn_anterior(){
		anterior();
	}
	
	public void proximo(){
	saveOrUpdade();
	Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
	div_content_out.detach();
	inc_main.setSrc("/views/expediente/confirmarRegistoEmpresa.zul");
	}
	
	public void anterior(){
		saveOrUpdade();
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_empresa.zul");
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
		u.setNome(tbx_nome.getValue());
		u.setNomeProprietario(tbx_nomeProprietario.getValue());
		u.setNomeRepresentante(tbx_nomeRepresentante.getValue());
		u.setCelularRepresentante(tbx_celular.getValue());
		u.setCelularRepresentante2(tbx_celular2.getValue());
		u.setEmailRepresentante(tbx_email.getValue());
		u.setGerencia(tbx_gerencia.getValue());
		u.setCapitalSocial(tbx_capital.getValue());
		_utenteService.saveOrUpdate(u);
	}
	
	public void preencherCampos(Utente u){
		if(u!=null){
			tbx_nome.setValue(u.getNome());
			tbx_nomeProprietario.setValue(u.getNomeProprietario());
			tbx_nomeRepresentante.setValue(u.getNomeRepresentante());
			tbx_celular.setValue(u.getCelularRepresentante());
			tbx_celular2.setValue(u.getCelularRepresentante2());
			tbx_email.setValue(u.getEmailRepresentante());
			tbx_gerencia.setValue(u.getGerencia());
			tbx_capital.setValue(u.getCapitalSocial());
		}
	}


}
