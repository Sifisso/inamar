package mz.ciuem.inamar.controller.monitoria;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Pedido;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.report.DesempenhoProcessual;
import mz.ciuem.inamar.entity.report.DesempenhoProcessualNaoFinanceiro;
import mz.ciuem.inamar.entity.report.PeticaoDelegacao;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.PeticaoService;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings({ "serial", "rawtypes" })
public class DesempenhoFinanceiroPedidoCtrl extends GenericForwardComposer{

	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	private Window win_peticoes;
	
	private Delegacao delegacao;
	
	private Execution ex = Executions.getCurrent();
	
	private List<Peticao> listPeticoes = new ArrayList<Peticao>();
	private List<Object[]> _countByPeticao = new ArrayList<Object[]>();
	List<DesempenhoProcessual> _desempenhoProcessuals;
	List<DesempenhoProcessualNaoFinanceiro> _desempenhoProcessualNaoFinanceiro;
	
	private Listbox lbx_peticoes, lbx_peticoesPedido;
	private Combobox cbx_delegacao;
	private Label lbl_total_peticao, lbl_total_peticao_invalida, lbl_total_peticao_pronta, lbl_total_peticao_em_tratamento;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
		
		delegacao = (Delegacao) ex.getArg().get("delegacao");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listarpeticoespedido(delegacao);
	}
	
	private void listar(){
		List<Object[]> peticoes = _peticaoService.getPeticaoDelegacaoDesempenhoProcessualNaoFinanceiro();
		lbx_peticoes.setModel(new ListModelList<Object[]>(peticoes));
		
		_countByPeticao = _peticaoService.getPeticaoDelegacaoDesempenhoProcessualNaoFinanceiro();
		(lbl_total_peticao).setValue(""+totalPeticao(_countByPeticao));
		//(lbl_total_peticao_paga).setValue(""+totalPeticoesPagas(_countByPeticao));
		//(lbl_total_peticao_nao_paga).setValue(""+totalPeticoesNaoPagas(_countByPeticao));
		(lbl_total_peticao_invalida).setValue(""+totalPeticoesInvalidas(_countByPeticao));
		(lbl_total_peticao_pronta).setValue(""+totalPeticoesProntas(_countByPeticao));
		(lbl_total_peticao_em_tratamento).setValue(""+totalPeticoesEmTratamento(_countByPeticao));
		lbx_peticoes.setModel(new ListModelList<>(_countByPeticao));
		
	}
	
	
	private void listarpeticoespedido(Delegacao delegacao){
		List<Object[]> peticoes = _peticaoService.getPeticaoPedido(delegacao);
		lbx_peticoesPedido.setModel(new ListModelList<Object[]>(peticoes));
	}
	
	private void listarAdmars(){
		List<Delegacao> delegacoes = _delegacaoService.getAll();
		cbx_delegacao.setModel(new ListModelList<Delegacao>(delegacoes));
	}
	
	private int totalPeticao(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[1]);
			
		}		
		return total;
	}
	
	
	
	private int totalPeticoesInvalidas(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[2]);
			
		}		
		return total;
	}
	
	private int totalPeticoesEmTratamento(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[3]);
			
		}		
		return total;
	}
	
	private int totalPeticoesProntas(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[4]);
			
		}		
		return total;
	}
	
	
	public void onClick$btn_imprimirGlobal(Event e) throws JRException{
		_countByPeticao = _peticaoService.getPeticaoDelegacaoDesempenhoProcessualNaoFinanceiro();
		desempenhoProcessualNaoFinanceiro(_countByPeticao);
		imprimirGlobal("/reporter/reporterDesempenhoProcessualNaoFinanceiro.jrxml", "Desempenho Processual por Delegação");
   	}

	
	private void imprimirGlobal(String path, String title) throws JRException {
		final Execution ex = Executions.getCurrent();
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", title);
        
        MasterRep.imprimir(path, _desempenhoProcessualNaoFinanceiro, mapaParam, win_peticoes);
	}
		
		private void desempenhoProcessualNaoFinanceiro(List<Object[]> listObjects) {
			_desempenhoProcessualNaoFinanceiro = new ArrayList<DesempenhoProcessualNaoFinanceiro>();
			for(Object[] objects : listObjects){
				
				DesempenhoProcessualNaoFinanceiro dpf = new DesempenhoProcessualNaoFinanceiro();
				
				String delegacao = (""+objects[0]);
				dpf.setDelegacao(delegacao);
				
				String peticoes = (""+objects[1]);
				dpf.setPeticoes(peticoes);
				
				String invalidos = (""+objects[2]);
				dpf.setInvalidos(invalidos);
				
				double peticoess = Double.valueOf(peticoes);
				double invalidoss = Double.valueOf(invalidos);
				double percentual = ((invalidoss*100)/peticoess);
				String percentuall = String.valueOf(percentual);
				dpf.setInvalidosPercentual(percentuall);
				
				
				String prontos = (""+objects[3]);
				dpf.setProntos(prontos);
				
				double prontoss = Double.valueOf(prontos);
				double prontosPercentual = ((prontoss*100)/peticoess);
				String prontosPercentuall = String.valueOf(prontosPercentual);
				dpf.setProntosPercentual(prontosPercentuall);
				
				String emTratamento = (""+objects[4]);
				dpf.setEmTratamento(emTratamento);
				
				double emTratamentos = Double.valueOf(emTratamento);
				double emTratamentoPercentual = ((emTratamentos*100)/peticoess);
				String emTratamentoPercentuall = String.valueOf(emTratamentoPercentual);
				dpf.setEmTratamentoPercentual(emTratamentoPercentuall);
				
				
				listPeticoes = new ArrayList<Peticao>();
				int total = 0;
				int totalInvalido=0;
				int totalPronto=0;
				int totalEmTratamento=0;
				
				for (Object[] listobjects :listObjects) {
					
				total = total + Integer.valueOf(""+listobjects[1]);
				totalInvalido = totalInvalido + Integer.valueOf(""+listobjects[2]);
				totalPronto = totalPronto + Integer.valueOf(""+listobjects[3]);
				totalEmTratamento = totalEmTratamento + Integer.valueOf(""+listobjects[4]);
					
				}	
				String totall = String.valueOf(total);
				String totalInvalidos = String.valueOf(totalInvalido);
				String totalProntoss = String.valueOf(totalPronto);
				String totalEmTratamentoo = String.valueOf(totalEmTratamento);
				
				dpf.setTotalPeticoes(totall);
				dpf.setTotalInvalidos(totalInvalidos);
				dpf.setTotalProntos(totalProntoss);
				dpf.setTotalEmTratamento(totalEmTratamentoo);
				
				_desempenhoProcessualNaoFinanceiro.add(dpf);
			}
		
	}
}
