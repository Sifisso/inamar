package mz.ciuem.inamar.controller.monitoria;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.ciuem.inamar.comps.MasterRep;
import mz.ciuem.inamar.entity.Funcionario;
import mz.ciuem.inamar.entity.Instituicao;
import mz.ciuem.inamar.entity.report.FuncionarioDelegacao;
import mz.ciuem.inamar.entity.report.PeticaoDelegacao;
import mz.ciuem.inamar.service.FuncionarioService;
import mz.ciuem.inamar.service.InstituicaoService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Label;

@SuppressWarnings({ "serial", "rawtypes" })
public class TotalFuncionarioCtrl extends GenericForwardComposer{
	
	@WireVariable
	private FuncionarioService _funcionarioService;
	
	@WireVariable
	private InstituicaoService _InstituicaoService;
	
	private List<Funcionario> listFuncionarios= new ArrayList<Funcionario>();
	private List<Instituicao> listInstituicaos = new ArrayList<Instituicao>();
	private List<Object[]> _countByFuncionario = new ArrayList<Object[]>();
	
	List<FuncionarioDelegacao> _funcionarioDelegacaoRep;
	
	private Window win_funcionarios;
	private Funcionario funcionario=null;
	private  Label lbl_total_funcioanario;
	private Listbox lbx_funcionarios;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		_funcionarioService = (FuncionarioService)SpringUtil.getBean("funcionarioService");
		_InstituicaoService = (InstituicaoService)SpringUtil.getBean("instituicaoService");
		super.doBeforeComposeChildren(comp);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listarFuncionariosPorDelegacao();
	}
	

	private void listarFuncionariosPorDelegacao(){
		List<Object[]> listF = _funcionarioService.getDelegacaoFuncionario();
		lbx_funcionarios.setModel(new ListModelList<Object[]>(listF));
		
		_countByFuncionario = _funcionarioService.getDelegacaoFuncionario (); 
		(lbl_total_funcioanario).setValue(""+totalFuncionarios(_countByFuncionario));
		lbx_funcionarios.setModel(new ListModelList<>(_countByFuncionario));
	}
	
	
	private int totalFuncionarios(List<Object[]> listObjects){
		listFuncionarios = new ArrayList<Funcionario>();
		int total = 0;
		for (Object[] objects :listObjects) {
			
		total = total + Integer.valueOf(""+objects[1]);
			
		}		
		return total;
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		_countByFuncionario = _funcionarioService.getDelegacaoFuncionario();
		preencherFuncionarioDelegacao(_countByFuncionario);
		imprimir("/reporter/reporterTotalFuncionario.jrxml", "Lista de Funcionários por Delegação");
	}

	private void imprimir(String path, String title) throws JRException {
		final Execution ex = Executions.getCurrent();
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/inmr.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", title);
        
        MasterRep.imprimir(path, _funcionarioDelegacaoRep, mapaParam, win_funcionarios);
		
	}
	
	private void preencherFuncionarioDelegacao(List<Object[]> listObjects) {
		_funcionarioDelegacaoRep = new ArrayList<FuncionarioDelegacao>();
		for(Object[] objects : listObjects){
			
			FuncionarioDelegacao fd = new FuncionarioDelegacao();
			
			String admar = (""+objects[0]);
			fd.setAdmar(admar);
			
			String quantidade = (""+objects[1]);
			fd.setQuantidade(quantidade);
			
			_funcionarioDelegacaoRep.add(fd);
		}
		
	}
}
