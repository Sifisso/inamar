package mz.ciuem.inamar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.service.UserService;

public class AlterarSenhaCtrl extends GenericForwardComposer<Component> {

	private static final long serialVersionUID = 1L;

	@WireVariable
	private UserService _userService;
	
	Window win;

	@WireVariable
	private UserRoleService _userRoleService;


	User user;
	private Textbox tbx_nome;
	private Textbox tbx_senha;
	private Textbox txb_username;
	private Textbox tbx_novaSenha;
	private Label lbl_nome;
	//private Combobox cbx_universidade;
	private Listbox lbx_candidatura;
	private Div div_dadosUser;
	private Div div_universidade;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_procurar;
	private Button btn_cancelar;

	private Chosenbox chxPerfil;
	//private Chosenbox chxUniversidade;
	private Listbox lbx_utlizadores;
	
	private Checkbox chx_alterarSenha;
	private Checkbox chx_activo;
	
	private List<UserRole> _listUserRoles;
	private ListModelList<User> _listModelUser;
	private ListModelList<UserRole> _listModelUserRole;
	
	private Div div_alterarSenha;
	
	User _selectedUser;
	
	User loggeduser = null;
	
	protected Authentication authentication = SecurityContextHolder
			.getContext().getAuthentication();

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		setUser(new User());
		loggeduser = _userService.getUser(authentication.getName());
		
        preencherPerfil();
        UserSelecionado();
	}

	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		_userRoleService = (UserRoleService) SpringUtil.getBean("userRoleService");
		_userService = (UserService) SpringUtil.getBean("userService");

	}
	
	public void UserSelecionado(){
		
		tbx_nome.setValue(loggeduser.getUsername());
		tbx_senha.setValue(loggeduser.getPassword());
		
		
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public void onClickAlterarSenha(final ForwardEvent e) {
		
		
		/*
		 * Peticao _pet = (Peticao) _peticao; Map<String, Object> mapContaReceber = new
		 * HashMap<String, Object>();
		mapContaReceber.put("peticao", _pet); */
		Executions.createComponents("/views/telinha.zul", win, null);
		
		
	}
	
	

	public void onClick$btn_gravar(Event e) {
		Set<UserRole> setUserRole = chxPerfil.getSelectedObjects();
		user.setUsername(tbx_nome.getValue());
		user.setPassword(tbx_senha.getValue());
		user.setEnabled(true);
		user.setRoles(setUserRole);
		
		_userService.create(user);
		Clients.showNotification("Utilizador registado com sucesso!", "info",
				null, "before_center", -1);
		limparCampos();

	}
	
	public void onClick$btn_cancelar(){
		limparCampos();
	}
	
	public void onClick$btn_procurar(){
		_listModelUser = new ListModelList<User>(_userService.procurarUser(txb_username.getValue()));
		lbx_utlizadores.setModel(_listModelUser);
	}
	
	public void onClick$btn_actualizar(){
			String senha = _userService.encriptarSenha(tbx_novaSenha.getValue());
			loggeduser.setPassword(senha);
		
		_userService.saveOrUpdate(loggeduser);
		
		//Clients.showNotification("Senha alterada com sucesso!", "info", null, "before_center", -1);
		limparCampos();
		
		
	}
	
	
	
	public void onSelect$lbx_utlizadores(){
		if(_listModelUser.isSelectionEmpty()){
			_selectedUser = null;
		}else{
			div_dadosUser.setVisible(false);
			_selectedUser =  lbx_utlizadores.getSelectedItem().getValue();
			tbx_nome.setValue(_selectedUser.getUsername());
			tbx_senha.setValue(_selectedUser.getPassword());
			tbx_senha.setReadonly(true);
			chxPerfil.setSelectedObjects(_selectedUser.getRoles());
			chx_alterarSenha.setVisible(true);
			chx_activo.setVisible(true);
			chx_activo.setChecked(_selectedUser.getEnabled());
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
			
			List<UserRole> listUserRoles = new ArrayList<UserRole>();
			listUserRoles.addAll(_selectedUser.getRoles());
			boolean candidato = verificarRoleCandidato(listUserRoles);
			
			String userName = _selectedUser.getUsername();
			
			boolean email = isValidEmailAddress(userName);
			
			if(!email && candidato == true){
			long codigo = Long.parseLong(userName);
			div_dadosUser.setVisible(true);
			div_universidade.setVisible(false);
			
			}
			
		}
		
	}
	
	public boolean verificarRoleCandidato(List<UserRole> listUserRoles){
		boolean estado= false;
			for (UserRole ur  : listUserRoles){
			
				if(ur.getRolename().equalsIgnoreCase("Candidato")){
					estado = true;
				}
			}
		return estado;
		
	}
	
	public void onCheck$chx_alterarSenha(){
		if(chx_alterarSenha.isChecked()){
			div_alterarSenha.setVisible(true);
		}else{
			div_alterarSenha.setVisible(false);
		}
	}

	
	public void preencherPerfil(){
		_listUserRoles = _userRoleService.getAll();
		_listModelUserRole = new ListModelList<UserRole>(_listUserRoles);
		chxPerfil.setModel(_listModelUserRole);
		
	}
	private void limparCampos() {
		//tbx_nome.setRawValue(null);
		tbx_senha.setRawValue(null);
		chxPerfil.clearSelection();
		tbx_senha.setReadonly(false);
		div_alterarSenha.setVisible(false);
		chx_alterarSenha.setVisible(false);
		chx_alterarSenha.setChecked(false);
		chx_activo.setVisible(false);
		chx_activo.setChecked(false);
		//chxUniversidade.clearSelection();
		_selectedUser = null;
		//btn_actualizar.setVisible(false);
		//btn_gravar.setVisible(true);
		div_dadosUser.setVisible(false);
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	   public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }

}
