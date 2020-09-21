package mz.ciuem.inamar.service;

public interface TwiloService {
	
	public void sendSMS(String to, String subject, String body) throws Exception;

}
