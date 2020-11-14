package mz.ciuem.inamar.embarcacao.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoCategoriaMaritimo;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.PeticaoCategoriaMaritimoService;
import mz.ciuem.inamar.service.PeticaoEmbarcacaoService;
import mz.ciuem.inamar.service.PeticaoMaritimoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.service.UtenteService;
import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class EmbarcacaoGeral extends GenericForwardComposer{
	
	private Window win_emissaoCedula;
	private Div div_content_out, div_terminar, div_utente, div_secretario, myModal, div_dados;
	private Include inc_main;
	private Button btn_proximo, btn_imprimir, btn_terminar, btn_voltarUtente, btn_voltar, btn_validar, btn_recusar, btn_prevalidar;
	private Textbox tbx_user, tbx_pass, tbx_decscricao, tbx_observacao;
	
	@WireVariable
	private PeticaoEmbarcacaoService _peticaoEmbarcacaoService;
	@WireVariable
	private CategoriaMaritimoService _categoriaMaritimoService;
	@WireVariable
	private PeticaoCategoriaMaritimoService _peticaoCategoriaMaritimoService; 
	@WireVariable
	private UserService _userService;
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private DepartamentoService _deService;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private PeticaoService _peticaoService;
	
	private PeticaoEmbarcacao _peticaoEmbarcacao;
	
	private List<Contagem> listContagem = new ArrayList<Contagem>();
	
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_peticaoEmbarcacaoService = (PeticaoEmbarcacaoService) SpringUtil.getBean("peticaoEmbarcacaoService");
		_categoriaMaritimoService = (CategoriaMaritimoService) SpringUtil.getBean("categoriaMaritimoService");
		_peticaoCategoriaMaritimoService = (PeticaoCategoriaMaritimoService) SpringUtil.getBean("peticaoCategoriaMaritimoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_deService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_peticaoEmbarcacao = (PeticaoEmbarcacao) Executions.getCurrent().getSession().getAttribute("p");
		//_peticaoMaritimo = _peticaoMaritimoService.find((long) 6);
		
		loggeduser = (User) Executions.getCurrent().getSession().getAttribute("ss_utilizador");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos();
		ocultarCampos();
	}
	
	   private void ocultarCampos() {
			if(loggeduser!=null){
				Set<Permission> list = loggeduser.getPermissions();
				for (Permission permission : list) {
					if(permission.getPermissionname().equals("SECRETARIA")){
						btn_prevalidar.setVisible(true);
						btn_validar.setVisible(false);
					}
				}
			}
			
		}

	private void preencherCampos() {
		if(_peticaoEmbarcacao!=null){
			tbx_decscricao.setValue(_peticaoEmbarcacao.getDescricao());
			tbx_observacao.setValue(_peticaoEmbarcacao.getObservacao());
		}
		
	}

	public void onClick$btn_proximo(){
		if(_peticaoEmbarcacao!=null){
			gravar();	
		}
	}
    
	public void onClick$btn_verFactura() throws JRException{
		verFactura();
	}
	
	//REfazer
	public void onClick$btn_terminar() throws JRException{
		verFactura();
	}
	
  	public void onClick$btn_proximo(Event e) throws JRException{
  		if(_peticaoEmbarcacao!=null){
			gravar();
  		}
   	}
  	
  	
  	public void onClick$btn_finalizar(Event e) throws JRException{
  		if(_peticaoEmbarcacao!=null){
			gravar();
  		}
   	}
  	
  	
  	public void onClick$btn_voltar(){
		if(_peticaoEmbarcacao!=null){
			gravar();	
		}else{
			alert("Ocorreu um erro "+null);
		}
	}
  	
	
	private void gravar() {
		_peticaoEmbarcacao.setDescricao(tbx_decscricao.getValue());
		_peticaoEmbarcacao.setObservacao(tbx_observacao.getValue());
	    _peticaoEmbarcacaoService.saveOrUpdate(_peticaoEmbarcacao);
	       
       visibilidades();
	}
	
	
	   private void visibilidades() {
		//   div_terminar.setVisible(true);
		   div_dados.setVisible(false);
		   //btn_proximo.setVisible(false);
		   myModal.setVisible(true);
		   
		   //Utente
		   if(loggeduser.getUtente()!=null){
			   div_secretario.setVisible(false);
			   div_utente.setVisible(true);
			   btn_voltarUtente.setVisible(true);
			   btn_voltar.setVisible(false);
			   btn_validar.setVisible(false);
			   btn_recusar.setVisible(false);
			   tbx_user.setValue(loggeduser.getUsername());
			   tbx_pass.setValue("**************");
		   }
		   //Secretario
		   else{
			   div_secretario.setVisible(true);
			   div_utente.setVisible(false);
			   btn_voltarUtente.setVisible(false);
			   btn_voltar.setVisible(true);
			   btn_validar.setVisible(true);
			   btn_recusar.setVisible(true);
			   
		   }
		   ocultarCampos();
	}
	   
		public void onClick$btn_recusar(){
			if(_peticaoEmbarcacao!=null && _peticaoEmbarcacao.getPeticao()!=null){
				Peticao pet= _peticaoEmbarcacao.getPeticao();
				pet.setValidado(false);
				pet.setAdmMaritima(true);
				pet.setRecusado(true);
				_peticaoService.update(pet);
				btn_validar.setVisible(true);
				btn_recusar.setVisible(false);
				showNotifications("Petição Recusada com sucesso.", "error");
				ocultarCampos();
			}
		}
	   
		public void onClick$btn_prevalidar(){
			if(_peticaoEmbarcacao!=null && _peticaoEmbarcacao.getPeticao()!=null){
				Peticao pet= _peticaoEmbarcacao.getPeticao();
				pet.setPreValidado(true);
				pet.setLocalizacao("Chefe da Secretaria");
				_peticaoService.update(pet);
				btn_validar.setVisible(false);
				btn_prevalidar.setVisible(false);
				btn_recusar.setVisible(true);
				showNotifications("Petição pré-validada com sucesso.", "info");
			}
		}
		
		public void onClick$btn_validar(){
			if(_peticaoEmbarcacao!=null && _peticaoEmbarcacao.getPeticao()!=null){
				Peticao pet= _peticaoEmbarcacao.getPeticao();
				pet.setValidado(true);
				pet.setAdmMaritima(true);
				pet.setRecusado(false);
				_peticaoService.update(pet);
				btn_validar.setVisible(false);
				btn_recusar.setVisible(true);
				showNotifications("Petição Validada com sucesso.", "info");
			}
		}

	private void verFactura() throws JRException {
		   Map<String, Object> mapaParam = new HashMap<String, Object>();	
	   		final Execution ex = Executions.getCurrent();
	   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/u4.jpg");       
	        mapaParam.put("imagemLogo", inputV);
	        Utente u = _utenteService.buscarUtenteByUser(_peticaoEmbarcacao.getUser());
	        
	        
	        //Parametros
	        //Fazer query
	        mapaParam.put("pedido", ""+_peticaoEmbarcacao.getPedido().getDescricao());
	        mapaParam.put("nome", ""+u.getNome()+" "+u.getApelido());
	        mapaParam.put("nomePai", ""+u.getNomePai());
	        mapaParam.put("nomeMae", ""+u.getNomeMae());
	        
	        int idade = new Date().getYear()-u.getDataNascimento().getYear();
	        mapaParam.put("idade", ""+idade);
	        mapaParam.put("tipoDocumento", ""+u.getTipoDocumento());
	        mapaParam.put("nrDocumento", ""+u.getNumeroDocumento());
	        //Nao existe
	        mapaParam.put("dataEmissaoDocumento", "22/10/2014");
	        mapaParam.put("bairro", ""+u.getBairro());
	        mapaParam.put("quarteirao", ""+u.getQuarteirao());
	        mapaParam.put("nrCasa", ""+u.getNrCasa());
	        mapaParam.put("nrTelefone", ""+u.getCelular());
	        //Nao existe
	        mapaParam.put("tipoUtente", "Empresa");
	        Peticao p =_peticaoEmbarcacao.getPeticao();
	        //Nao existe
	        mapaParam.put("paticionarioNr", p.getNrExpediente());
	        //Nso existe
	        mapaParam.put("codigoArea", "02");
	        //Nao Existe
	        mapaParam.put("codigoSubArea", "04");
	        //Nao Existe
	        mapaParam.put("nrFactura", p.getNrFactura());
	        mapaParam.put("referencia", p.getReferencia());
	        mapaParam.put("subToal",+p.getValor());
	        double iva = p.getValor()+0.17;
	        double valorTotal = p.getValor()+iva;
	        mapaParam.put("valor_pagar",valorTotal);
	        mapaParam.put("iva_valor", iva);
	        mapaParam.put("entidade",""+ p.getEntidade());
	        
	        mapaParam.put("nrExpediente", p.getNrExpediente());
	        mapaParam.put("hora", ""+_peticaoEmbarcacao.getCreated().getHours());
	        
	       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoEmbarcacao(_peticaoEmbarcacao);
	   	   MasterRep.imprimir("/reportParam/facturaPedidoUtente.jrxml", _list, mapaParam, win_emissaoCedula);
		
	}

	public void showNotifications(String message, String type) {
   		Clients.showNotification(message, type, btn_terminar,"before_center", 4000, true);
   	}

}
