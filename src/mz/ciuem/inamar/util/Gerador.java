package mz.ciuem.inamar.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Gerador{
	
	public static String gerarReferencia( String aa, String ss, String uuuuu, String cd) {
		String ref = aa+ss+uuuuu+cd;
		return ref;
	}

	public static String gerarEntidade(String xxx, String ss) {
		String ent = xxx+ss;
		return ent;
	}

}
