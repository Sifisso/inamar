package mz.ciuem.inamar.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="pagamento")
public class Pagamento extends IdEntity{

	private static final long serialVersionUID = 1L;

	private String nrDocumento;
	
	private double valorRecebido;
	
	@Column(name="data_recepcao_valor")
	@Temporal(TemporalType.DATE)
	private Date dataRecepcaoValor;
	
	private String formaRecepcao;
	
	private String conta;
	
	private String observacoes;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "peticao_id")
	private Peticao peticao;
	
	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public Date getDataRecepcaoValor() {
		return dataRecepcaoValor;
	}

	public void setDataRecepcaoValor(Date dataRecepcaoValor) {
		this.dataRecepcaoValor = dataRecepcaoValor;
	}

	public String getFormaRecepcao() {
		return formaRecepcao;
	}

	public void setFormaRecepcao(String formaRecepcao) {
		this.formaRecepcao = formaRecepcao;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

}
