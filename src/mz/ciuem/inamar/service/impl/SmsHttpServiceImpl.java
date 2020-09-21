package mz.ciuem.inamar.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

@Service
public class SmsHttpServiceImpl {

	public void config(String Url, String destino, String messagem,
			String utitizador, String senha, String origem) {
		
		try {

			Url = "http://127.0.0.1:9501/api?action=sendmessage&"
					+ "utitizador=" + URLEncoder.encode(utitizador, "UTF-8")
					+ "&senha=" + URLEncoder.encode(senha, "UTF-8")
					+ "&destino=" + URLEncoder.encode(destino, "UTF-8")
					+ "&messagetype=SMS:TEXT" + "&messagem="
					+ URLEncoder.encode(messagem, "UTF-8") + "&origem="
					+ URLEncoder.encode(origem, "UTF-8")
					+ "&serviceprovider=GSMModem1" + "&responseformat=html";

			URL url = new URL(Url);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.disconnect();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}
	}
}
