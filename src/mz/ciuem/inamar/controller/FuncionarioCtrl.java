package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.DelegacaoDepartamentoSector;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.UserRole;
import mz.ciuem.inamar.service.DelegacaoDepartamentoSectorService;
import mz.ciuem.inamar.service.DelegacaoDepartamentoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.UserRoleService;
import mz.ciuem.inamar.service.UserService;
import net.sf.jasperreports.engine.JRException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.w3c.dom.ls.LSInput;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class FuncionarioCtrl extends GenericForwardComposer{
	
	private Textbox txb_apelido, txb_nomefind, txb_emailfind;
	private Textbox txb_nome;
	private Textbox txb_email;
	private Intbox itx_contacto;
	private Longbox lgx_telefone;
	private Intbox itx_nuit;
	private Combobox cbx_instituicao, cbx_delegacao, cbx_departamento, cbx_sector;
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_listar;
	private Button btn_nova;
	private Chosenbox chxPerfil;
	private Listbox lbx_agentes;
	private Button btn_magia;
	private Button btn_imprimir;
	
	int i=1;
	

	@WireVariable
	private FuncionarioService _funcionarioService;
	@WireVariable
	private UserService _userService;
	@WireVariable
	private UserRoleService _userRoleService;
	@WireVariable
	private InstituicaoService _insrituicaoService;
    @WireVariable
    private DelegacaoService _delegacaoService;
    @WireVariable
    private DelegacaoDepartamentoService _delegacaoDepartamentoService;
    @WireVariable
    private DelegacaoDepartamentoSectorService _delegacaoDepartamentoSectorService;
    
    ListModelList <UserRole> listUserRoles ;
    
    private List<UserRole> _listUserRoles;
	private ListModelList<User> _listModelUser;
	private ListModelList<UserRole> _listModelUserRole;
	private Window win;
	
	Execution ex = Executions.getCurrent();
	
	private List<Funcionario> listAgen = new ArrayList<Funcionario>(); 
	
	Funcionario _selectedFuncionario;
	DelegacaoDepartamentoSector _selectedSector;
	
	 public void doBeforeComposeChildren(Component comp) throws Exception {
	        super.doBeforeComposeChildren(comp);
	        _userRoleService  = (UserRoleService) SpringUtil.getBean("userRoleService");
	        _userService = (UserService) SpringUtil.getBean("userService");
	        _insrituicaoService = (InstituicaoService) SpringUtil.getBean("instituicaoService");
	        _delegacaoDepartamentoService = (DelegacaoDepartamentoService) SpringUtil.getBean("delegacaoDepartamentoService");
	        _delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
	        _delegacaoDepartamentoSectorService = (DelegacaoDepartamentoSectorService) SpringUtil.getBean("delegacaoDepartamentoSectorService");
	        _funcionarioService = (FuncionarioService) SpringUtil.getBean("funcionarioService");
	 }
	
	public void doAfterCompose (Component comp) throws Exception{
		super.doAfterCompose(comp);
		preencherPerfis();
		listarIntituicao();
		visualizarAgente();
		preencherPerfil();
	}
	
	public void listarIntituicao(){
		List<Instituicao> listI = _insrituicaoService.getAll();
		cbx_instituicao.setModel(new ListModelList<Instituicao>(listI));
	}
	
	
	public void onClickprcurar(ForwardEvent e){
		String nome = txb_nomefind.getValue();
		String email = txb_nomefind.getValue();
		findByNome(nome, email);
	}
	
	public void findByNome (String nome, String email){
		listAgen = _funcionarioService.findByNome(nome, email);
		lbx_agentes.setModel(new ListModelList<Funcionario>(listAgen));
	}
	
	public void onSelect$cbx_instituicao(){
		
		cbx_delegacao.setRawValue(null);
		cbx_delegacao.getItems().clear();
		
		cbx_departamento.setRawValue(null);
		cbx_departamento.getItems().clear();
		
		cbx_sector.setRawValue(null);
		cbx_sector.getItems().clear();
		
		cbx_delegacao.setModel(new ListModelList<Delegacao>(_delegacaoService.findByInstituicao((Instituicao) cbx_instituicao.getSelectedItem().getValue())));
	}
	
	public void onSelect$cbx_delegacao(){
		cbx_departamento.setRawValue(null);
		cbx_departamento.getItems().clear();
		
		cbx_sector.setRawValue(null);
		cbx_sector.getItems().clear();
		
		cbx_departamento.setModel(new ListModelList<DelegacaoDepartamento>(_delegacaoDepartamentoService.findByDelegacao((Delegacao) cbx_delegacao.getSelectedItem().getValue())));
	}
	
	public  void onSelect$cbx_departamento(){
		cbx_sector.setRawValue(null);
		cbx_sector.getItems().clear();
		
		cbx_sector.setModel(new ListModelList<DelegacaoDepartamentoSector>(_delegacaoDepartamentoSectorService.findByDelegacaoDepartamento((DelegacaoDepartamento) cbx_departamento.getSelectedItem().getValue())));
	}
	 
	public void onClick$btn_gravar(Event e){
		boolean existe = _userService.emailExiste(txb_email.getValue());
		
		if(!existe){
			Set<UserRole> setUserRole = chxPerfil.getSelectedObjects();
	    	 Funcionario _agente = new Funcionario();
			_agente.setApelido(txb_apelido.getText());
			_agente.setNome(txb_nome.getText());
			_agente.setEmail(txb_email.getText());
			_agente.setTelefone(itx_contacto.getValue());
			_agente.setNuit(itx_nuit.getValue());
			_agente.setSector((DelegacaoDepartamentoSector) cbx_sector.getSelectedItem().getValue());
			
			//_funcionarioService.saveOrUpdate(_agente);
			
			criarAgente(_agente, setUserRole);
			
	        showNotification("Funcionario registado com sucesso","info");
			
			limparCampos();
			visualizarAgente();
		}else{
			Messagebox.show("O email a registar ja existe");
		}

		
	}
	
	private void criarAgente(Funcionario _agente, Set<UserRole> setUserRole) {
		Funcionario _fun = _funcionarioService.create(_agente);
		Set<UserRole> setUserRoles = new HashSet<>();

		for (UserRole ur : setUserRole) {

			setUserRoles.add(ur);
		}
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String pass = gerarSenhaAleatoria();*/

		User user = new User();

		user.setUsername(_fun.getEmail());
		user.setRoles(setUserRoles);
		_fun.setUserLogin(user);
		user.setFuncionario(_fun);
		
			
		
		//Envia poe Email
		/*Notificacao n = new Notificacao();
		n.setDestinoEmail(agent.getEmail());
		n.setEnviada(false);
		n.setMsg("Seja bem vindo ao ecampus -- Receba os dados de acesso para o Sistema de Gestao de Candidaturas para os Exames de admissao "+"\n"+" Utilizador :" +user.getUsername()+" Senha:"+user.getPlanPass()+" ");
		n.setType("Dados de Acesso");
		notificacaoDao.saveOrUpdate(n);*/
		
		String senha = ""+itx_contacto.getValue();

		criarUtilizador(_fun.getEmail(),senha , _fun, user);
	}
	
	private void criarUtilizador(String email, String senha, Funcionario funcionario, User user) {
		
		String pass = _userService.encriptarSenha(senha);
		user.setEnabled(true);
		user.SetPasswordEncripted(senha);
		user.setPlanPass(senha);
		user.setPassword(pass);
		_userService.saveOrUpdate(user);
		_funcionarioService.saveOrUpdate(funcionario);
	
	}

	private void showNotification(String message, String type) {
		 Clients.showNotification(message, type, win, "middle_center", 4000);
		
	}


	public void onClick$btn_cancelar(Event e){
		
		limparCampos();
		visualizarAgente();
	}
	
	public void onClick$btn_actualizar(Event e){
		
		//Mudar O Intbox na view ou mudar o mtodo para String no modelo;
				_selectedFuncionario.setApelido(txb_apelido.getText());
				_selectedFuncionario.setNome(txb_nome.getText());
				_selectedFuncionario.setEmail(txb_email.getText());
				_selectedFuncionario.setTelefone(itx_contacto.getValue());
				_selectedFuncionario.setNuit(itx_nuit.getValue());
				//_selectedFuncionario.setSector((DelegacaoDepartamentoSector)cbx_sector.getSelectedItem().getValue());
				
				showNotification("Funcionario actualizado com sucesso","info");
				if(_selectedFuncionario.getUserLogin()==null){
					
					boolean existe = _userService.emailExiste(txb_email.getValue());
					
					if(!existe){
						
						User user = new User();

						Set<UserRole> setUserRoles = new HashSet<>();

						UserRole ur = _userRoleService.find((long) 3);
						setUserRoles.add(ur);
					
						BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
						String pass = gerarSenhaAleatoria();
						
						user.setUsername(_selectedFuncionario.getEmail());
						user.setPassword(passwordEncoder.encode(pass));
						user.setPlanPass(pass);
						user.setEnabled(false);
						user.setRoles(setUserRoles);
						try {
						_userService.create(user);
						_selectedFuncionario.setUserLogin(user);
						_funcionarioService.update(_selectedFuncionario);
							
						/*Notificacao n = new Notificacao();
						n.setDestinoEmail(_selectedFuncionario.getEmail());
						n.setEnviada(false);
						n.setMsg("Seja bem vindo ao ecampus -- Receba os dados de acesso para o Sistema de Gestão de Candidaturas para os Exames de admissão "+"\n"+" Utilizador :" +user.getUsername()+" Senha:"+user.getPlanPass()+" ");
						n.setType("Dados de Acesso");
						_notificacaoService.saveOrUpdate(n);*/
						} catch (DataIntegrityViolationException ex) {
							showNotification("O Email registado ja foi Usado anteriormente"+ex.toString(), "info");
						}
						
					}else{
						Messagebox.show("O email a registar ja existe");
					}
					
				}
				_funcionarioService.update(_selectedFuncionario);
				
				limparCampos();
				visualizarAgente();
				
				btn_gravar.setVisible(true);
				btn_actualizar.setVisible(false);

		
	}
	
	public void onSelect$lbx_agentes(Event e){
			_selectedFuncionario = (Funcionario)lbx_agentes.getSelectedItem().getValue();
			
			txb_apelido.setValue(_selectedFuncionario.getApelido());
			txb_nome.setValue(_selectedFuncionario.getNome());
			txb_email.setValue(_selectedFuncionario.getEmail());
			chxPerfil.setSelectedObjects(_selectedFuncionario.getRoles());
			//lgx_telefone.setValue(_selectedFuncionario.getTelefone());
			//UnidadeOrganica unidade = (UnidadeOrganica)_selectedFuncionario.getSector().getUnidadeOrganica();
			//cbx_unidadeOrganica.setValue(unidade.getDesignacao());
			//cbx_sector.setModel(new ListModelList<Sector>(_selectedFuncionario.getSector().getUnidadeOrganica().getSectores()));
			cbx_sector.setValue(_selectedFuncionario.getSector().getSectorr().getNome());
			cbx_delegacao.setValue(_selectedFuncionario.getSector().getDelegacaoDepartamento().getDelegacao().getNome());
			cbx_departamento.setValue(_selectedFuncionario.getSector().getDelegacaoDepartamento().getDepartamento().getNome());
			cbx_instituicao.setValue(_selectedFuncionario.getSector().getDelegacaoDepartamento().getDelegacao().getInstituicao().getNome());
			
			List<UserRole> listUserRoles = new ArrayList<UserRole>();
			listUserRoles.addAll(_selectedFuncionario.getRoles());
			
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
	}
	
	
	public void limparCampos(){
		txb_nome.setRawValue(null);
		txb_apelido.setRawValue(null);
		txb_email.setRawValue(null);
		itx_contacto.setRawValue(null);
		itx_nuit.setRawValue(null);
		cbx_instituicao.setRawValue(null);
		cbx_delegacao.setRawValue(null);
		cbx_departamento.setRawValue(null);
        cbx_sector.setRawValue(null);
		lbx_agentes.clearSelection();
		chxPerfil.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}
	
		
	
	private void visualizarAgente(){
		List<Funcionario> listF = _funcionarioService.getAll();
		lbx_agentes.setModel(new ListModelList<Funcionario>(listF));
	} 
	
	
	private void preencherPerfis(){
		listUserRoles = new ListModelList<UserRole>(_userRoleService.buscarRoleNaoAdminCandidato());
		chxPerfil.setModel(listUserRoles);
	}
	
	public void preencherPerfil(){
		_listUserRoles = _userRoleService.getAll();
		_listModelUserRole = new ListModelList<UserRole>(_listUserRoles);
		chxPerfil.setModel(_listModelUserRole);
		
	}
	
	private static String gerarSenhaAleatoria() {
		int max = 6;
		String[] caracteres = { "a", "1", "b", "2", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };

		StringBuilder senha = new StringBuilder();

		for (int i = 0; i < max; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			senha.append(caracteres[posicao]);
		}

		return senha.toString();
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
//		List<Funcionario> lisFuncionarios = new ArrayList<Funcionario>();
//		List<Object[]> lisFuncionarios = _funcionarioService.
//		
//		Map<String, Object> mapaParam = new HashMap<String, Object>();	
//		final Execution ex = Executions.getCurrent();
//		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
//        mapaParam.put("imagemLogo", inputV);
//        mapaParam.put("listNome", "Lista de Agentes");
//		MasterRep.imprimir("/reportParam/reportAgentParam.jrxml", listAgen, mapaParam, win);
	}
	

}
