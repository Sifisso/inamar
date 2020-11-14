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
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.entity.report.DesempenhoProcessual;
import mz.ciuem.inamar.entity.report.DesempenhoProcessualNaoFinanceiro;
import mz.ciuem.inamar.entity.report.PeticaoDelegacao;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.PeticaoService;
import net.sf.jasperreports.engine.JRException;

@SuppressWarnings({ "serial", "rawtypes" })
public class DesempenhoProcessualCtrl extends GenericForwardComposer{

	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	private Window win_peticoes;
	
	private List<Peticao> listPeticoes = new ArrayList<Peticao>();
	private List<Object[]> _countByPeticao = new ArrayList<Object[]>();
	List<DesempenhoProcessual> _desempenhoProcessuals;
	List<DesempenhoProcessualNaoFinanceiro> _desempenhoProcessualNaoFinanceiro;
	
	private Listbox lbx_peticoes;
	private Combobox cbx_delegacao;
	private Label lbl_total_peticao, lbl_total_peticao_paga, lbl_total_peticao_nao_paga;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		_peticaoService = (PeticaoService) SpringUtil.getBean("peticaoService");
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listar();
		listarAdmars();
	}
	
	private void listar(){
		List<Object[]> peticoes = _peticaoService.getPeticaoDelegacaoDesempenhoProcessual();
		lbx_peticoes.setModel(new ListModelList<Object[]>(peticoes));
		
		_countByPeticao = _peticaoService.getPeticaoDelegacaoDesempenhoProcessual();
		(lbl_total_peticao).setValue(""+totalPeticao(_countByPeticao));
		(lbl_total_peticao_paga).setValue(""+totalPeticoesPagas(_countByPeticao));
		(lbl_total_peticao_nao_paga).setValue(""+totalPeticoesNaoPagas(_countByPeticao));
		lbx_peticoes.setModel(new ListModelList<>(_countByPeticao));
		
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
	
	private int totalPeticoesPagas(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[2]);
			
		}		
		return total;
	}
	
	
	
	private int totalPeticoesNaoPagas(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[3]);
			
		}		
		return total;
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		_countByPeticao = _peticaoService.getPeticaoDelegacaoDesempenhoProcessual();
		preencherPeticaDelegacao(_countByPeticao);
		imprimir("/reporter/reporterDesempenhoProcessual.jrxml", "Desempenho Processual por Delegação");
   	}
	

	private void imprimir(String path, String title) throws JRException {
		final Execution ex = Executions.getCurrent();
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", title);
        
        MasterRep.imprimir(path, _desempenhoProcessuals, mapaParam, win_peticoes);
	}
	
	private void preencherPeticaDelegacao(List<Object[]> listObjects) {
		_desempenhoProcessuals = new ArrayList<DesempenhoProcessual>();
		for(Object[] objects : listObjects){
			
			DesempenhoProcessual dp = new DesempenhoProcessual();
			
			String delegacao = (""+objects[0]);
			dp.setDelegacao(delegacao);
			
			String peticoes = (""+objects[1]);
			dp.setPeticoes(peticoes);
			
			String pagas = (""+objects[2]);
			dp.setPagas(pagas);
			
			double peticoess = Double.valueOf(peticoes);
			double pagass = Double.valueOf(pagas);
			double percentual = (Math.round(pagass*100)/peticoess);
			String percentuaal = String.valueOf(percentual);
			dp.setPagasPercentual(percentuaal);
			
			String naoPagas = (""+objects[3]);
			dp.setNaoPagas(naoPagas);
			
			double naoPagass = Double.valueOf(naoPagas);
			double naoPagasPercentual = (Math.round(naoPagass*100)/peticoess);
			String naoPagasPercentuaal = String.valueOf(naoPagasPercentual);
			dp.setNaoPagasPercentual(naoPagasPercentuaal);
			
			
				listPeticoes = new ArrayList<Peticao>();
				int total = 0;
				int totalPagos=0;
				int totalNaoPagos=0;
				
				for (Object[] listobjects :listObjects) {
					
				total = total + Integer.valueOf(""+listobjects[1]);
				totalPagos = totalPagos + Integer.valueOf(""+listobjects[2]);
				totalNaoPagos = totalNaoPagos + Integer.valueOf(""+listobjects[3]);
					
				}	
				String totall = String.valueOf(total);
				String totalPagoss = String.valueOf(totalPagos);
				String totalNaoPagoss = String.valueOf(totalNaoPagos);
				
				dp.setTotalPeticoes(totall);
				dp.setTotalPagas(totalPagoss);
				dp.setTotalNaoPagas(totalNaoPagoss);
			
			
			
			_desempenhoProcessuals.add(dp);
		}
		
	}
	
}
