package mz.ciuem.inamar.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "email")
@Access(AccessType.FIELD)
public class Email extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private String subject;
	private String body;
	private boolean isEnviada;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isEnviada() {
		return isEnviada;
	}

	public void setEnviada(boolean isEnviada) {
		this.isEnviada = isEnviada;
	}

}
