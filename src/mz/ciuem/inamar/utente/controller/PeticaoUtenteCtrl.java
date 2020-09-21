package mz.ciuem.inamar.utente.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mz.ciuem.inamar.entity.Area;
import mz.ciuem.inamar.entity.EtapaFluxo;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.PedidoEtapa;
import mz.ciuem.inamar.entity.PedidoRequisito;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.PeticaoMaritimo;
import mz.ciuem.inamar.entity.PeticaoMaritimoTaxaPedido;
import mz.ciuem.inamar.entity.PeticaoPedidoRequisito;
import mz.ciuem.inamar.entity.SubArea;
import mz.ciuem.inamar.entity.TarefaNaEtapa;
import mz.ciuem.inamar.entity.TaxaPedido;
import mz.ciuem.inamar.entity.TipoPedido;
import mz.ciuem.inamar.entity.User;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.EtapaFluxoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.PedidoEtapaService;
import mz.ciuem.inamar.service.PedidoRequisitoService;
import mz.ciuem.inamar.service.PedidoService;
import mz.ciuem.inamar.service.PeticaoMaritimoService;
import mz.ciuem.inamar.service.PeticaoMaritimoTaxaPedidoService;
import mz.ciuem.inamar.service.PeticaoPedidoRequisitoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.SubAreaService;
import mz.ciuem.inamar.service.TarefaNaEtapaService;
import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.service.TipoPedidoService;
import mz.ciuem.inamar.service.UserService;
import mz.ciuem.inamar.util.Gerador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

public class PeticaoUtenteCtrl extends GenericForwardComposer{
	
	private Div div_compor,div_entrada, div_content_out;
	private Combobox cbx_area,cbx_tipoPedido,cbx_pedido;
	private Listbox lbx_requisitos, lbx_taxasPedido, lbx_instLegalPedido, lbx_peticao, lbx_etapaFluxo;
	private Button btn_proximo;
	private Include inc_main;
	
	@WireVariable
	private AreaService _areaService;
	@WireVariable
	private FuncionarioService _funcionarioService;
	@WireVariable
	private SubAreaService _subAreaService;
	@WireVariable
	private TipoPedidoService _tipoPedidoService;
	@WireVariable
	private PedidoService _pedidoService;
	@WireVariable 
	private PedidoRequisitoService _pedidoRequisitoService;
	@WireVariable
	private PedidoEtapaService _pedidoIntrumentoLegalService;
	@WireVariable
	private TaxaPedidoService _taxaPedidoService;
	@WireVariable
	private TarefaNaEtapaService _tarefaEtapaService;
	@WireVariable
	private PeticaoPedidoRequisitoService _peticaoPedidoRequisitoService;
	@WireVariable
	private UserService _userService;
	@WireVariable
	private PeticaoMaritimoService _peticaoMaritimoService;
	@WireVariable
	private PeticaoMaritimoTaxaPedidoService _peticaoMaritimoTaxaPedidoService;
	@WireVariable
	private PeticaoService _peticaoService;
	@WireVariable
	private EtapaFluxoService _etapaFluxoService;
	
