package mz.ciuem.inamar.entity.report;

import mz.ciuem.inamar.entity.IdEntity;

@SuppressWarnings("serial")
public class DesempenhoProcessualNaoFinanceiro extends IdEntity{

	private String delegacao;
	private String peticoes;
	private String invalidos;
	private String invalidosPercentual;
	private String emTratamento;
	private String emTratamentoPercentual;
	private String prontos;
	private String prontosPercentual;
	
	private String totalInvalidos;
	private String totalEmTratamento;
	private String totalProntos;
	private String totalPeticoes;
	
	
	
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
	public String getTotalInvalidos() {
		return totalInvalidos;
	}
	public void setTotalInvalidos(String totalInvalidos) {
		this.totalInvalidos = totalInvalidos;
	}
	public String getTotalEmTratamento() {
		return totalEmTratamento;
	}
	public void setTotalEmTratamento(String totalEmTratamento) {
		this.totalEmTratamento = totalEmTratamento;
	}
	public String getTotalProntos() {
		return totalProntos;
	}
	public void setTotalProntos(String totalProntos) {
		this.totalProntos = totalProntos;
	}
	public String getTotalPeticoes() {
		return totalPeticoes;
	}
	public void setTotalPeticoes(String totalPeticoes) {
		this.totalPeticoes = totalPeticoes;
	}
	public String getInvalidosPercentual() {
		return invalidosPercentual;
	}
	public void setInvalidosPercentual(String invalidosPercentual) {
		this.invalidosPercentual = invalidosPercentual;
	}
	public String getEmTratamentoPercentual() {
		return emTratamentoPercentual;
	}
	public void setEmTratamentoPercentual(String emTratamentoPercentual) {
		this.emTratamentoPercentual = emTratamentoPercentual;
	}
	public String getProntosPercentual() {
		return prontosPercentual;
	}
	public void setProntosPercentual(String prontosPercentual) {
		this.prontosPercentual = prontosPercentual;
	}
	
}
