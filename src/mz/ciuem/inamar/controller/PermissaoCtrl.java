package mz.ciuem.inamar.controller;

import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.service.PermissionService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

@SuppressWarnings("rawtypes")
public class PermissaoCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;

	@WireVariable
	private PermissionService _permissionService;

	private Listbox lbx_permissoes;

	ListModelList<Permission> _permissions;

	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		_permissionService = (PermissionService) SpringUtil.getBean("permissionService");

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		visualizarPermissoes();
	}

	private void visualizarPermissoes() {
		_permissions = new ListModelList<Permission>(
				_permissionService.getAll());
		lbx_permissoes.setModel(_permissions);
	}
}
