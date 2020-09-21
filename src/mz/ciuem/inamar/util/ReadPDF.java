package mz.ciuem.inamar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ReadPDF {

	

	    public static void main(String args[]) {
	        PDFTextStripper pdfStripper = null;
	        PDDocument pdDoc = null;
	        COSDocument cosDoc = null;
	        File file = new File("D:/cv.pdf");
	        try {
	            PDFParser parser = new PDFParser(new FileInputStream(file));
	            parser.parse();
	            cosDoc = parser.getDocument();
	            pdfStripper = new PDFTextStripper();
	            pdDoc = new PDDocument(cosDoc);
	            pdfStripper.setStartPage(1);
	            pdfStripper.setEndPage(3);
	            String parsedText = pdfStripper.getText(pdDoc);
	            System.out.println(parsedText);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	    }
	
}
