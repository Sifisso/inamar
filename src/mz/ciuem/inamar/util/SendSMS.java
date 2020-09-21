package mz.ciuem.inamar.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SendSMS {

	public static void sms(String to, String text) {
		if (!(to.isEmpty() || to == null)) {
			try {
				enviar(to, text);
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		}
	}

	public static String enviar(String to, String text) throws UnirestException {

		HttpResponse<String> response;
		response = Unirest.post("https://api.infobip.com/sms/1/text/single")

				.header("authorization", " Basic dWVtMjAxMzp1ZW1hZG1pc3Nhbw==")
				.header("content-type", "application/json").header("accept", "application/json")
				.body("{\"from\":\"UEM\",\"to\":\"+" + to + "\",\"text\":\"".concat(text) + "\"}").asString();
		return response.getBody();
	}

}
