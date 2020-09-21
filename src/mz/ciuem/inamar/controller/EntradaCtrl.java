package mz.ciuem.inamar.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.Entrada;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Pais;
import mz.ciuem.inamar.entity.Porto;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.service.EmbarcacaoService;
import mz.ciuem.inamar.service.EntradaService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.PaisService;
import mz.ciuem.inamar.service.PortoService;
import mz.ciuem.inamar.service.UserService;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

@SuppressWarnings({ "serial", "rawtypes" })
public class EntradaCtrl extends GenericForwardComposer{

	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	
	//Main Div
		private Textbox txb_nomefind;
		private Listbox lbx_entrada;
		private Listbox lbx_embarcacoes;
		private Combobox cbx_EmbarcaoFind;
		private Button btn_procurar;
		private Button btn_imprimir;
		private Textbox txb_embarcacaoFind;
		
		//Modal Div Entrada
		private Datebox dbx_dataEntrada;
		private Timebox tbx_horaEntrada;
		private Timebox tbx_livrePratico;
		private Combobox cbx_paises;
		private Combobox cbx_porto;
		private Combobox cbx_embarcacao;
		private Textbox txb_cais;
		private Textbox txb_agencia;
		private Textbox txb_calado;
		private Textbox txb_motivo;
		private Textbox txb_funServEntrada;
		private Textbox txb_portoEntrada;
		private Intbox ibx_nrOrdem;
		
		//Modal Div Saida
		
		private Datebox dbx_dataEntradaS;
		private Timebox tbx_horaEntradaS;
		private Timebox tbx_livrePraticoS;
		private Combobox cbx_paisesS;
		private Combobox cbx_portoS;
		
		
		private Combobox cbx_embarcacaoS;
		private Textbox txb_caisS;
		private Textbox txb_agenciaS;
		private Textbox txb_caladoS;
		private Textbox txb_motivoS;
		private Textbox txb_funServEntradaS;
		private Textbox txb_portoEntradaS;
		private Intbox ibx_nrOrdemS;
		
		private Datebox dbx_dataSaida;
		private Timebox tbx_horaSaida;
		private Combobox cbx_destino;
		private Textbox txb_funServSaida;
		
		//Modal Div Botoes
		private Button btn_gravar;
		private Button btn_gravarSaida;
		private Button btn_actualizar;
		private Button btn_actualizarSaida;
		private Button btn_cancelar;
		private Button btn_cancelarSaida;
		private Button btn_embarcacao;
		
		private Window win_regEntrada;
		
		
		Execution ex = Executions.getCurrent();
		
		private Entrada _entrada;
		
		private Funcionario funcionario=null;
		protected User loggeduser;
		protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		private Embarcacao _embarcacao;
		
		private Pais _pais;
		private Porto _porto;
		
		@WireVariable
		private EntradaService _entradaService;
		
		@WireVariable
		private FuncionarioService _funcionarioService;
		
		@WireVariable
		private UserService _userService;
		
		@WireVariable
		private EmbarcacaoService _embarcacaoService; 
		
		@WireVariable
		private PaisService _paisService;
		@WireVariable
		private PortoService _portoService;
		
		private List<Entrada> listEntrada = new ArrayList<>();
		
		private List<Embarcacao> listEmbarcacao = new ArrayList<Embarcacao>();
		
		private List<Pais> listPais = new ArrayList<Pais>();
		
		private List<Porto> listPorto = new ArrayList<Porto>();
		
		
		@SuppressWarnings("unchecked")
		@Override
		public void doBeforeComposeChildren(Component comp) throws Exception {
			super.doBeforeComposeChildren(comp);
			
			_entradaService = (EntradaService) SpringUtil.getBean("entradaService");
			_funcionarioService = (FuncionarioService) SpringUtil.getBean("funcionarioService");
			_userService = (UserService) SpringUtil.getBean("userService");
			_embarcacaoService = (EmbarcacaoService) SpringUtil.getBean("embarcacaoService");
			_portoService = (PortoService) SpringUtil.getBean("portoService");
			_paisService = (PaisService) SpringUtil.getBean("paisService");
			
			_embarcacao = (Embarcacao) ex.getArg().get("_embarcacao");
			_pais = (Pais) ex.getArg().get("_pais");
			_porto = (Porto) ex.getArg().get("_porto");
			
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
			listarPorto();
			listarPaises();
		}
		
		private void listar() {
			listEntrada =  _entradaService.getAll();
			lbx_entrada.setModel(new ListModelList<Entrada>(listEntrada));
		}
		
		private void listarEmbarcoesLbx(){
			listEmbarcacao = _embarcacaoService.getAll();
			lbx_embarcacoes.setModel(new ListModelList<Embarcacao>(listEmbarcacao));
		}
		
