package mz.ciuem.inamar.entity.report;

import mz.ciuem.inamar.entity.IdEntity;

@SuppressWarnings("serial")
public class DesempenhoProcessual extends IdEntity{

	private String delegacao;
	private String peticoes;
	private String pagas;
	private String pagasPercentual;
	private String naoPagas;
	private String naoPagasPercentual;
	private String totalPeticoes;
	private String totalPagas;
	private String totalNaoPagas;
	private String invalidos;
	private String emTratamento;
	private String prontos;
	
	
	
	public String getDelegacao() {
		return delegacao;
	}
	public void setDelegacao(String delegacao) {
		this.delegacao = delegacao;
	}
	public String getPeticoes() {
		return peticoes;
	}
	public void setPeticoes(String peticoes) {
		this.peticoes = peticoes;
	}
	public String getPagas() {
		return pagas;
	}
	public void setPagas(String pagas) {
		this.pagas = pagas;
	}
	public String getPagasPercentual() {
		return pagasPercentual;
	}
	public void setPagasPercentual(String pagasPercentual) {
		this.pagasPercentual = pagasPercentual;
	}
	public String getNaoPagas() {
		return naoPagas;
	}
	public void setNaoPagas(String naoPagas) {
		this.naoPagas = naoPagas;
	}
	public String getNaoPagasPercentual() {
		return naoPagasPercentual;
	}
	public void setNaoPagasPercentual(String naoPagasPercentual) {
		this.naoPagasPercentual = naoPagasPercentual;
	}
	public String getTotalPeticoes() {
		return totalPeticoes;
	}
	public void setTotalPeticoes(String totalPeticoes) {
		this.totalPeticoes = totalPeticoes;
	}
	public String getTotalPagas() {
		return totalPagas;
	}
	public void setTotalPagas(String totalPagas) {
		this.totalPagas = totalPagas;
	}
	public String getTotalNaoPagas() {
		return totalNaoPagas;
	}
	public void setTotalNaoPagas(String totalNaoPagas) {
		this.totalNaoPagas = totalNaoPagas;
	}
	public String getInvalidos() {
		return invalidos;
	}
	public void setInvalidos(String invalidos) {
		this.invalidos = invalidos;
	}
	public String getEmTratamento() {
		return emTratamento;
	}
	public void setEmTratamento(String emTratamento) {
		this.emTratamento = emTratamento;
	}
	public String getProntos() {
		return prontos;
	}
	public void setProntos(String prontos) {
		this.prontos = prontos;
	}
	
}
