package mz.ciuem.inamar.utente.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@SuppressWarnings({ "serial", "rawtypes" })
public class ConfirmarRegistoCtrl extends GenericForwardComposer{
	
	private Button btn_proximo, btn_actualizar, btn_peticao,btn_confirmar,btn_anterior,btn_continuar;
	private Window win_confirmarRegisto;
	private Include inc_main;
	private Div div_content_out,div_modal;
	
	private Textbox tbx_nome;
	private Textbox tbx_apelido;
	private Textbox tbx_nomePai;
	private Textbox tbx_nomeMae;
	private Textbox tbx_nrDocumento;
	private Textbox tbx_localEmissao;
	private Textbox tbx_nuit;
	private Datebox dtb_dataEmissao;
	private Combobox cbx_tipoDocumento;
	//email senha senha2
	private Textbox tbx_email;
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
	Utente _utentePass;
	
	private List<Utente> listUtente = new ArrayList<Utente>();
	
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
			showNotifications("Utente gravado com sucesso", "info");
			_utente = new Utente();
			session.removeAttribute("ss_utente");
		 
	}
	

	public void onClicPeticao(final ForwardEvent e) {
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		//inc_main.setSrc("/views/expediente/peticoes_utentes.zul");
		
		inc_main.setSrc("/views/expediente/registar_pedido.zul");
		
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
			tbx_apelido.setValue(u.getApelido());
			tbx_nomePai.setValue(u.getNomePai());
			tbx_nomeMae.setValue(u.getNomeMae());
			tbx_nrDocumento.setValue(u.getNumeroDocumento());
			tbx_localEmissao.setValue(u.getLocalEmissao());
			tbx_nuit.setValue(u.getNuit());
			tbx_email.setValue(u.getEmail());
			dtb_dataEmissao.setValue(u.getValidade());
			cbx_tipoDocumento.setValue(u.getTipoDocumento());
		}
	}
	
	public void gravar(){
		
		if(_utente.getUserLogin()==null){
			if(validarSenha()){
				pegarValores();
				//criarUtilizador(tbx_email.getValue(), senha1.getValue(), _utente);
				criarUtilizador(tbx_email.getValue(), senha1.getValue(), _utente);
			}
		}else{
			User u = _utente.getUserLogin();
			tbx_user.setValue("user");
			tbx_pass.setValue("**************");
			pegarValores();
		}
		
		boolean exist = false;
		
		for(Utente utente: listUtente){
			
			if((_utente.getNumeroDocumento()==(utente.getNumeroDocumento()))){
				exist=true;
			}
		}
		
		if(exist=false){
			_utenteService.update(_utente);
		}else{
			Messagebox.show("O Número de documento já foi registado anteriormente, "
					+ "certifique a existência deste utente primeiro");
		}
		
		
		
	}
	
	private void verificarUser() {
		if(_utente.getUserLogin()!=null){
			tbx_email.setValue(_utente.getEmail());
			tbx_email.setDisabled(true);
			senha1.setValue("************");
			senha2.setValue("************");
			senha1.setDisabled(true);
			senha2.setDisabled(true);
		}
	}
	
	@SuppressWarnings("unused")
	private void criarUtilizador(String email, String senha, Utente u) {
		User user = new User();
			
		user.setUsername(u.getNumeroDocumento());
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
		_utente.setApelido(tbx_apelido.getValue());
		_utente.setNomePai(tbx_nomePai.getValue());
		_utente.setNomeMae(tbx_nomeMae.getValue());
		_utente.setNumeroDocumento(tbx_nrDocumento.getValue());
		_utente.setLocalEmissao(tbx_localEmissao.getValue());
		_utente.setNuit(tbx_nuit.getValue());
		_utente.setEmail(tbx_email.getValue());
		_utente.setValidade(dtb_dataEmissao.getValue());
		_utente.setTipoDocumento(cbx_tipoDocumento.getValue());
	}
	
	public void habilitarCampos(){
		tbx_nome.setDisabled(false);
		tbx_apelido.setDisabled(false);
		tbx_nomePai.setDisabled(false);
		tbx_nomeMae.setDisabled(false);
		tbx_nrDocumento.setDisabled(false);
		tbx_localEmissao.setDisabled(false);
		tbx_nuit.setDisabled(false);
		tbx_email.setDisabled(false);
		dtb_dataEmissao.setDisabled(false);
		cbx_tipoDocumento.setDisabled(false);
		verificarUser();
	}
	
	public void desabilitarCampos(){
		tbx_nome.setDisabled(true);
		tbx_apelido.setDisabled(true);
		tbx_nomePai.setDisabled(true);
		tbx_nomeMae.setDisabled(true);
		tbx_nrDocumento.setDisabled(true);
		tbx_localEmissao.setDisabled(true);
		tbx_nuit.setDisabled(true);
		tbx_email.setDisabled(true);
		dtb_dataEmissao.setDisabled(true);
		cbx_tipoDocumento.setDisabled(true);
		verificarUser();
	}
	
	public void anterior(){
		gravar();
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_utenteDetalhes.zul");
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, tbx_nome,"before_center", 4000, true);
	}

}
