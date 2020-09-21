package mz.ciuem.inamar.util;
import java.io.File;

import org.zkoss.zk.ui.Desktop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import mz.ciuem.inamar.entity.AnexoCandidato;
//import mz.ciuem.inamar.entity.Candidato;
//import mz.ciuem.inamar.entity.User;
//import mz.ciuem.inamar.service.AnexoService;
//import mz.ciuem.inamar.service.CandidatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

@SuppressWarnings("rawtypes")
public class TakePhotoCtrl extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Window win;
	private Window win_photo;
	@SuppressWarnings("unused")
	private Include inc_photo;
	private Image img_pic;
	private String fileName;
//	private AnexoCandidato _anexo;
	private Media _media;
	private String PHOTO_PATH = "/ficheiros/foto/";
	private String SAVE_PATH;
	private UploadFile _uploadFile;
	private Desktop _desktop;
	private Session _session = Executions.getCurrent().getSession();
//	private Candidato _candidato;
	String path = "";
	String canoPath="";
	String fs = File.separator;
	private org.zkoss.zhtml.Button btn_capturar, btn_cancelar, btn_enviar;
	
	@Autowired
//	private CandidatoService _candidatoService;
	
	
//	private AnexoService _anexoService;
	
	public void onClose$win(Event e){
		Executions.sendRedirect(null);
		//alert("");
		win.detach();
		
	}
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
//		_candidatoService = (CandidatoService) SpringUtil.getBean("candidatoService");
//		_anexoService = (AnexoService) SpringUtil.getBean("anexoService");
	}
	 @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
//		User user = (User) Executions.getCurrent().getDesktop().getSession().getAttribute("ss_utilizador");
//		_candidato = _candidatoService.buscarUser(user);
//		
//		_uploadFile = new UploadFile();
//		_desktop = Executions.getCurrent().getDesktop();
//		_session = _desktop.getSession();
//		path = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/ficheiros/foto");
//		path = path.concat(fs)+_candidato.getCodigo()+".png";
//		_session.setAttribute("uri_foto", path);
//		canoPath = fs+"ficheiros"+fs+"foto"+fs+_candidato.getCodigo()+".png";
//		
		
	}
	public void onClick$btn_capturar(Event e){
//		img_pic = (Image) win.getFellow("img_pic");
//		img_pic.setSrc("/img/avatar.png");

	}

	
	public void onClick$btn_enviar(){
//		img_pic = (Image) win.getFellow("img_pic");
		
//		_candidato.setFoto("/"+"ficheiros"+"/"+"foto"+"/"+_candidato.getCodigo()+".png");
//		_candidatoService.saveOrUpdate(_candidato);
		
		SAVE_PATH = _desktop.getWebApp().getRealPath(PHOTO_PATH) + "\\";
//		fileName = ""+_candidato.getCodigo()+".png";
		
		criarAnexo(PHOTO_PATH + fileName, "FOTOGRAFIA");
//		img_pic.setSrc("/"+"ficheiros"+"/"+"foto"+"/"+_candidato.getCodigo()+".png");
		win_photo.detach();
	}
	
	public void onClick$btn_cancelar(Event e){
		win_photo.detach();
	}
	
	private void criarAnexo(String URI, String type) {

//		AnexoCandidato anexo;
//
//		anexo = _anexo == null ? new AnexoCandidato() : _anexo;
//
//		anexo.setURI(URI);
//		anexo.setDesignacao("" + _candidato.getCodigo());
//		anexo.setCandidatoId(_candidato.getCodigo());
//		anexo.setTipo(type);
//		_anexoService.saveOrUpdate(anexo);
//		anexo = _anexo;
	}
	
}