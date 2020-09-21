package mz.ciuem.inamar.controller;

import java.io.File;
import java.io.IOException;

import mz.ciuem.inamar.entity.Calendario;
import mz.ciuem.inamar.service.CalendarioService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import ch.qos.logback.core.Context;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class CalendarioCtrl {


	
	public static void main(String[] args) throws BiffException, IOException, ClassNotFoundException{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:*applicationContext.xml");
	
		CalendarioService _calendarioService = (CalendarioService) context.getBean("calendarioService");
		
		 //busca o arquivo
		 Workbook workbook = Workbook.getWorkbook(new File("D:/distsalasUEMULUZ2016.xls"));
		 
		 //pega primeira panilha dentro do arquivo excel
		 Sheet sheet = workbook.getSheet(0);
		 
		 //pega o número de linhas da panilha
		 long linhas = sheet.getRows();
		int j=0;
		 for (int i=0; i<linhas ; i++){
			 if(i>3263){
				 j=i;
			 Calendario calendario = new Calendario();
			
			 //pega as celulas de cada linha
			 Cell cellProv = sheet.getCell(1,i);
			 Cell cellNrCand = sheet.getCell(4,i);
			 Cell cellNome = sheet.getCell(5,i);
			 Cell cellDics = sheet.getCell(11,i);
			 Cell cellLocal = sheet.getCell(10,i);
			 Cell cellSala = sheet.getCell(9,i);
			 Cell cellData = sheet.getCell(12,i);
			 Cell cellHora = sheet.getCell(13,i);
			 
			 calendario.setProvincia(cellProv.getContents());
			 calendario.setCodCandidato(cellNrCand.getContents());
			 calendario.setNomeCandidato(cellNome.getContents());
			 calendario.setDisciplina(cellDics.getContents());
			 calendario.setLocal(cellLocal.getContents());
			 calendario.setSala(cellSala.getContents());
			 calendario.setData(cellData.getContents());
			 calendario.setHora(cellHora.getContents());
			 
			 _calendarioService.create(calendario);
			 System.out.println("created! - "+j);
			 j++;
			 }
		 }
		 
		 workbook.close();
		
	}
	
}
