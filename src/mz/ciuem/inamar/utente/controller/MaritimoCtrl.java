package mz.ciuem.inamar.utente.controller;

import mz.ciuem.inamar.entity.CategoriaMaritimo;
import mz.ciuem.inamar.entity.ClasseMaritimo;
import mz.ciuem.inamar.entity.GrupoMaritimo;
import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.CategoriaMaritimoService;
import mz.ciuem.inamar.service.ClasseMaritimoService;
import mz.ciuem.inamar.service.Grupo_MaritimoService;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class MaritimoCtrl extends GenericForwardComposer{
	
	private Combobox cbx_tipoUtente;
	private Combobox cbx_grupo, cbx_classe, cbx_categoria;
	private Include inc_main;
	private Div div_content_out;
	private Window win_regMaritimos;
	
	@WireVariable
	private Grupo_MaritimoService _grupoMaritimoService;
	@WireVariable
	private ClasseMaritimoService _classeMaritimoService;
	@WireVariable
	private CategoriaMaritimoService _categoriaMaritimoService;
	@WireVariable
	private UtenteService _utenteService;
	
	Utente _utente;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		_grupoMaritimoService= (Grupo_MaritimoService) SpringUtil.getBean("grupo_MaritimoService");
		_classeMaritimoService = (ClasseMaritimoService) SpringUtil.getBean("classeMaritimoService");
		_categoriaMaritimoService = (CategoriaMaritimoService) SpringUtil.getBean("categoriaMaritimoService");
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		
		_utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
		
		if(_utente==null) {
			_utente = new Utente();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherGrupoMaritimo();
		preenchercampos(_utente);
	}
	
	 private void preenchercampos(Utente _utente2) {
		cbx_grupo.setValue(_utente.getGrupo_maritimo());
		cbx_classe.setValue(_utente.getClasse_maritimo());
		cbx_categoria.setValue(_utente.getCategoria_maritimo().getNome());
		//((Object) cbx_categoria).setItem(new Comboitem().setValue(_utente.getCategoria_maritimo()));
		Comboitem ci = new Comboitem();
		ci.setValue(_utente.getCategoria_maritimo());
		cbx_categoria.appendChild(ci);
		cbx_categoria.setSelectedIndex(0);
		//cbx_categoria.setSelectedItem(ci);
		cbx_tipoUtente.setValue(_utente.getTipo());
	}
	 
    public void onClick$btn_proximo(Event e){
    	if(_utente==null)_utente = new Utente();
    	gravar();
    }

	private void gravar() {
		try {
			_utente.setGrupo_maritimo(cbx_grupo.getValue());
			_utente.setClasse_maritimo(cbx_classe.getValue());
			_utente.setCategoria_maritimo((CategoriaMaritimo)cbx_categoria.getSelectedItem().getValue());
			_utente.setMaritimo(true);
			_utente.setTipo(cbx_tipoUtente.getValue());
			_utenteService.saveOrUpdate(_utente);
			redirecionar(_utente);
		} catch (NullPointerException e) {
      System.out.println("Erro ");
		}
	}

	private void redirecionar(Utente _utente2) {
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente2);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_maritimoUm.zul");
	}

	private void preencherGrupoMaritimo() {
	      cbx_grupo.setModel(new ListModelList<GrupoMaritimo>(_grupoMaritimoService.getAll()));		
		}
		
	   public void onSelect$cbx_grupo(){
			cbx_classe.getItems().clear();
			cbx_categoria.getItems().clear();
			GrupoMaritimo gm = (GrupoMaritimo) cbx_grupo.getSelectedItem().getValue();
			cbx_classe.setModel(new ListModelList<ClasseMaritimo>(_classeMaritimoService.findByGrupoMaritimo(gm)));
		}

		public void onSelect$cbx_classe(){
			cbx_categoria.getItems().clear();
			ClasseMaritimo cm = (ClasseMaritimo) cbx_classe.getSelectedItem().getValue();
			cbx_categoria.setModel(new ListModelList<CategoriaMaritimo>(_categoriaMaritimoService.findByClasseMaritimo(cm)));
		}
	
	   
	

}
