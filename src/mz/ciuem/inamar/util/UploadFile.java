package mz.ciuem.inamar.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.util.GenericForwardComposer;

@SuppressWarnings({ "serial", "rawtypes" })
public class UploadFile extends GenericForwardComposer {


	private FileOutputStream fos;
	private ZipOutputStream zos;
	
	
	
	public void saveFile(Media media, String path, String name) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			InputStream fin = media.getStreamData();			
			in = new BufferedInputStream(fin);
			
			File baseDir = new File(path);

			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}

			
			File newFile = new File(path+"\\"+name);
			
						
			OutputStream fout = new FileOutputStream(newFile);
			out = new BufferedOutputStream(fout);
			byte buffer[] = new byte[1024];
			int ch = in.read(buffer);
			while (ch != -1) {
				out.write(buffer, 0, ch);
				ch = in.read(buffer);
			}			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null) 
					out.close();	
				
				if (in != null)
					in.close();
				
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		
	}
	
	public UploadFile (String path,String fileName) throws FileNotFoundException{
		fos = new FileOutputStream(path+"\\"+fileName);
		zos = new ZipOutputStream(fos);
		
	}
	
	public UploadFile(){
		
		
	}

	public void initUpload(List<Media> medias) throws Exception {

	

		if (medias != null) {
		

			for (Media media : medias) {
				ZipEntry ze = new ZipEntry(media.getName());
				zos.putNextEntry(ze);
				zos.write(media.getByteData());

			}

		}
	}

	public void finishUpload() throws IOException {
		zos.closeEntry();
		zos.close();
	}

}