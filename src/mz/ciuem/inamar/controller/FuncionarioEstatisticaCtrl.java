package mz.ciuem.inamar.controller;

import java.util.ArrayList;
import java.util.List;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.service.ContaService;
import mz.ciuem.inamar.service.DelegacaoService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

@SuppressWarnings({ "serial", "rawtypes" })
public class FuncionarioEstatisticaCtrl extends GenericForwardComposer{
	
	@Wire("#mainlayout")
	private Div target;
	
	@Wire("#breadcrumb")
	private Ol ol;
	
	//Main Div
	
	//Modal Div
	
	private Window win_regFunEstatistica;
	
	Execution ex = Executions.getCurrent();
	
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	@WireVariable
	private ContaService _contaService;
	
	private List<Delegacao> lisDeleg =  new ArrayList<Delegacao>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_delegacaoService = (DelegacaoService) SpringUtil.getBean("delegacaoService");
		_contaService = (ContaService) SpringUtil.getBean("contaService");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

	}

}
