package mz.ciuem.inamar.utente.controller;

import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Distrito;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.DistritoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UtenteCtrl extends GenericForwardComposer{
	
	private Button btn_proximo;
	private Window win_regDados;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_nome;
	private Textbox tbx_apelido;
	private Textbox tbx_nuit;
	private Textbox tbx_rua;
	private Textbox tbx_quarteirao;
	private Textbox tbx_nrCasa;
	private Textbox tbx_celular;
	private Textbox tbx_celular2;
	private Textbox tbx_email;
	private Datebox dtb_dataNascimento;
	private Combobox cbx_provincia;
	private Combobox cbx_distrito;
	private Textbox tbx_bairro;
	
	@WireVariable
	private FuncionarioService _funcionarioService;
	
	@WireVariable
	private UserService _userService;
	@WireVariable
	private ProvinciaService _provinciaService;
	@WireVariable
	private DistritoService _distritoService;
	@WireVariable
	private UtenteService _utenteService;
	private Session _session;
	
	Utente _utente;
	private Funcionario funcionario=null;
	protected User loggeduser;
	private User _loggedUser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_funcionarioService = (FuncionarioService) SpringUtil.getBean("funcionarioService");
		_userService = (UserService) SpringUtil.getBean("userService");
		_provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		_distritoService = (DistritoService) SpringUtil.getBean("distritoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
		//verificarLogado();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		preencherProvincias();
		preencherCampos(_utente);
	}
	
	private void verificarLogado() {
		if(_utente==null){
			_loggedUser = (User) _session.getAttribute("ss_utilizador");
			_utente = _loggedUser.getUtente();
		}
	}

	public void onSelect$cbx_provincia(){
		List<Distrito> listD = _distritoService.buscarDistritosDeUmaProvincia((Provincia) cbx_provincia.getSelectedItem().getValue());
		cbx_distrito.setModel(new ListModelList<Distrito>(listD));
	}
	
	private void preencherProvincias() {
		List<Provincia> listP = _provinciaService.getAll();
		cbx_provincia.setModel(new ListModelList<Provincia>(listP));
	}

	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void proximo(){
	saveOrUpdade();
	Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
	div_content_out.detach();
	inc_main.setSrc("/views/expediente/registar_utenteDetalhes.zul");
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
		u.setApelido(tbx_apelido.getValue());
		u.setDataNascimento(dtb_dataNascimento.getValue());
		u.setNuit(tbx_nuit.getValue());
		u.setProvinciaResidencia(cbx_provincia.getValue());
		u.setDistritoResidencia(cbx_distrito.getValue());
		u.setBairro(tbx_bairro.getValue());
		u.setRua(tbx_rua.getValue());
		u.setNrCasa(tbx_nrCasa.getValue());
		u.setQuarteirao(tbx_quarteirao.getValue());
		u.setCelular(tbx_celular.getValue());
		u.setCelularAlternativo(tbx_celular2.getValue());
		u.setEmail(tbx_email.getValue());
		u.setEmpresa(false);
		u.setTipo("Singular");
		_utenteService.saveOrUpdate(u);
	}
	
	public void preencherCampos(Utente u){
		if(u!=null){
			tbx_nome.setValue(u.getNome());
			tbx_apelido.setValue(u.getApelido());
			dtb_dataNascimento.setValue(u.getDataNascimento());
			tbx_nuit.setValue(u.getNuit());
			cbx_provincia.setValue(u.getProvinciaResidencia());
			cbx_distrito.setValue(u.getDistritoResidencia());
			tbx_bairro.setValue(u.getBairro());
			tbx_rua.setValue(u.getRua());
			tbx_nrCasa.setValue(u.getNrCasa());
			tbx_quarteirao.setValue(u.getQuarteirao());
			tbx_celular.setValue(u.getCelular());
			tbx_celular2.setValue(u.getCelularAlternativo());
			tbx_email.setValue(u.getEmail());
		}
	}

}
