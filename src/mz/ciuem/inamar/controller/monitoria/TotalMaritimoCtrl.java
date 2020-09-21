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
import mz.ciuem.inamar.service.AreaService;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.InstituicaoService;
import mz.ciuem.inamar.service.PeticaoService;
import mz.ciuem.inamar.service.UtenteService;
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

@SuppressWarnings({ "serial", "rawtypes" })
public class TotalMaritimoCtrl extends GenericForwardComposer{
	
	@WireVariable
	private UtenteService _utenteService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	private Window win_peticoes;
	
	private List<Peticao> listPeticoes= new ArrayList<Peticao>();
	private List<Object[]> _countByMaritimo = new ArrayList<Object[]>();
	
	private Listbox lbx_maritimos;
	private Label lbl_total_maritimos;
	private Button btn_imprimir;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		//listar();
	}
	
	
	private void listar(){
		/*List<Object[]> peticoes = _peticaoService.getPeticaoDelegacao();
		lbx_peticoes.setModel(new ListModelList<Object[]>(peticoes));
		
		_countByPeticao = _peticaoService.getPeticaoDelegacao();
		(lbl_total_peticao).setValue(""+totalPeticao(_countByPeticao));
		lbx_peticoes.setModel(new ListModelList<>(_countByPeticao));*/
	}
	
	private int totalMaritimo(List<Object[]> listObjects){
		listPeticoes = new ArrayList<Peticao>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[1]);
			
		}		
		return total;
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
   		
   		Map<String, Object> mapaParam = new HashMap<String, Object>();	
   		final Execution ex = Executions.getCurrent();
   		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
           mapaParam.put("imagemLogo", inputV);
           mapaParam.put("listNome", "Lista de Petições");
   		MasterRep.imprimir("/reportParam/report_peticoes_total.jrxml", listPeticoes, mapaParam, win_peticoes);
   	}

}
