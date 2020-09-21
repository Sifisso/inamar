package mz.ciuem.inamar.util;

import java.util.Calendar;

public class NuicUtil {
	
	public String gerarNUIC(String apelido, String nome,Calendar dataNascimento, String distrito){
		
		StringBuilder sb = new StringBuilder();
		sb.append(apelido).append(nome).append(dataNascimento.get(Calendar.DAY_OF_MONTH)).append(dataNascimento.get(Calendar.MONTH))
		.append(dataNascimento.get(Calendar.YEAR)).append(distrito);

		return 	sb.toString().toUpperCase().trim();
	
	}


}
