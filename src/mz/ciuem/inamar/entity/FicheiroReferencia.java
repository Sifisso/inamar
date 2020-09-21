package mz.ciuem.inamar.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import mz.ciuem.inamar.entity.IdEntity;

@Entity
@Table(name = "ficheiro_referencia")
@Access(AccessType.FIELD)
public class FicheiroReferencia extends IdEntity{
	
	
	@Column(name = "URI")
	private String URI;
	
	@Column(name = "banco")
	private String banco;
	
	@Column(name = "last_read")
	private int line;
	
	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	@Column(name = "lido")
	private boolean isLido;

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}



	public boolean isLido() {
		return isLido;
	}

	public void setLido(boolean isLido) {
		this.isLido = isLido;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	
	

}
