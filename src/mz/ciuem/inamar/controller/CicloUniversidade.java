//package mz.ciuem.sgea.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import mz.ciuem.sgea.entity.Ciclo;
//import mz.ciuem.sgea.entity.CicloDisciplina;
//import mz.ciuem.sgea.entity.CicloUniversidade;
//import mz.ciuem.sgea.entity.Universidade;
//import mz.ciuem.sgea.service.AgenteService;
//import mz.ciuem.sgea.service.CicloAgenteService;
//import mz.ciuem.sgea.service.CicloCursoService;
//import mz.ciuem.sgea.service.CicloDisciplinaService;
//import mz.ciuem.sgea.service.CicloLocailService;
//import mz.ciuem.sgea.service.CicloUniversidadeService;
//import mz.ciuem.sgea.service.ContaService;
//import mz.ciuem.sgea.service.CursoService;
//import mz.ciuem.sgea.service.DisciplinaService;
//import mz.ciuem.sgea.service.LocalService;
//import mz.ciuem.sgea.service.PapelService;
//import mz.ciuem.sgea.service.ProvinciaService;
//import mz.ciuem.sgea.service.SectorService;
//import mz.ciuem.sgea.service.UnidadeOrganicaService;
//import mz.ciuem.sgea.service.UniversidadeService;
//
//import org.zkoss.spring.SpringUtil;
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.Execution;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zk.ui.select.annotation.WireVariable;
//import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.zul.Button;
//import org.zkoss.zul.Combobox;
//import org.zkoss.zul.Doublebox;
//import org.zkoss.zul.ListModelList;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zul.Listcell;
//import org.zkoss.zul.Listitem;
//import org.zkoss.zul.Window;
//
//public class CicloUniversidade extends GenericForwardComposer{
//
//	private Combobox cbx_universidade;
//	private Button btn_adicionar;
//	private Button btn_cancelar;
//	private Button btn_gravar;
//	private Listbox lbx_universidades;
//	private Listbox lbx_universidadesAdicionadas;
//
//	Window win;
//
//	Ciclo c;
//	private Universidade _selectedUniversidadeU;
//
//	CicloUniversidade _selectedCicloUniversidade;
//
//	@WireVariable
//	private UniversidadeService _universidadeService;
//	@WireVariable
//	private CicloUniversidadeService _cicloUniversidadeService;
//
//	ListModelList<Universidade> listModelUniversidade, listModelUniversidadeU;
//	ListModelList<CicloUniversidade> listModelCicloUniversidade;
//	ListModelList<CicloDisciplina> listModelCicloDisciplina;
//
//	List<Universidade> listUniversidadeU = new ArrayList<Universidade>();
//
//	
//	public void doBeforeComposeChildren(Component comp) throws Exception {
//		super.doBeforeComposeChildren(comp);
//
//
//		_cicloUniversidadeService = (CicloUniversidadeService) SpringUtil.getBean("cicloUniversidadeService");
//		_universidadeService = (UniversidadeService) SpringUtil.getBean("universidadeService");
//
//		final Execution ex = Executions.getCurrent();
//		c = (Ciclo) ex.getArg().get("_ciclo");
//	}
//
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//
//
//		// -----------------------------Universidade------------------------------------
//		_selectedUniversidadeU = null;
//		listarUniversidade();
//		listaCicloUniversidade();
//		// ----------------------------/universidade------------------------------------
//
//	
//	}
//	
//	
//	// -----------------------------Universidade------------------------------------
//	public void onSelect$cbx_universidade() {
//		if (listModelUniversidade.isSelectionEmpty()) {
//			_selectedUniversidadeU = null;
//		} else {
//			_selectedUniversidadeU = (Universidade) listModelUniversidade
//					.getSelection().iterator().next();
//		}
//	}
//
//	public void onClick$btn_adicionar() {
//		listUniversidadeU.add(_selectedUniversidadeU);
//
//		listModelUniversidadeU = new ListModelList<Universidade>(
//				listUniversidadeU);
//
//		lbx_universidades.setModel(listModelUniversidadeU);
//		limparCU();
//	}
//
//	public void onClick$btn_cancelar() {
//		lbx_universidades.getItems().clear();
//
//		listUniversidadeU.clear();
//
//	}
//
//	public void onClick$btn_gravar() {
//		for (Listitem listItem : lbx_universidades.getItems()) {
//
//			CicloUniversidade _cicloUniversidade = new CicloUniversidade();
//			_cicloUniversidade.setUniversidade((Universidade) listItem.getValue());
//
//			Listcell listcell = (Listcell) listItem.getLastChild();
//			Doublebox db = (Doublebox) listcell.getFirstChild();
//			_cicloUniversidade.setValorDisciplina(db.getValue());
//			_cicloUniversidade.setCiclo(c);
//			_cicloUniversidade.setDataInicio(c.getDataInicio());
//			_cicloUniversidade.setDataFim(c.getDataFim());
//			_cicloUniversidadeService.create(_cicloUniversidade);
//		}
//		listaCicloUniversidade();
//
//		lbx_universidades.getItems().clear();
//
//		listUniversidadeU.clear();
//	}
//
//	public void listarUniversidade() {
//		List<Universidade> listUniversidade = _universidadeService.getAll();
//		listModelUniversidade = new ListModelList<Universidade>(
//				listUniversidade);
//		cbx_universidade.setModel(listModelUniversidade);
//	}
//
//	public void limparCU() {
//		cbx_universidade.setRawValue(null);
//		_selectedUniversidadeU = null;
//	}
//
//	public void listaCicloUniversidade() {
//		List<CicloUniversidade> l = _cicloUniversidadeService.getAll();
//		listModelCicloUniversidade = new ListModelList<CicloUniversidade>(l);
//		lbx_universidadesAdicionadas.setModel(listModelCicloUniversidade);
//	}
//
//	// ----------------------------/universidade------------------------------------
//	
//
//}
