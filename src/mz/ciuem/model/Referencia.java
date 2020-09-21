package mz.ciuem.model;

import javax.swing.JOptionPane;

public class Referencia {

	public static void main(String[] args) {
		
					int pi=0,si=0,pn=0,checkDigit=0;
					
					String entidade= "70032";	
					String nrFactura = "126808401";
					String valorCandidato = "70000";

					String dadosEntrada= entidade+nrFactura+valorCandidato;
					
					for (int i=1; i<=dadosEntrada.length();i++){
							si = (Integer.parseInt(""+dadosEntrada.charAt(i-1)))+pi;
							pi= (si*10) % 97;
					}
					
					
					pn = (pi*10) % 97;
					checkDigit= 98-pn;
					String checkDigi=checkDigit<10?"0"+checkDigit:""+checkDigit;

				JOptionPane.showMessageDialog(null, nrFactura+""+checkDigi);
				
				
				
				
				
				
				
				
			

	}	
}
