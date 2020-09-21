package mz.ciuem.inamar.embarcacao.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoEmbarcacao;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.Provincia;
import mz.ciuem.inamar.entity.ServicoDestino;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.ClasseEmbarcacaoService;
import mz.ciuem.inamar.service.PeticaoEmbarcacaoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.ProvinciaService;
import mz.ciuem.inamar.service.ServicoDestinoService;
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

public class EmissaoTituloPropriedadeCtrl extends GenericForwardComposer{
	
	private Window win_titulo;
	private Div div_content_out, div_terminar, div_utente, div_secretario, myModal, div_dados;
	private Include inc_main;
	private Combobox cbx_local;
	private Button btn_proximo, btn_imprimir, btn_terminar, btn_voltarUtente, btn_voltar, btn_validar, btn_recusar, btn_prevalidar;
	private Textbox tbx_denominacao,tbx_nr_registo;
	
	@WireVariable
	private PeticaoEmbarcacaoService _peticaoEmbarcacaoService;
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private ClasseEmbarcacaoService _classeEmbarcacaoService;
	@WireVariable
	private ServicoDestinoService _servicoDestinoServico;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private UtenteService _utenteService;
	@WireVariable
	private ProvinciaService _provinciaService;
	
	private PeticaoEmbarcacao _peticaoEmbarcacao;
	
	private List<Contagem> listContagem = new ArrayList<Contagem>();
	
	private Peticao p;
	
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_peticaoEmbarcacaoService = (PeticaoEmbarcacaoService) SpringUtil.getBean("peticaoEmbarcacaoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_classeEmbarcacaoService = (ClasseEmbarcacaoService) SpringUtil.getBean("classeEmbarcacaoService");
		_servicoDestinoServico = (ServicoDestinoService) SpringUtil.getBean("servicoDestinoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_provinciaService = (ProvinciaService) SpringUtil.getBean("provinciaService");
		
		p =  (Peticao) Executions.getCurrent().getSession().getAttribute("p");
		
		loggeduser = (User) Executions.getCurrent().getSession().getAttribute("ss_utilizador");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		pegarPeticaoEmbarcacao();
		peencherLocal();
		preencherCampos();
	}
	
	
	private void peencherLocal() {
		cbx_local.setModel(new ListModelList<Provincia>(_provinciaService.getAll()));
	  }

	private void pegarPeticaoEmbarcacao() {
			if(p!=null){
				_peticaoEmbarcacao=p.getPeticaoEmbarcacao();
			}
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
		  		}else{
		  			alert("Ocorreu um erro "+null);
		  		}
		   	}
		  	
		  	public void onClick$btn_voltar(){
				if(_peticaoEmbarcacao!=null){
					gravar();	
				}else{
					alert(""+null);
				}
			}
			
			private void gravar() {
				_peticaoEmbarcacao.setLocal_registo(cbx_local.getValue());
				_peticaoEmbarcacao.setNumero_registo(tbx_nr_registo.getValue());
				_peticaoEmbarcacao.setDenominacao(tbx_denominacao.getValue());
		        _peticaoEmbarcacaoService.saveOrUpdate(_peticaoEmbarcacao);
		       //visibilidades();
			}
			
			
			   private void visibilidades() {
				//   div_terminar.setVisible(true);
				   div_dados.setVisible(false);
				   btn_proximo.setVisible(false);
				   myModal.setVisible(true);
				   
				   //Utente
				   if(loggeduser.getUtente()!=null){
					   div_secretario.setVisible(false);
					   div_utente.setVisible(true);
					   btn_voltarUtente.setVisible(true);
					   btn_voltar.setVisible(false);
					   btn_validar.setVisible(false);
					   btn_recusar.setVisible(false);
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
			        
			        //int idade = new Date().getYear()-u.getDataNascimento().getYear();
			        mapaParam.put("idade", ""+0);
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
			        //Nao existe
			        Peticao p =_peticaoEmbarcacao.getPeticao();
			        mapaParam.put("paticionarioNr", p.getNrExpediente());
			        //Nso existe
			        mapaParam.put("codigoArea", "02");
			        //Nao Existe
			        mapaParam.put("codigoSubArea", "04");
			        //Nao Existe
			        mapaParam.put("nrFactura", p.getNrFactura());
			        mapaParam.put("referencia", p.getReferencia());
			        mapaParam.put("subToal",p.getValor());
			        double iva = p.getValor()+0.17;
			        double valorTotal = p.getValor()+iva;
			        mapaParam.put("valor_pagar",valorTotal);
			        mapaParam.put("iva_valor", iva);
			        mapaParam.put("entidade",""+ p.getEntidade());
			        
			        mapaParam.put("nrExpediente", p.getNrExpediente());
			        mapaParam.put("hora", ""+_peticaoEmbarcacao.getCreated().getHours());
			        
			        
			       //Factura Utente
			       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoEmbarcacao(_peticaoEmbarcacao);
			   	   MasterRep.imprimir("/reportParam/facturaPedidoUtente.jrxml", _list, mapaParam, win_titulo);
			   	   
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
				win_titulo.detach();
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
			
			public void onClick$btn_recusar(){
				if(_peticaoEmbarcacao!=null && _peticaoEmbarcacao.getPeticao()!=null){
					Peticao pet= _peticaoEmbarcacao.getPeticao();
					pet.setValidado(false);
					pet.setRecusado(true);
					_peticaoService.update(pet);
					btn_validar.setVisible(true);
					btn_recusar.setVisible(false);
					showNotifications("Petição Recusada com sucesso.", "error");
					ocultarCampos();
				}
			}
			
			private void preencherCampos() {
				if(_peticaoEmbarcacao!=null && _peticaoEmbarcacao.getLocal_registo()!=null){
				    _peticaoEmbarcacao = _peticaoEmbarcacaoService.findOneWithEager(_peticaoEmbarcacao.getId());
				    tbx_denominacao.setValue(_peticaoEmbarcacao.getDenominacao());
				    tbx_nr_registo.setValue(_peticaoEmbarcacao.getNumero_registo());
					cbx_local.setValue(_peticaoEmbarcacao.getLocal_registo());
				}
			}

			
			public void showNotifications(String message, String type) {
		   		Clients.showNotification(message, type, btn_terminar,"before_center", 4000, true);
		   	}
			

}
