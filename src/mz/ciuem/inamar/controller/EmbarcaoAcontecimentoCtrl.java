package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.entity.ActividadeZonaMaritima;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.DelegacaoDepartamento;
import mz.ciuem.inamar.entity.Departamento;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.EmbarcacaoAcontecimento;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.service.AcontecimentoService;
import mz.ciuem.inamar.service.DelegacaoDepartamentoService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.EmbarcacaoAcontecimentoService;
import mz.ciuem.inamar.service.EmbarcacaoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.PaisService;
import mz.ciuem.inamar.service.UserService;

public class EmbarcaoAcontecimentoCtrl extends GenericForwardComposer{

	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	//Main Div
		private Textbox txb_nomefind;
		private Radio rbx_actSimfin;
		private Radio rbx_actNaofind;
		private Listbox lbx_embarcaoAcontecimento;
		private Listbox lbx_embarcacoes;
		private Button btn_procurar;
		private Button btn_procurarr;
		private Button btn_imprimir;
		private Combobox cbx_acontecimentoFind;
		private Textbox txb_embarcacaoFind;
		
		//Modal Div
		private Datebox dbx_dataOcorrencia;
		private Combobox cbx_paises;
		private Combobox cbx_acontecimento;
		private Combobox cbx_embarcacao;
		private Textbox txb_desAcontecimento;
		private Chosenbox chxDep;
		private Button btn_gravar;
		private Button btn_actualizar;
		private Button btn_cancelar;
		private Button btn_embarcacao;
		
		private Window win_regEmbarcaoAcontecimento;
		
		
		Execution ex = Executions.getCurrent();
		
		private EmbarcacaoAcontecimento _embarcacaoAcontecimento;
		
		private Funcionario funcionario=null;
		protected User loggeduser;
		protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		private Acontecimento _acontecimento;
		
		private Embarcacao _embarcacao;
		
		private Pais _pais;
		
		
		@WireVariable
		private EmbarcacaoAcontecimentoService _embarcacaoAcontecimentoService;
		
		@WireVariable
		private FuncionarioService _funcionarioService;
		
		@WireVariable
		private UserService _userService;
		
		@WireVariable
		private AcontecimentoService _acontecimentoService;
		
		@WireVariable
		private EmbarcacaoService _embarcacaoService; 
		
		@WireVariable
		private PaisService _paisService;
		
		private List<EmbarcacaoAcontecimento> listEmbarcacaoAcontecimento = new ArrayList<EmbarcacaoAcontecimento>();
		
		private List<Acontecimento> listAcontecimento = new ArrayList<Acontecimento>();
		
		private List<Embarcacao> listEmbarcacao = new ArrayList<Embarcacao>();
		
		private List<Pais> listPais = new ArrayList<Pais>();
		
		@SuppressWarnings("unchecked")
		@Override
		public void doBeforeComposeChildren(Component comp) throws Exception {
			super.doBeforeComposeChildren(comp);
			
			_embarcacaoAcontecimentoService = (EmbarcacaoAcontecimentoService) SpringUtil.getBean("embarcacaoAcontecimentoService");
			_funcionarioService = (FuncionarioService) SpringUtil.getBean("funcionarioService");
			_userService = (UserService) SpringUtil.getBean("userService");
			_acontecimentoService = (AcontecimentoService) SpringUtil.getBean("acontecimentoService");
			_embarcacaoService = (EmbarcacaoService) SpringUtil.getBean("embarcacaoService");
			_paisService = (PaisService) SpringUtil.getBean("paisService");
			
			_embarcacao = (Embarcacao) ex.getArg().get("_embarcacao");
			_acontecimento = (Acontecimento) ex.getArg().get("_acontecimento");
			_pais = (Pais) ex.getArg().get("_pais");
			
			loggeduser = _userService.getUser(authentication.getName());
			
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			// TODO Auto-generated method stub
			super.doAfterCompose(comp);

			if(loggeduser.getFuncionario() != null){
				funcionario = _funcionarioService.buscarDadosDoFuncionario(loggeduser.getFuncionario());
			}
			
			listar();
			listarEmbarcoesLbx();
			listarEmbarcacao();
			listarAcontecimento();
			listarPaises();
			
		}
		
		private void listar() {
			listEmbarcacaoAcontecimento =  _embarcacaoAcontecimentoService.getAll();
			lbx_embarcaoAcontecimento.setModel(new ListModelList<EmbarcacaoAcontecimento>(listEmbarcacaoAcontecimento));
		}
		
		private void listarEmbarcoesLbx(){
			listEmbarcacao = _embarcacaoService.getAll();
			lbx_embarcacoes.setModel(new ListModelList<Embarcacao>(listEmbarcacao));
		}
		
		private void listarEmbarcacao(){
			cbx_embarcacao.setModel(new ListModelList<Embarcacao>(_embarcacaoService.getAll()));
		}
		
		private void listarAcontecimento(){
			cbx_acontecimento.setModel(new ListModelList<Acontecimento>(_acontecimentoService.getAll()));
		}
		
		private void listarPaises(){
			cbx_paises.setModel(new ListModelList<Pais>(_paisService.getAll()));
		}
		
		public void onClickprcurar(ForwardEvent e)  {
			String embarcacao = txb_embarcacaoFind.getValue();
	         findByNome(embarcacao);
			}

