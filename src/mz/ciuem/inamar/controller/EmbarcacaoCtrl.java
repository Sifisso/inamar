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
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Acontecimento;
import mz.ciuem.inamar.entity.ActividadeZonaMaritima;
import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.ClasseEmbarcacao;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Entrada;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.service.ActividadeZonaMaritimaService;
import mz.ciuem.inamar.service.ClasseEmbarcacaoService;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.EmbarcacaoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.PaisService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.ServicoDestinoService;
import mz.ciuem.inamar.service.UserService;

@SuppressWarnings({ "serial", "rawtypes" })
public class EmbarcacaoCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	private Listbox lbx_embarcacoes;
	private Textbox txb_nomefind;
	private Radio rbx_Simfin;
	private Radio rbx_Naofind;
	private Radio rbx_SimNavioEmbarcacaoFind;
	private Radio rbx_NaoNavioEmbarcacaoFind;
	private Combobox cbx_paisFind;
	private Button btn_procurar;
	private Button btn_imprimir;
	
	//Modal Div
	private Textbox txb_nome;
	private Textbox txb_codigo;
	private Textbox txb_propreitario;
	private Textbox txb_matricula;
	private Textbox txb_tonelagem;
	private Datebox dbx_dataFabrico;
	private Combobox cbx_paises;
	private Combobox cbx_classeEmbarcacao;
	private Combobox cbx_actividadeZonaMaritima;
	private Combobox cbx_servicoDestino;
	
	private Radio rbx_actSimA;
	private Radio rbx_actNaoA;
	
	private Radio rbx_navio;
	private Radio rbx_embarcacao;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_cancelar;
	
	
	private Window win_regEmbarcacao;
	
	Execution ex = Executions.getCurrent();
	
	private Embarcacao _embarcacao;
	private Funcionario funcionario=null;
	protected User loggeduser;
	private Pais _pais;
	private ClasseEmbarcacao _classeEmbarcacao;
	private ActividadeZonaMaritima _actividadeZonaMaritima;
	private ServicoDestino _servicoDestino;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	

	@WireVariable
	private EmbarcacaoService  _embarcacaoService;
	
	@WireVariable
	private FuncionarioService _funcionarioService;
	
	@WireVariable
	private UserService _userService;
	
	@WireVariable
	private PaisService _paisService;
	
	@WireVariable
	private ClasseEmbarcacaoService _classeEmbarcacaoService; 
	
	@WireVariable
	private ActividadeZonaMaritimaService _actividadeZonaMaritimaService; 
	
	@WireVariable
	private ServicoDestinoService _servicoDestinoService;
	
	private List<Embarcacao> listaDeEmbarcacoes = new ArrayList<Embarcacao>();
	
	private List<Pais> listDePaises = new ArrayList<Pais>();
	
	private List<ClasseEmbarcacao> listaDeClasseEmbarcacao = new ArrayList<ClasseEmbarcacao>();
	
	private List<ActividadeZonaMaritima> listaDeActividadeZonaMaritima = new ArrayList<ActividadeZonaMaritima>();
	
	private List<ServicoDestino> listServicoDestinos = new ArrayList<ServicoDestino>();
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_embarcacaoService = (EmbarcacaoService) SpringUtil.getBean("embarcacaoService");
		_funcionarioService = (FuncionarioService) SpringUtil.getBean("funcionarioService");
		_userService = (UserService) SpringUtil.getBean("userService");
		_paisService= (PaisService) SpringUtil.getBean("paisService");
		_classeEmbarcacaoService = (ClasseEmbarcacaoService) SpringUtil.getBean("classeEmbarcacaoService");
		_actividadeZonaMaritimaService = (ActividadeZonaMaritimaService) SpringUtil.getBean("actividadeZonaMaritimaService");
		_servicoDestinoService = (ServicoDestinoService) SpringUtil.getBean("servicoDestinoService");
		
		_pais =  (Pais) ex.getArg().get("_pais");
		_classeEmbarcacao =  (ClasseEmbarcacao) ex.getArg().get("_classeEmbarcacao");
		_actividadeZonaMaritima = (ActividadeZonaMaritima) ex.getArg().get("_actividadeZonaMaritima");
		_servicoDestino = (ServicoDestino) ex.getArg().get("_servicoDestino");
		
		loggeduser = _userService.getUser(authentication.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		listarPaises();
		if(loggeduser.getFuncionario() != null){
			funcionario = _funcionarioService.buscarDadosDoFuncionario(loggeduser.getFuncionario());
		}
		listar();
		
//		listarClassesEmbarcacoes();
//		listarZonaActividadeMaritima();
//		listarServicoDestino();
//		preencherPais();
	}
	
	private void listar() {
		listaDeEmbarcacoes =  _embarcacaoService.getAll();
		lbx_embarcacoes.setModel(new ListModelList<Embarcacao>(listaDeEmbarcacoes));
	}
	
	private void listarPaises(){
		cbx_paises.setModel(new ListModelList<Pais>(_paisService.getAll()));
	}
	
	
