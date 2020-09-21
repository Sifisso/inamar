package mz.ciuem.inamar.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SMSModem {

	public static String server;
	public static String user;
	public static String password;
	public static String phonenumber;
	public static String text;
	public static String data;
	public static String udh;
	public static String pid;
	public static String dcs;
	public static String sender;
	public static String validity;
	public static String servicetype;
	public static String smscroute;
	public static String receiptrequested;
	public static String sourceport;
	public static String destport;
	public static String delayuntil;
	public static String voicemail;
	public static String wapurl;
	public static String wapsl;

	public static String url_str;

	public static void init() {
		server = null;
		user = null;
		password = null;
		phonenumber = null;
		text = null;
		data = null;
		udh = null;
		pid = null;
		dcs = null;
		sender = null;
		validity = null;
		servicetype = null;
		smscroute = null;
		receiptrequested = null;
		sourceport = null;
		destport = null;
		delayuntil = null;
		voicemail = null;
		wapurl = null;
		wapsl = null;
	}

	public static void setvar(String argname, String argvalue) {

		if (argname != null) {
			if (argvalue != null) {
				url_str = url_str + "&" + argname + "=";
				try {
					String encoded = URLEncoder.encode(argvalue, "UTF-8");
					url_str = url_str + encoded;
				} catch (UnsupportedEncodingException e) {
					url_str = url_str + argvalue;
				}
			}
		}

	}

	public static String send() {

		String returnstring;

		returnstring = null;

		if (server == null) {
			System.out.println("sendsms.server value not set");
			return returnstring;
		}

		url_str = server + "?";
		setvar("user", user);
		setvar("password", password);
		setvar("phonenumber", phonenumber);
		setvar("text", text);
		setvar("data", data);
		setvar("udh", udh);
		setvar("pid", pid);
		setvar("dcs", dcs);
		setvar("sender", sender);
		setvar("validity", validity);
		setvar("servicetype", servicetype);
		setvar("smscroute", smscroute);
		setvar("receiptrequested", receiptrequested);
		setvar("sourceport", sourceport);
		setvar("destport", destport);
		setvar("delayuntil", delayuntil);
		setvar("voicemail", voicemail);
		setvar("wapurl", wapurl);
		setvar("wapsl", wapsl);

		try {
			URL url2 = new URL(url_str);

			HttpURLConnection connection = (HttpURLConnection) url2
					.openConnection();
			connection.setDoOutput(false);
			connection.setDoInput(true);

			String res = connection.getResponseMessage();

			System.out.println("Response Code  ->" + res);

			int code = connection.getResponseCode();

			if (code == HttpURLConnection.HTTP_OK) {
				// Get response data.
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));

				String str;

				while (null != ((str = in.readLine()))) {
					if (str.startsWith("MessageID=")) {
						returnstring = returnstring + str + "\r\n";
						System.out.println(str);
					}
				}
				connection.disconnect();
			}
		} catch (IOException e) {
			System.out.println("unable to create new url" + e.getMessage());
		}
		return returnstring;
	}

	private static void configurarServidorSMS() {
		SMSModem.init();
		SMSModem.server = "http://127.0.0.1:8800/";
		SMSModem.user = "emuchave";
		SMSModem.password = "emuchave";
	}

	public static void EnviarSMS(String num, String msg) {
		configurarServidorSMS();

		SMSModem.phonenumber = num;

		SMSModem.text = msg;
		SMSModem.send();

	}

	@SuppressWarnings("unused")
	public static void main(String args[]) {
		String body = "Exmo/a Senhor/a \n\n"
				.concat("eli" + "\t" + "Muucha" + "\n")
				.concat("Na sequencia do seu registo no Sistema de Gestão de Recursos Partilhados da UEM"
						+ ", temos o prazer de lhe enviar os seus dados de acesso a este Sistema.\n\n")
				.concat("Utilizador: " + "username" + "\n\n")
				.concat("Poderá  Aceder o sistema no seguinte endereço: expediente.uem.mz/sigep"
						+ "\n")
				.concat("\nnota: \n")
				.concat("Esta comunicação foi enviada automaticamente\nPor favor não responda a esta mensagem, porque esta caixa destina-se exclusivamente ao envio.");
		EnviarSMS("846133865", "sucesosso");

	}
}
