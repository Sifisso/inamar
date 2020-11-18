package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="param_taxas")
public class Taxa extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	String admar;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column(name="valor")
	private double valor;
	
	
    @Column(name="emolumento")
	private double emolumento;
    
    @Transient
   	private double somaTxaEmolumento;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	
	@Column(name="isLegal")
	private boolean isLegal;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoTaxa_id", insertable = true, updatable = true)
	private TipoTaxa tipoTaxa;*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subArea_id", insertable = true, updatable = true, nullable=true)
	private SubArea subArea;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxa")
	private List<TipoServico> tipoServicos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxa")
	private List<TaxaPedido> taxasPedido;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public boolean isLegal() {
		return isLegal;
	}

	public void setLegal(boolean isLegal) {
		this.isLegal = isLegal;
	}

	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public SubArea getSubArea() {
		return subArea;
	}

	public void setSubArea(SubArea subArea) {
		this.subArea = subArea;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public double getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(double emolumento) {
		this.emolumento = emolumento;
	}
	public List<TaxaPedido> getTaxasPedido() {
		return taxasPedido;
	}

	public void setTaxasPedido(List<TaxaPedido> taxasPedido) {
		this.taxasPedido = taxasPedido;
	}

	public double getSomaTxaEmolumento() {
		//somaTxaEmolumento=0;
		somaTxaEmolumento = valor+emolumento;
		return somaTxaEmolumento;
	}

	public String getAdmar() {
		return admar;
	}

	public void setAdmar(String admar) {
		this.admar = admar;
	}
	
}