		 public void findByNome(String embarcacao){
		   		listEmbarcacaoAcontecimento = _embarcacaoAcontecimentoService.findByNome(embarcacao);
		   		lbx_embarcaoAcontecimento.setModel(new ListModelList<EmbarcacaoAcontecimento>(listEmbarcacaoAcontecimento));
		   	}
		
		 public void gravar(Delegacao del, User loggedUser){
			 EmbarcacaoAcontecimento embAct = new EmbarcacaoAcontecimento();
				embAct.setDelegacao(del);
				embAct.setUserLoggado(loggedUser);
				embAct.setDescricao(txb_desAcontecimento.getValue());
				embAct.setDataOcorrencia(dbx_dataOcorrencia.getValue());
				embAct.setEmbarcacao((Embarcacao)cbx_embarcacao.getSelectedItem().getValue());
				embAct.setAcontecimento((Acontecimento)cbx_acontecimento.getSelectedItem().getValue());
				embAct.setPais((Pais)cbx_paises.getSelectedItem().getValue());
				
				_embarcacaoAcontecimentoService.saveOrUpdate(embAct);
				listar();
				limparCampos();
				
				showNotifications("Descri��o de Acontecimento registado com sucesso!", "info");
		 }
		 
		public void onClick$btn_gravar(Event e) throws InterruptedException{
			
			gravar(funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser);
		}
		
		public void actualizar(Delegacao del, User loggedUser) {
			_embarcacaoAcontecimento.setDelegacao(del);
			_embarcacaoAcontecimento.setUserLoggado(loggedUser);
			_embarcacaoAcontecimento.setDescricao(txb_desAcontecimento.getValue());
			_embarcacaoAcontecimento.setDataOcorrencia(dbx_dataOcorrencia.getValue());
			_embarcacaoAcontecimento.setEmbarcacao((Embarcacao)cbx_embarcacao.getSelectedItem().getValue());
			_embarcacaoAcontecimento.setAcontecimento((Acontecimento)cbx_acontecimento.getSelectedItem().getValue());
			_embarcacaoAcontecimento.setPais((Pais)cbx_paises.getSelectedItem().getValue());
			
			_embarcacaoAcontecimentoService.update(_embarcacaoAcontecimento);
			listar();
			showNotifications("Descri��o de Acontecimento actualizado com sucesso!", "info");
			limparCampos();
		}
		
		public void onClick$btn_actualizar() throws InterruptedException {
			actualizar(funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser);
		}
		
		public void onClick$btn_embarcacao() throws InterruptedException {
			
			_embarcacaoAcontecimento.setEmbarcacao((Embarcacao)cbx_embarcacao.getSelectedItem().getValue());
			
			_embarcacaoAcontecimentoService.saveOrUpdate(_embarcacaoAcontecimento);
			cbx_embarcacao.setDisabled(true);
			btn_gravar.setVisible(true);
			listar();
			showNotifications("Descri��o de Acontecimento actualizado com sucesso!", "info");
			limparCampos();
		}
		
		public void onSelect$lbx_embarcaoAcontecimento(Event e){
				
			_embarcacaoAcontecimento = lbx_embarcaoAcontecimento.getSelectedItem().getValue();
			txb_desAcontecimento.setValue(_embarcacaoAcontecimento.getDescricao());
			dbx_dataOcorrencia.setValue(_embarcacaoAcontecimento.getDataOcorrencia());
			cbx_embarcacao.setValue(_embarcacaoAcontecimento.getEmbarcacao().getNome());
			cbx_acontecimento.setValue(_embarcacaoAcontecimento.getAcontecimento().getDescricao());
			cbx_paises.setValue(_embarcacaoAcontecimento.getPais().getDesignacao());
			
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
			
		}
		
		public void onSelect$lbx_embarcacoes(Event e){
			
			_embarcacao = lbx_embarcacoes.getSelectedItem().getValue();
			cbx_embarcacao.setValue(_embarcacao.getNome());
			
//			sbtn_actualizar.setVisible(true);
			btn_gravar.setVisible(true);
			
		}
		
		public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
		private void limparCampos() {
			txb_desAcontecimento.setRawValue(null);
			dbx_dataOcorrencia.setRawValue(null);
			cbx_embarcacao.setRawValue(null);
			cbx_acontecimento.setRawValue(null);
		    cbx_paises.setRawValue(null);
		    
	   		btn_gravar.setVisible(true);
			btn_actualizar.setVisible(false);
			
		}
		
		public void showNotifications(String message, String type) {
			Clients.showNotification(message, type, lbx_embarcaoAcontecimento,"before_center", 4000, true);
		}
		
		public void Test(){
System.out.println(listEmbarcacaoAcontecimento);
		}
		public static void main(String[] args){
			EmbarcaoAcontecimentoCtrl p=new EmbarcaoAcontecimentoCtrl();
		p.Test();
			
		}
		
		public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
	           mapaParam.put("imagemLogo", inputV);
	           mapaParam.put("listNome", "Lista de Acontecimentos");
	   		MasterRep.imprimir("/reportParam/reportAcontecimento.jrxml", listEmbarcacaoAcontecimento, mapaParam, win_regEmbarcaoAcontecimento);
	   	}
		
		
}