//	private void listarClassesEmbarcacoes(){
//		cbx_classeEmbarcacao.setModel(new ListModelList<ClasseEmbarcacao>(_classeEmbarcacaoService.getAll()));
//	}
	
//	private void listarZonaActividadeMaritima(){
//		cbx_actividadeZonaMaritima.setModel(new ListModelList<ActividadeZonaMaritima>(_actividadeZonaMaritimaService.getAll()));
//	}
	
//	private void listarServicoDestino(){
//		cbx_servicoDestino.setModel(new ListModelList<ServicoDestino>(_servicoDestinoService.getAll()));
//	}
	
	 public void onClickprcurar(ForwardEvent e)  {
         String nome = txb_nomefind.getValue();
         boolean isActivo = (rbx_Naofind.isChecked() ? false : true);
//         Pais nacionalidade = cbx_paisFind.getSelectedItem().getValue();
         findByNomeActivo(nome, isActivo);
		}

	 public void findByNomeActivo(String nome, boolean isActivo){
	   		listaDeEmbarcacoes = _embarcacaoService.findByNomeActivo(nome, isActivo);
	   		lbx_embarcacoes.setModel(new ListModelList<Embarcacao>(listaDeEmbarcacoes));
	   	}
	 
	 public void onClickprcurarTodos(ForwardEvent e)  {
         listar();
		}
	 
	 private void gravar(Delegacao del, User loggedUser){
		 
		 Embarcacao emb = new Embarcacao();
		 	emb.setDelegacao(del);
		 	emb.setUserLoggado(loggedUser);
		 	emb.setTonelagem(txb_tonelagem.getValue());
		 	emb.setMatricula(txb_matricula.getValue());
		 	emb.setActivo(rbx_actSimA.isChecked()? true : false);
			emb.setPropreitario(txb_propreitario.getValue());
			emb.setNome(txb_nome.getValue());
			emb.setNacionalidade((Pais)cbx_paises.getSelectedItem().getValue());
			
			_embarcacaoService.create(emb);
			listar();
			limparCampos();
			showNotifications("Barco registado com sucesso!", "info");
	 }
	 
	public void onClick$btn_gravar(Event e) throws InterruptedException{
		gravar(funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser);
	}
	
	public void actualizar(Delegacao del, User loggedUser){
		_embarcacao.setDelegacao(del);
		_embarcacao.setUserLoggado(loggedUser);
		_embarcacao.setMatricula(txb_matricula.getValue());
		_embarcacao.setTonelagem(txb_tonelagem.getValue());
		_embarcacao.setActivo(rbx_actSimA.isChecked() ? true : false);
		_embarcacao.setNome(txb_nome.getValue());
		_embarcacao.setPropreitario(txb_propreitario.getValue());
		_embarcacao.setNacionalidade((Pais)cbx_paises.getSelectedItem().getValue());
		
		_embarcacaoService.update(_embarcacao);
		listar();
		showNotifications("Barco actualizado com sucesso!", "info");
		limparCampos();
	}
	
	public void onClick$btn_actualizar() throws InterruptedException {
		actualizar(funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser);
	}
	
	public void onSelect$lbx_embarcacoes(Event e){
		_embarcacao = lbx_embarcacoes.getSelectedItem().getValue();
		rbx_actSimA.setChecked(_embarcacao.isActivo());
		txb_nome.setValue(_embarcacao.getNome());
		txb_propreitario.setValue(_embarcacao.getPropreitario());
		cbx_paises.setValue(_embarcacao.getNacionalidade().getDesignacao());
	    txb_matricula.setValue(_embarcacao.getMatricula());
	    txb_tonelagem.setValue(_embarcacao.getTonelagem());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
		
	}
	public void onClick$btn_cancelar(Event e) throws InterruptedException{

   		limparCampos();
   	
   	}
	
	private void limparCampos() {
		rbx_actSimA.setChecked(false);
	    rbx_actNaoA.setChecked(true);
		txb_nome.setRawValue(null);
		txb_propreitario.setRawValue(null);
	    cbx_paises.setRawValue(null);
	    txb_matricula.setRawValue(null);
	    txb_tonelagem.setRawValue(null);
        habilitarCampos();
   		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		
	}
	
	private void habilitarCampos() {
		txb_nome.setDisabled(false);
		txb_propreitario.setDisabled(false);
		cbx_paises.setDisabled(false);
	}
	
	public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, lbx_embarcacoes,"before_center", 4000, true);
	}
	
public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Navios");
   		MasterRep.imprimir("/reportParam/reportEmbarcacao.jrxml", listaDeEmbarcacoes, mapaParam, win_regEmbarcacao);
   	}


}