		private void listarEmbarcacao(){
			cbx_embarcacao.setModel(new ListModelList<Embarcacao>(_embarcacaoService.getAll()));
			cbx_embarcacaoS.setModel(new ListModelList<Embarcacao>(_embarcacaoService.getAll()));
		}
		private void listarPaises(){
			cbx_paises.setModel(new ListModelList<Pais>(_paisService.getAll()));
			cbx_destino.setModel(new ListModelList<Pais>(_paisService.getAll()));
			cbx_paisesS.setModel(new ListModelList<Pais>(_paisService.getAll()));
		}
		/*private void listarEmbarcacaoDestino(){
			cbx_embarcacaoDestino.setModel(new ListModelList<Embarcacao>(_embarcacaoService.getAll()));
		}*/
		
		private void listarPorto(){
			//cbx_paisesS.setModel(new ListModelList<Pais>(_paisService.getAll()));
		//	cbx_destino.setModel(new ListModelList<Pais>(_paisService.getAll()));
			cbx_porto.setModel(new ListModelList<Porto>(_portoService.getAll()));
			cbx_portoS.setModel(new ListModelList<Porto>(_portoService.getAll()));
			
		}
		
		/*private void listarPaisesDestino(){
			cbx_destino.setModel(new ListModelList<Pais>(_paisService.getAll()));
		}*/
		
		
//		 public void onClickprcurar(ForwardEvent e)  {
//	         Embarcacao embarcacao = cbx_EmbarcaoFind.getSelectedItem().getValue();
//	         findByBarcoProcedencia(embarcacao);
//			}

//		 public void findByBarcoProcedencia(Embarcacao embarcacao){
//		   		listEntrada = _entradaService.findByBarcoProcedencia(embarcacao);
//		   		lbx_entrada.setModel(new ListModelList<Entrada>(listEntrada));
//		   	}
		 
//		 private void preencherEmbarcacao(){
//				listEmbarcacao = _embarcacaoService.getAll();
//				cbx_embarcacao.setModel(new ListModelList<Embarcacao>(listEmbarcacao));
//				cbx_EmbarcaoFind.setModel(new ListModelList<Embarcacao>(listEmbarcacao));
//			}
		 
		 public void onClickprcurar(ForwardEvent e)  {
				String embarcacao = txb_embarcacaoFind.getValue();
		         findByNome(embarcacao);
		}
		 public void findByNome(String embarcacao){
		   		listEntrada = _entradaService.findByNome(embarcacao);
		   		lbx_entrada.setModel(new ListModelList<Entrada>(listEntrada));
		   	}
		 
		 public void onClickprcurarEmb(ForwardEvent e)  {
			 String embarcacao = txb_embarcacaoFind.getValue();
		         findByNome(embarcacao);
				}
		 public void findByNomeEmb(String embarcacao){
			 listEntrada = _entradaService.findByNomeEmb(embarcacao);
		   		lbx_embarcacoes.setModel(new ListModelList<Embarcacao>(listEmbarcacao));
		   	}
		
		 public void gravar(Delegacao del, User loggedUser){
			 Entrada ent = new Entrada();
				ent.setDelegacao(del);
				ent.setUserLoggado(loggedUser);
				ent.setAgencia(txb_agencia.getValue());
				ent.setCais(txb_cais.getValue());
				ent.setMotivo(txb_motivo.getValue());
				ent.setCalado(txb_calado.getValue());
			//	ent.setPortoEntrada(txb_portoEntrada.getValue());
				ent.setDataEntrada(dbx_dataEntrada.getValue());
				ent.setLivrePratico(tbx_livrePratico.getValue());
				ent.setHoraEntrada(tbx_horaEntrada.getValue());
				ent.setFunServEntrada(txb_funServEntrada.getValue());
				ent.setNrOrdem(ibx_nrOrdem.getValue());
				ent.setPorto((Porto)cbx_porto.getSelectedItem().getValue());
				ent.setPais((Pais)cbx_paises.getSelectedItem().getValue());
				
				ent.setEmbarcacao((Embarcacao)cbx_embarcacao.getSelectedItem().getValue());
				
				boolean existe = false;
				
				for(Entrada entrada: listEntrada){
					

		   			if (entrada.getNrOrdem()==(ent.getNrOrdem())){
		   				existe=true;
		   			}
				}
				
				if(existe==false){
					_entradaService.create(ent);
					listar();
					limparCampos();
					showNotifications("Entrada do Navio registada com sucesso!", "info");
				}
				
				else{
					showNotifications("O Nr de Ordem introduzido ja existe", "error");
				}
		 }
		 
		public void onClick$btn_gravar(Event e) throws InterruptedException{
			gravar(funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser);
		}
		
