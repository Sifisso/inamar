package mz.ciuem.inamar.controller.monitoria;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import mz.ciuem.inamar.entity.Delegacao;
import mz.ciuem.inamar.entity.Peticao;
import mz.ciuem.inamar.service.DelegacaoService;
import mz.ciuem.inamar.service.PeticaoService;

@SuppressWarnings({ "serial", "rawtypes" })
public class DesempenhoProcessualCtrl extends GenericForwardComposer{

	@WireVariable
	private PeticaoService _peticaoService;
	
	@WireVariable
	private DelegacaoService _delegacaoService;
	
	private Window win_peticoes;
	
	private List<Peticao> listPeticoes = new ArrayList<Peticao>();
	
	private Listbox lbx_peticoes;
	private Combobox cbx_delegacao;
	
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
		
	}	
	
	private void listarAdmars(){
		List<Delegacao> delegacoes = _delegacaoService.getAll();
		cbx_delegacao.setModel(new ListModelList<Delegacao>(delegacoes));
	}
	
}
