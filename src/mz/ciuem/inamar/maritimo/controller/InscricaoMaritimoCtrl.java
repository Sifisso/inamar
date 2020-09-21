package mz.ciuem.inamar.maritimo.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.Contagem;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.entity.Permission;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.ClasseMaritimoService;
import mz.ciuem.inamar.service.DepartamentoService;
import mz.ciuem.inamar.service.Grupo_MaritimoService;
import mz.ciuem.inamar.service.PeticaoCategoriaMaritimoService;
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
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class InscricaoMaritimoCtrl extends GenericForwardComposer{
	
	private Window win_inscricaoMaritima;
	private Div div_content_out, div_terminar, div_utente, div_secretario, myModal, div_dados;
	private Include inc_main;
	private Button btn_proximo, btn_imprimir, btn_terminar, btn_voltarUtente, btn_voltar, btn_validar, btn_recusar, btn_prevalidar;
	private Textbox tbx_user, tbx_pass, tbx_decscricao, tbx_observacao;
	private Combobox cbx_grupo, cbx_classe, cbx_categoria;
	
	@WireVariable
	private PeticaoMaritimoService _peticaoMaritimoService;
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
	@WireVariable
	private Grupo_MaritimoService _grupoMaritimoService;
	@WireVariable
	private ClasseMaritimoService _classeMaritimoService;
	@WireVariable
	private CategoriaMaritimoService _categoriaMaritimoService;
	
	private PeticaoMaritimo _peticaoMaritimo;
	
	private List<Contagem> listContagem = new ArrayList<Contagem>();
	
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_peticaoMaritimoService = (PeticaoMaritimoService) SpringUtil.getBean("peticaoMaritimoService");
		_peticaoCategoriaMaritimoService = (PeticaoCategoriaMaritimoService) SpringUtil.getBean("peticaoCategoriaMaritimoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_deService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		
		//Maritimo
		_grupoMaritimoService= (Grupo_MaritimoService) SpringUtil.getBean("grupo_MaritimoService");
		_classeMaritimoService = (ClasseMaritimoService) SpringUtil.getBean("classeMaritimoService");
		_categoriaMaritimoService = (CategoriaMaritimoService) SpringUtil.getBean("categoriaMaritimoService");
		
		_peticaoMaritimo = (PeticaoMaritimo) Executions.getCurrent().getSession().getAttribute("ss_peticaoMaritimo");
		//_peticaoMaritimo = _peticaoMaritimoService.find((long) 6);
		
		loggeduser = (User) Executions.getCurrent().getSession().getAttribute("ss_utilizador");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherGrupoMaritimo();
		preencherCampos();
		ocultarCampos();
		
	}
	
	private void preencherGrupoMaritimo() {
      cbx_grupo.setModel(new ListModelList<GrupoMaritimo>(_grupoMaritimoService.getAll()));		
	}
	
   public void onSelect$cbx_grupo(){
		cbx_classe.getItems().clear();
		cbx_categoria.getItems().clear();
		GrupoMaritimo gm = (GrupoMaritimo) cbx_grupo.getSelectedItem().getValue();
		cbx_classe.setModel(new ListModelList<ClasseMaritimo>(_classeMaritimoService.findByGrupoMaritimo(gm)));
	}
	
	             

	public void onSelect$cbx_classe(){
		cbx_categoria.getItems().clear();
		ClasseMaritimo cm = (ClasseMaritimo) cbx_classe.getSelectedItem().getValue();
		cbx_categoria.setModel(new ListModelList<CategoriaMaritimo>(_categoriaMaritimoService.findByClasseMaritimo(cm)));
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
	if(_peticaoMaritimo!=null){
		tbx_decscricao.setValue(_peticaoMaritimo.getDescricao());
		tbx_observacao.setValue(_peticaoMaritimo.getObservacao());
		cbx_categoria.setValue(_peticaoMaritimo.getCategoriamaritimo());
		cbx_classe.setValue(_peticaoMaritimo.getClassemaritimo());
		cbx_grupo.setValue(_peticaoMaritimo.getGrupomaritimo());
	}
	
}

public void onClick$btn_proximo(){
	if(_peticaoMaritimo!=null){
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
		if(_peticaoMaritimo!=null){
		gravar();
		}
	}

private void gravar() {
    _peticaoMaritimo.setDescricao(tbx_decscricao.getValue());
    _peticaoMaritimo.setObservacao(tbx_observacao.getValue());
    _peticaoMaritimo.setGrupomaritimo(cbx_grupo.getValue());
    _peticaoMaritimo.setClassemaritimo(cbx_classe.getValue());
    _peticaoMaritimo.setCategoriamaritimo(cbx_categoria.getValue());
    
    _peticaoMaritimoService.saveOrUpdate(_peticaoMaritimo);
       
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
		if(_peticaoMaritimo!=null && _peticaoMaritimo.getPeticao()!=null){
			Peticao pet= _peticaoMaritimo.getPeticao();
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
		if(_peticaoMaritimo!=null && _peticaoMaritimo.getPeticao()!=null){
			Peticao pet= _peticaoMaritimo.getPeticao();
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
		if(_peticaoMaritimo!=null && _peticaoMaritimo.getPeticao()!=null){
			Peticao pet= _peticaoMaritimo.getPeticao();
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
        Utente u = _utenteService.buscarUtenteByUser(_peticaoMaritimo.getUser());
        
        
        //Parametros
        //Fazer query
        mapaParam.put("pedido", ""+_peticaoMaritimo.getPedido().getDescricao());
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
        Peticao p =_peticaoMaritimo.getPeticao();
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
        mapaParam.put("hora", ""+_peticaoMaritimo.getCreated().getHours());
        
       List<PeticaoMaritimoTaxaPedido> _list = _peticaoMaritimoTaxaPedidoService.findByPeticaoMaritimo(_peticaoMaritimo);
   	   MasterRep.imprimir("/reportParam/facturaPedidoUtente.jrxml", _list, mapaParam, win_inscricaoMaritima);
	
}

public void showNotifications(String message, String type) {
		Clients.showNotification(message, type, btn_terminar,"before_center", 4000, true);
	}

	

}
