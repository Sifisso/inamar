package mz.ciuem.inamar.util;

import java.io.File;

//import mz.ciuem.inamar.entity.Candidato;
import mz.ciuem.inamar.entity.User;
//import mz.ciuem.inamar.service.CandidatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class TakePhotoValidadorUtil extends GenericForwardComposer{
	

	private static final long serialVersionUID = 1L;
	private Window win3;
	private Window win_photo;
	
	@SuppressWarnings("unused")
	private Include inc_photo;
	private Image image;
	private Session _session = Executions.getCurrent().getSession();
//	private Candidato _candidato;
	String path = "";
	private String barra;
	private org.zkoss.zhtml.Button btn_capturar, btn_cancelar, btn_enviar;
	
	@Autowired
//	private CandidatoService _candidatoService;
	
	public void onClose$win(Event e){
	
		
	}
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
//		_candidato = (Candidato) Executions.getCurrent().getArg()
//				.get("candidato");
//		_candidatoService = (CandidatoService) SpringUtil.getBean("candidatoService");
		barra = "/";
	}
	 @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		

		path = Executions.getCurrent().getDesktop().getWebApp().getRealPath(barra+"ficheiros"+barra+"foto");
//		path = path.concat(barra)+_candidato.getCodigo()+".png";
		_session.setAttribute("uri_foto", path);
		//alert(""+barra);
	}
	 
	 
	public void onClick$btn_capturar(Event e){
		//image = (Image) win3.getFellow("image");
		//image.setSrc("/img/avatar.png");
	
	
	}

	
	public void onClick$btn_enviar(){
	
		
//		long id = _candidato.getCodigo();
//		
//		Listitem lisIt = (Listitem)win3.getFellow(""+id);
//		
//		Listcell lc = (Listcell) lisIt.getChildren().get(0);
//		image = (Image) lc.getFirstChild();
//		
//		_candidato.setFoto(barra+"ficheiros"+barra+"foto"+barra+_candidato.getCodigo()+".png");
//		
//		_candidatoService.update(_candidato);
//		
//		image.setSrc(barra+"ficheiros"+barra+"foto"+barra+_candidato.getCodigo()+".png");
//		
//		win_photo.detach();
		
	}
	
	public void onClick$btn_cancelar(Event e){
		

		win_photo.detach();
	}
	

}
