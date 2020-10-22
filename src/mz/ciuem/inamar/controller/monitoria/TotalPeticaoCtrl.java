package mz.ciuem.inamar.controller.monitoria;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.report.PeticaoDelegacao;
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.PeticaoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Label;

import com.mysql.fabric.xmlrpc.base.Array;

@SuppressWarnings({ "serial", "rawtypes" })
public class TotalPeticaoCtrl extends GenericForwardComposer{
	
	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private AreaService _areaService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	private Window win_peticoes;
	
	private List<Peticao> listPeticoes = new ArrayList<Peticao>();
	private List<Object[]> _countByPeticao = new ArrayList<Object[]>();
	private List<Object[]> _peticaoDelegacao = new ArrayList<Object[]>();
	
	List<PeticaoDelegacao> _peticDelegacaoRep;
	
	private Listbox lbx_peticoes;
	private Combobox cbx_ciclos;
	private Label lbl_total_peticao, lbl_total_peticao_paga, lbl_total_peticao_terminada;
	private Button btn_imprimir;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_areaService = (AreaService) SpringUtil.getBean("areaService");
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listar();
		//listarPeticoes();
		listarAdmars();
	}
	
	private void listarAdmars(){
		List<Delegacao> delegacoes = _delegacaoService.getAll();
		cbx_ciclos.setModel(new ListModelList<Delegacao>(delegacoes));
	}
	
	/*private void listarPeticoes(){
		List<Peticao> peticaos = _peticaoService.getAll();
		lbx_funcionarios.setModel(new ListModelList<Peticao>(peticaos));
	}*/
	
	private void listar(){
		List<Object[]> peticoes = _peticaoService.getPeticaoDelegacao();
		lbx_peticoes.setModel(new ListModelList<Object[]>(peticoes));
		
		_countByPeticao = _peticaoService.getPeticaoDelegacao();
		(lbl_total_peticao).setValue(""+totalPeticao(_countByPeticao));
		//(lbl_total_peticao_paga).setValue(""+totalPeticoesPagas(_countByPeticao));
		//(lbl_total_peticao_terminada).setValue(""+totalPeticoesTerminadas(_countByPeticao));
		lbx_peticoes.setModel(new ListModelList<>(_countByPeticao));
	}
	
	private int totalPeticao(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[2]);
			
		}		
		return total;
	}
	
	/*private int totalPeticoesPagas(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[3]);
			
		}		
		return total;
	}
	
	private int totalPeticoesTerminadas(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[4]);
			
		}		
		return total;
	}*/
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		_countByPeticao = _peticaoService.getPeticaoDelegacao();
		preencherPeticaDelegacao(_countByPeticao);
		imprimir("/reporter/reporterTotalPeticao.jrxml", "Lista de Petições por Delegação");
   	}

	private void imprimir(String path, String title) throws JRException {
		final Execution ex = Executions.getCurrent();
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", title);
        
        MasterRep.imprimir(path, _peticDelegacaoRep, mapaParam, win_peticoes);
	}

	private void preencherPeticaDelegacao(List<Object[]> listObjects) {
		_peticDelegacaoRep = new ArrayList<PeticaoDelegacao>();
		for(Object[] objects : listObjects){
			
			PeticaoDelegacao pd = new PeticaoDelegacao();
			
			String admar = (""+objects[0]);
			pd.setAdmar(admar);
			
			String area = (""+objects[1]);
			pd.setArea(area);
			
			/*String paga = (""+objects[2]);
			pd.setPaga(paga);
			
			String terminada = (""+objects[3]);
			pd.setTerminada(terminada);*/
			
			String quantidade = (""+objects[2]);
			pd.setQuantidade(quantidade);
			
			
			_peticDelegacaoRep.add(pd);
		}
		
	}
	

	
}
