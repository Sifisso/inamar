package mz.ciuem.inamar.utente.controller;

import java.util.HashSet;

import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ConfirmarRegistoEmpresaCtrl extends GenericForwardComposer{
	
	private Button btn_proximo, btn_actualizar, btn_peticao,btn_confirmar,btn_anterior,btn_continuar;
	private Window win_confirmarRegisto;
	private Include inc_main;
	private Div div_content_out,div_modal;
	
	private Textbox tbx_nome;
	private Textbox tbx_celularEmpresa;
	private Textbox tbx_emailEmpresa;
	private Textbox tbx_nomeRepresentante;
	private Textbox tbx_nomeProprietario;
	private Textbox tbx_celularProprietario;
	
	//email senha senha2
	private Textbox tbx_emailProprietario;
	private Textbox senha1;
	private Textbox senha2,tbx_user,tbx_pass;
	private Div div_senhas;
	
	
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private UserService _userService;
	@WireVariable
	private UserRoleService _userRoleService;
	
	Utente _utente;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_userService = (UserService) SpringUtil.getBean("userService");
		_userRoleService = (UserRoleService) SpringUtil.getBean("userRoleService");
		_utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos(_utente);
		verificarUser();
	}
	

	public void onClick$btn_actualizar(){
		habilitarCampos();
		btn_actualizar.setVisible(false);
	}
	
	public void onClick$btn_confirmar(){
		gravar();
		div_modal.setVisible(true);
		btn_confirmar.setVisible(false);
		btn_actualizar.setVisible(false);
		btn_anterior.setVisible(false);
		showNotifications("Utente actualizado com sucesso", "info");
		session.removeAttribute("ss_utente");
		
	}
	

	public void onClicPeticao(final ForwardEvent e) {
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/peticoes_utentes.zul");
		
	}
	
	public void onClick$btn_anterior(){
		anterior();
	}
	
	public boolean validarSenha(){
		if(senha1.getValue().equals(senha2.getValue())){
			return true;
		}else{
			div_senhas.setVisible(true);
			return false;
		}
	}
	
	public void preencherCampos(Utente u){
		if(u!=null){
			tbx_nome.setValue(u.getNome());
			tbx_nomeProprietario.setValue(u.getNomeProprietario());
			tbx_celularEmpresa.setValue(u.getCelular());
			tbx_celularProprietario.setValue(u.getCelularRepresentante());
			tbx_emailEmpresa.setValue(u.getEmail());
			tbx_emailProprietario.setValue(u.getEmailRepresentante());
			tbx_nomeRepresentante.setValue(u.getNomeRepresentante());
		}
	}
	
	public void gravar(){
		if(_utente.getUserLogin()==null){
			
				pegarValores();
				//criarUtilizador(tbx_emailEmpresa.getValue(), senha1.getValue(), _utente);
				criarUtilizador(tbx_emailProprietario.getValue(), senha1.getValue(), _utente);
			
		}else{
			User u = _utente.getUserLogin();
			tbx_user.setValue(u.getUsername());
			tbx_pass.setValue("**************");
			pegarValores();
		}
		
		_utenteService.update(_utente);
	}
	
	private void verificarUser() {
		if(_utente.getUserLogin()!=null){
			tbx_emailProprietario.setValue(_utente.getEmail());
			tbx_emailProprietario.setDisabled(true);
			senha1.setValue("************");
			senha2.setValue("************");
			senha1.setDisabled(true);
			senha2.setDisabled(true);
		}
	}
	
	@SuppressWarnings("unused")
	private void criarUtilizador(String email, String senha, Utente u) {
		User user = new User();
			
		user.setUsername(u.getEmail());
		String pass = _userService.encriptarSenha(senha);
		user.setEnabled(true);
		user.SetPasswordEncripted(senha);
		user.setPlanPass(senha);
		user.setPassword(pass);
		user.setUtente(_utente);
		_userService.saveOrUpdate(user);
		int cod = (int) (10000+user.getId());
		u.setUserLogin(user);
		u.setCodigo(""+cod);
		_utenteService.update(u);
		
		HashSet<UserRole> userRoles = new HashSet<UserRole>();
		userRoles.add(_userRoleService.buscarRoleCandidato("Utente"));
		user.setRoles(userRoles);
	
		_userService.update(user);
		
		tbx_user.setValue(email);
		tbx_pass.setValue(senha);
	
	}
	
	public void pegarValores(){
		verificarUser();
		_utente.setNome(tbx_nome.getValue());
		_utente.setNomeProprietario(tbx_nomeProprietario.getValue());
		_utente.setCelular(tbx_celularEmpresa.getValue());
		_utente.setCelularRepresentante(tbx_celularProprietario.getValue());
		_utente.setEmail(tbx_emailEmpresa.getValue());
		_utente.setEmailRepresentante(tbx_emailProprietario.getValue());
		_utente.setNomeRepresentante(tbx_nomeProprietario.getValue());
	
	}
	
	public void habilitarCampos(){
		tbx_nome.setDisabled(false);
		tbx_nomeProprietario.setDisabled(false);
		tbx_celularEmpresa.setDisabled(false);
		tbx_celularProprietario.setDisabled(false);
		tbx_emailEmpresa.setDisabled(false);
		tbx_emailProprietario.setDisabled(false);
		tbx_nomeRepresentante.setDisabled(false);
		verificarUser();
	}
	
	public void desabilitarCampos(){
		tbx_nome.setDisabled(true);
		tbx_nomeProprietario.setDisabled(true);
		tbx_celularEmpresa.setDisabled(true);
		tbx_celularProprietario.setDisabled(true);
		tbx_emailEmpresa.setDisabled(true);
		tbx_emailProprietario.setDisabled(true);
		tbx_nomeRepresentante.setDisabled(true);
		verificarUser();
	}
	
	public void anterior(){
		gravar();
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_empresaDetalhes.zul");
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, tbx_nome,"before_center", 4000, true);
	}

}