	Peticao peticao;
	private Funcionario funcionario=null;
	protected User loggeduser;
	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
	Gerador gerador = new Gerador();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		_areaService = (AreaService) SpringUtil.getBean("areaService");
		_funcionarioService = (FuncionarioService) SpringUtil.getBean("funcionarioService");
		_tipoPedidoService = (TipoPedidoService) SpringUtil.getBean("tipoPedidoService");
		_pedidoService = (PedidoService) SpringUtil.getBean("pedidoService");
		_pedidoRequisitoService = (PedidoRequisitoService) SpringUtil.getBean("pedidoRequisitoService");
		_pedidoIntrumentoLegalService = (PedidoEtapaService) SpringUtil.getBean("pedidoEtapaService");
		_taxaPedidoService = (TaxaPedidoService) SpringUtil.getBean("taxaPedidoService");
		_tarefaEtapaService = (TarefaNaEtapaService) SpringUtil.getBean("tarefaNaEtapaService");
		_subAreaService = (SubAreaService) SpringUtil.getBean("subAreaService");
		_peticaoPedidoRequisitoService = (PeticaoPedidoRequisitoService) SpringUtil.getBean("peticaoPedidoRequisitoService");
		_userService = (UserService) SpringUtil.getBean("userService");
		_peticaoMaritimoService = (PeticaoMaritimoService) SpringUtil.getBean("peticaoMaritimoService");
		_peticaoMaritimoTaxaPedidoService = (PeticaoMaritimoTaxaPedidoService) SpringUtil.getBean("peticaoMaritimoTaxaPedidoService");
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_etapaFluxoService = (EtapaFluxoService) SpringUtil.getBean("etapaFluxoService");

		
		loggeduser = _userService.getUser(authentication.getName());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listarArea();
		if(loggeduser.getFuncionario() != null){
			funcionario = _funcionarioService.buscarDadosDoFuncionario(loggeduser.getFuncionario());
		}
		listarPeticao();
	}
	
	private void listarPeticao() {
		// TODO Auto-generated method stub
		//Buscar do user logado
		List<Peticao> listPeticao = _peticaoService.getbyUser(loggeduser);
		lbx_peticao.setModel(new ListModelList<Peticao>(listPeticao));
	}
	
	public void onSelect$cbx_area(){
		cbx_pedido.setRawValue(null);
		cbx_tipoPedido.setRawValue(null);
		cbx_pedido.getItems().clear();
		cbx_tipoPedido.getItems().clear();
		List<TipoPedido> listTP = _tipoPedidoService.findByArea((Area)cbx_area.getSelectedItem().getValue());
		cbx_tipoPedido.setModel(new ListModelList<TipoPedido>(listTP));
		
		/*cbx_subArea.setRawValue(null);
		cbx_subArea.getItems().clear();
		List<SubArea> listSubArea = _subAreaService.findByArea((Area)cbx_area.getSelectedItem().getValue());
		cbx_subArea.setModel(new ListModelList<SubArea>(listSubArea));*/
	}
	
	public void onSelect$cbx_tipoPedido(){
		cbx_pedido.getItems().clear();
		List<Pedido> listP = _pedidoService.findByTipoPedido((TipoPedido) cbx_tipoPedido.getSelectedItem().getValue());
		cbx_pedido.setModel(new ListModelList<Pedido>(listP));;
	}
	
	public void onSelect$cbx_pedido(){
		Pedido pedido = (Pedido) cbx_pedido.getSelectedItem().getValue();
		List<PedidoRequisito> _listPR = _pedidoRequisitoService.findByPedidoVisivelParaUtente(pedido);
		lbx_requisitos.setModel(new ListModelList<PedidoRequisito>(_listPR));
		
		//_pedidoIntrumentoLegalService
		List<PedidoEtapa> listPE = _pedidoIntrumentoLegalService.findByPedido(pedido);
	    lbx_instLegalPedido.setModel(new ListModelList<PedidoEtapa>(listPE));
		
		//_taxaPedidoService
	    List<TaxaPedido> listTP = _taxaPedidoService.findByPedido(pedido);
	    lbx_taxasPedido.setModel(new ListModelList<TaxaPedido>(listTP));
		
		//_tarefaEtapaService
	    List<EtapaFluxo> listTE = _etapaFluxoService.findByFluxo(pedido.getFluxo());
	    lbx_etapaFluxo.setModel(new ListModelList<EtapaFluxo>(listTE));
	}
	
	public void onClickCompor(final ForwardEvent e){
		if(div_compor.isVisible()){
			div_compor.setVisible(false);
			div_entrada.setVisible(true);
		}else{
			div_entrada.setVisible(false);
			div_compor.setVisible(true);
		}
	}
	
	public void onClick$btn_proximo(){
		gravar();
	}
	
	private void gravar() {
		Utente utente = null;
		if(loggeduser.getUtente()!=null){
			 utente =loggeduser.getUtente();
		}
		_peticaoService.gravarRedicionar((Pedido)cbx_pedido.getSelectedItem().getValue(), utente,
				funcionario.getSector().getDelegacaoDepartamento().getDelegacao(), loggeduser, lbx_requisitos,lbx_instLegalPedido,lbx_taxasPedido,lbx_etapaFluxo , inc_main, div_content_out);

	}
	
	public void onClickDetalhes(ForwardEvent e){
		Peticao pet = (Peticao) e.getData();
		if(pet.getTipo().equals("1")){
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/emissaoCedulaMaritima.zul");	
		} 
			//Averbamento de cedulas maritimas
			if(pet.getTipo().equals("3")){
				PeticaoMaritimo petM = pet.getPeticaoMaritimo();
				Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
	    		div_content_out.detach();
	    		inc_main.setSrc("/views/Maritimo/averbamentoCedulaMaritima.zul");
			}else{
			PeticaoMaritimo petM = pet.getPeticaoMaritimo();
			Executions.getCurrent().getSession().setAttribute("ss_peticaoMaritimo", petM);
    		div_content_out.detach();
    		inc_main.setSrc("/views/Maritimo/peticaoGeralEmpty.zul");
		}
	}
	
	public void gerarPedidoRequisitos(List<Listitem> listI, Peticao pm){
		for (Listitem listitem : listI) {
			PeticaoPedidoRequisito ppr = new PeticaoPedidoRequisito();
			ppr.setPedidoRequisito((PedidoRequisito) listitem.getValue());
			ppr.setPeticao(pm);
			_peticaoPedidoRequisitoService.saveOrUpdate(ppr);
		}
	}
	
	public void gerarPedidoTaxas(List<Listitem> listI, Peticao pm){
		double valor=0;
		for (Listitem listitem : listI) {
	    	PeticaoMaritimoTaxaPedido _pmtp = new PeticaoMaritimoTaxaPedido();
	    	TaxaPedido tp = (TaxaPedido)listitem.getValue();
	    	_pmtp.setTaxaPedido(tp);
//	    	valor+=tp.getTaxa().getValor();
	    	_pmtp.setPeticao(pm);
	    	_peticaoMaritimoTaxaPedidoService.saveOrUpdate(_pmtp);
		}
		
		pm.setValor(valor);
	}

	private void listarArea() {
		List<Area> listArea = _areaService.getAll();
		cbx_area.setModel(new ListModelList<Area>(listArea));
	}

}
