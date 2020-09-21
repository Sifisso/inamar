package mz.ciuem.inamar.controller.monitoria;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Embarcacao;
import mz.ciuem.inamar.entity.report.NavioDelegacao;
import mz.ciuem.inamar.service.EmbarcacaoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

@SuppressWarnings({ "serial", "rawtypes" })
public class TotalNavioCtrl extends GenericForwardComposer{
	
	@WireVariable
	private EmbarcacaoService _embarcacaoService;
	
	private List<Embarcacao> listEmbarcacoes= new ArrayList<Embarcacao>();
	private List<Object[]> _countByEmbarcacao = new ArrayList<Object[]>();
	
	List<NavioDelegacao> _navioDelegacaoRep;
	
	private Window win_peticoes;
	private  Label lbl_total_navio;
	private Listbox lbx_peticoes;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		_embarcacaoService = (EmbarcacaoService)SpringUtil.getBean("embarcacaoService");
		super.doBeforeComposeChildren(comp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listarNaviosPorDelegacaoDeRegisto();
	}
	

	private void listarNaviosPorDelegacaoDeRegisto(){
		List<Object[]> navios = _embarcacaoService.getNaviosByDelegacaoDeRegisto();
		lbx_peticoes.setModel(new ListModelList<Object[]>(navios));
		
		_countByEmbarcacao = _embarcacaoService.getNaviosByDelegacaoDeRegisto(); 
		(lbl_total_navio).setValue(""+totalNavios(_countByEmbarcacao));
		lbx_peticoes.setModel(new ListModelList<>(_countByEmbarcacao));
	}
	
	
	private int totalNavios(List<Object[]> listObjects){
		listEmbarcacoes = new ArrayList<Embarcacao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[2]);
			
		}		
		return total;
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		_countByEmbarcacao = _embarcacaoService.getNaviosByDelegacaoDeRegisto();
		preencherNavioDelegacao(_countByEmbarcacao);
		imprimir("/reporter/reporterTotalNavio.jrxml", "Lista de Navios por Delegação");
	}

	private void imprimir(String path, String title) throws JRException{
		final Execution ex = Executions.getCurrent();
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", title);
        
        MasterRep.imprimir(path, _navioDelegacaoRep, mapaParam, win_peticoes);
	}
	
	private void preencherNavioDelegacao(List<Object[]> listObjects) {
		_navioDelegacaoRep = new ArrayList<NavioDelegacao>();
		
		for(Object[] objects : listObjects){
			NavioDelegacao nd = new NavioDelegacao();
			
			String admar =(""+objects[0]);
			nd.setAdmar(admar);
			
			String nacionalidade =(""+objects[1]);
			nd.setNacionalidade(nacionalidade);
			
			String quantidade =(""+objects[2]);
			nd.setQuantidade(quantidade);
			
			_navioDelegacaoRep.add(nd);
		}
	}
}