		public void actualizar(Delegacao del, User loggedUser){
			
			_entrada.setDelegacao(del);
			_entrada.setUserLoggado(loggedUser);
			_entrada.setNrOrdem(ibx_nrOrdemS.getValue());
			_entrada.setAgencia(txb_agenciaS.getValue());
			_entrada.setCais(txb_caisS.getValue());
			_entrada.setMotivo(txb_motivoS.getValue());
			_entrada.setCalado(txb_caladoS.getValue());
	//		_entrada.setPortoEntrada(txb_portoEntradaS.getValue());
			_entrada.setDataEntrada(dbx_dataEntradaS.getValue());
			_entrada.setHoraEntrada(tbx_horaEntradaS.getValue());
			_entrada.setFunServEntrada(txb_funServEntradaS.getValue());
			_entrada.setLivrePratico(tbx_livrePraticoS.getValue());
			_entrada.setNrOrdem(ibx_nrOrdemS.getValue());
			_entrada.setPais((Pais)cbx_paisesS.getSelectedItem().getValue());
			_entrada.setPorto((Porto)cbx_portoS.getSelectedItem().getValue());
			_entrada.setEmbarcacao((Embarcacao)cbx_embarcacaoS.getSelectedItem().getValue());

//			========================out==========================================
			
			_entrada.setDataSaida(dbx_dataSaida.getValue());
			_entrada.setHoraSaida(tbx_horaSaida.getValue());
			_entrada.setFunServSaida(txb_funServSaida.getValue());
			_entrada.setDestino((Pais)cbx_destino.getSelectedItem().getValue());
			
//			========================out==========================================			
			
				_entradaService.update(_entrada);
				listar();
				showNotifications("Entrada do Navio actualizada com sucesso!", "info");
				limparCampos();
		}
		
		public void onClick$btn_actualizar() throws InterruptedException {
			actualizar(funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser);
		}
		
		public void onSelect$lbx_entrada(Event e){
			_entrada = lbx_entrada.getSelectedItem().getValue();
			
			txb_agenciaS.setValue(_entrada.getAgencia());
			ibx_nrOrdemS.setValue(_entrada.getNrOrdem());
			txb_caisS.setValue(_entrada.getCais());
			txb_motivoS.setValue(_entrada.getMotivo());
			txb_caladoS.setValue(_entrada.getCalado());
			cbx_portoS.setValue(_entrada.getPorto().getDescricao());
			dbx_dataEntradaS.setValue(_entrada.getDataEntrada());
			dbx_dataSaida.setValue(_entrada.getDataSaida());
			tbx_horaEntradaS.setValue(_entrada.getHoraEntrada());
			tbx_horaSaida.setValue(_entrada.getHoraSaida());
			txb_funServEntradaS.setValue(_entrada.getFunServEntrada());
			txb_funServEntrada.setValue(_entrada.getFunServEntrada());
			txb_funServSaida.setValue(_entrada.getFunServSaida());
			tbx_livrePraticoS.setValue(_entrada.getLivrePratico());
			cbx_paisesS.setValue(_entrada.getPais().getDesignacao());
      //	cbx_porto.setValue(_entrada.getPorto().getDescricao());
			//cbx_destino.setValue(_entrada.getDestino().getDesignacao());
			cbx_embarcacaoS.setValue(_entrada.getEmbarcacao().getNome());
			
			btn_actualizar.setVisible(true);
		}
		
		public void onSelect$lbx_embarcacoes(Event e){
			
			_embarcacao = lbx_embarcacoes.getSelectedItem().getValue();
			cbx_embarcacao.setValue(_embarcacao.getNome());
			//cbx_embarcacaoDestino.setValue(_embarcacao.getNome());
			
			//btn_actualizar.setVisible(true);
			btn_gravar.setVisible(true);
			//btn_gravarSaida.setVisible(true);
			
		}
		
		public void onClick$btn_cancelar(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}
		
		/*public void onClick$btn_cancelarSaida(Event e) throws InterruptedException{

	   		limparCampos();
	   	
	   	}*/
		
		public void showNotifications(String message, String type) {
			Clients.showNotification(message, type, lbx_entrada,"before_center", 4000, true);
		}
		
		private void limparCampos() {
			ibx_nrOrdem.setRawValue(null);
			txb_agencia.setRawValue(null);
			txb_cais.setRawValue(null);
			txb_motivo.setRawValue(null);
			txb_calado.setRawValue(null);
			txb_funServEntrada.setRawValue(null);
			tbx_livrePratico.setRawValue(null);
			tbx_horaEntrada.setRawValue(null);
		//	txb_portoEntrada.setRawValue(null);
			//cbx_embarcacao.setRawValue(null);
			cbx_paises.setRawValue(null);
			cbx_porto.setRawValue(null);
		dbx_dataEntrada.setRawValue(null);
			
//	        habilitarCampos();
	   		btn_gravar.setVisible(true);
//	   		btn_gravarSaida.setVisible(true);
	   		
			btn_actualizar.setVisible(false);
	//		btn_actualizarSaida.setVisible(false);
			
		}
		
		public void onClick$btn_imprimir(Event e) throws JRException{
	   		
	   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
	           mapaParam.put("imagemLogo", inputV);
	           mapaParam.put("listNome", "Lista de Entradas");
	   		MasterRep.imprimir("/reportParam/reportEntradaa.jrxml", listEntrada, mapaParam, win_regEntrada);
	   	}
		
		public void onClickClose(ForwardEvent e){
			win_regEntrada.detach();
		}

		
		
		
}
