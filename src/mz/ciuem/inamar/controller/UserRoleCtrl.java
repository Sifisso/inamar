package mz.ciuem.inamar.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import mz.ciuem.inamar.comps.DualListbox;
import mz.ciuem.inamar.entity.Entity;
import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.PermissionService;
import mz.ciuem.inamar.service.UserRoleService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

@SuppressWarnings("rawtypes")
public class UserRoleCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;

	@WireVariable
	private UserRoleService _userRoleService;

	@WireVariable
	private PermissionService _permissionService;

	private ListModelList<Permission> _permissions;

	private Textbox txb_perfil;

	private Listbox lbx_perfis;
	private List<UserRole> userRoles;
	private DualListbox dual_direitos;

	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;

	private UserRole _userRole;
	private UserRole _selectedUserRole;

	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		set_userRole(new UserRole());
		listarPermissoes();
		listarPerfis();

	}

	@SuppressWarnings("unchecked")
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		_userRoleService = (UserRoleService) SpringUtil
				.getBean("userRoleService");
		_permissionService = (PermissionService) SpringUtil
				.getBean("permissionService");
	}

	@SuppressWarnings("unchecked")
	public void onClick$btn_gravar(Event e) {
		_userRole.setRolename(txb_perfil.getValue());

		_userRole.getPermissions().addAll(
				(Collection<? extends Permission>) dual_direitos
						.getChosenDataList());

		_userRoleService.create(_userRole);
		Clients.showNotification("Peril registado com sucesso!", "info", null,
				"before_center", -1);
		limparCampos();
		listarPerfis();
	}

	public void listarPerfis() {
		userRoles = new ListModelList<UserRole>(_userRoleService.getAll());
		lbx_perfis.setModel((ListModel<?>) userRoles);

	}

	public void onClick$btn_actualizar(Event e) throws InterruptedException {
		_selectedUserRole.setRolename(txb_perfil.getValue());

		_selectedUserRole.getPermissions().addAll((Collection<? extends Permission>) dual_direitos.getChosenDataList());

		_userRoleService.update(_selectedUserRole);
		Clients.showNotification("Peril Actualizado com sucesso!", "info", null,
				"before_center", -1);
		
		listarPerfis();
		limparCampos();
	}

	private void saveOraupdate(UserRole userRole) {
		userRole.setRolename(txb_perfil.getValue());
		userRole.setRolename("Normal");
		limparCampos();

	}
	
	

	private void limparCampos() {
		txb_perfil.setRawValue(null);
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		dual_direitos.chosenDataModel.clear();
		dual_direitos.candidateModel.clear();
		listarPermissoes();
	}
	
	public void onSelect$lbx_perfis(Event e) throws InterruptedException {
		limparCampos();
		listarPermissoes();
		_selectedUserRole = (UserRole) lbx_perfis.getSelectedItem().getValue();
		txb_perfil.setValue(_selectedUserRole.getRolename());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
		Set<Permission> l = _selectedUserRole.getPermissions(); 
		dual_direitos.chosenDataModel.addAll(new ListModelList<Permission>(l));             
		dual_direitos.candidateModel.removeAll(l);
		
	}
	
	public void onClick$btn_cancelar(){
		limparCampos();
	}
	
    
	
	public void listarPermissoes(){
		_permissions = new ListModelList<Permission>(_permissionService.getAll());
		dual_direitos.setModel(_permissions);
	}

	public List<Permission> get_permissions() {
		return _permissions;
	}

	public Textbox getTxb_perfil() {
		return txb_perfil;
	}

	public void setTxb_perfil(Textbox txb_perfil) {
		this.txb_perfil = txb_perfil;
	}

	public UserRole get_userRole() {
		return _userRole;
	}

	public void set_userRole(UserRole _userRole) {
		this._userRole = _userRole;
	}

}
