package mz.ciuem.inamar.utente.controller;

import mz.ciuem.inamar.entity.Utente;
import mz.ciuem.inamar.service.UtenteService;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class MaritimoCtrlDois extends GenericForwardComposer{
	
	private Button btn_proximo,btn_anterior;
	private Window win_regDetalhes;
	private Include inc_main;
	private Div div_content_out;
	
	private Textbox tbx_nomePai;
	private Textbox tbx_nomeMae;
	private Textbox tbx_nrDocumento;
	private Textbox tbx_localEmissao;
	private Textbox tbx_ocupacao;
	private Datebox dtb_dataEmissao;
	private Combobox cbx_estadoCivil;
	private Combobox cbx_tipoDocumento;
	private Combobox cbx_nivelAcademico;
	
	@WireVariable
	private UtenteService _utenteService;
	
	Utente _utente;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		_utenteService = (UtenteService) SpringUtil.getBean("utenteService");
		_utente = (Utente) Executions.getCurrent().getSession().getAttribute("ss_utente");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		preencherCampos(_utente);
	}
	
	
	public void onClick$btn_proximo(){
		proximo();
	}
	
	public void onClick$btn_anterior(){
		anterior();
	}
	
	public void proximo(){
	//Include inc  = (Include) win_regDados.getParent();
	saveOrUpdade();
	Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
	div_content_out.detach();
	inc_main.setSrc("/views/expediente/confirmarRegistoMaritimo.zul");
	}
	
	public void anterior(){
		//Include inc  = (Include) win_regDados.getParent();
		saveOrUpdade();
		Executions.getCurrent().getSession().setAttribute("ss_utente", _utente);
		div_content_out.detach();
		inc_main.setSrc("/views/expediente/registar_maritimoUm.zul");
	}
	
	public void saveOrUpdade(){
		if(_utente==null){
			_utente = new Utente();
			gravar(_utente);
		}else{
			gravar(_utente);
		}
	}
	
	public void gravar(Utente u){
		u.setNomePai(tbx_nomePai.getValue());
		u.setNomeMae(tbx_nomeMae.getValue());
		u.setEstadoCivil(cbx_estadoCivil.getValue());
		u.setTipoDocumento(cbx_tipoDocumento.getValue());
		u.setNumeroDocumento(tbx_nrDocumento.getValue());
		u.setLocalEmissao(tbx_localEmissao.getValue());
		u.setValidade(dtb_dataEmissao.getValue());
		u.setHabilitacaoLiteraria(cbx_nivelAcademico.getValue());
		u.setOcupacao(tbx_ocupacao.getValue());
		_utenteService.saveOrUpdate(u);
	}
	
	public void preencherCampos(Utente u){
		if(u!=null){
			tbx_nomePai.setValue(u.getNomePai());
			tbx_nomeMae.setValue(u.getNomeMae());
			cbx_estadoCivil.setValue(u.getEstadoCivil());
			cbx_tipoDocumento.setValue(u.getTipoDocumento());
			tbx_nrDocumento.setValue(u.getNumeroDocumento());
			tbx_localEmissao.setValue(u.getLocalEmissao());
			dtb_dataEmissao.setValue(u.getValidade());
			cbx_nivelAcademico.setValue(u.getHabilitacaoLiteraria());
			tbx_ocupacao.setValue(u.getOcupacao());
		}
	}


}
