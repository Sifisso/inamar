package mz.ciuem.inamar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.EmbarcacaoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.UserService;

import org.zkoss.bind.annotation.Command;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Input;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class DashboardCtrl extends GenericForwardComposer {

	private Div div_agente;
	private Div div_candidato;
	private Div div_content_out;
	private Include inc_main;
	
	private Label lbl_total_funcioanario;
	private Label lbl_total_peticao;
	private  Label lbl_total_navio;
	
	private List<Funcionario> listFuncionarios= new ArrayList<Funcionario>();
	private List<Object[]> _countByFuncionario = new ArrayList<Object[]>();

	private List<Peticao> listPeticoes= new ArrayList<Peticao>();
	private List<Object[]> _countByPeticao = new ArrayList<Object[]>();
	
	private List<Embarcacao> listEmbarcacoes= new ArrayList<Embarcacao>();
	private List<Object[]> _countByEmbarcacao = new ArrayList<Object[]>();

	@WireVariable
	private FuncionarioService _funcionarioService;
	
	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private EmbarcacaoService _embarcacaoService;
	
	private Session _session;
	
	protected List<String> links;

	
private Window win_funcionarios;
	
	Execution ex = Executions.getCurrent();
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	

	private User _loggedUser;

	@SuppressWarnings("unused")
	private UserService _userService;

	@SuppressWarnings("unchecked")
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_embarcacaoService = (EmbarcacaoService)SpringUtil.getBean("embarcacaoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_funcionarioService = (FuncionarioService)SpringUtil.getBean("funcionarioService");
		_userService = (UserService) SpringUtil.getBean("userService");
	}

	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		_session = Executions.getCurrent().getDesktop().getSession();

		verificarUser();

		mostrarDados();
	}

	public void verificarUser() {

		_loggedUser = (User) _session.getAttribute("ss_utilizador");
	
		
	}

   private void mostrarDados() {
		
		Utente _utente = _loggedUser.getUtente();

		if (_utente == null) {
         
		} else {
			preencherCampos(_utente);
		}
		
		_countByFuncionario = _funcionarioService.getDelegacaoFuncionario (); 
		(lbl_total_funcioanario).setValue(""+totalFuncionarios(_countByFuncionario));
		
		_countByPeticao = _peticaoService.getPeticaoDelegacao();
		(lbl_total_peticao).setValue(""+totalPeticao(_countByPeticao));
		
		_countByEmbarcacao = _embarcacaoService.getNaviosByDelegacaoDeRegisto(); 
		(lbl_total_navio).setValue(""+totalNavios(_countByEmbarcacao));
	}
   
   private int totalFuncionarios(List<Object[]> listObjects){
		listFuncionarios = new ArrayList<Funcionario>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[1]);
			
		}		
		return total;
	}
   
   private int totalPeticao(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[2]);
			
		}		
		return total;
	}
   
   private int totalNavios(List<Object[]> listObjects){
		listEmbarcacoes = new ArrayList<Embarcacao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[2]);
			
		}		
		return total;
	}

	private void preencherCampos(Utente _utente) {

		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/peticoes_utentes.zul");

	}
	
	@Command
	public void verFuncionarios() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents(
				"views/monitoria/total_funcionarios.zul", target,
				map);

	}
}
