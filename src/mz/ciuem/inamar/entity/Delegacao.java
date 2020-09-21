package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_delegacao")
public class Delegacao extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome_delegacao")
	private String nome;
	
	@Column (name = "codigo")
	private int codigo;
	
	@Column(name="entidade", nullable=true)
	private String entidade;
	
	@Column(name="nuit")
	private String nuit;
	
	@Column(name="nrContaBim")
	private String nrContaBim;
	
	@Column(name="nrContaBci")
	private String nrContaBci;
	
	@Column(name="nrContaBarclays")
	private String nrContaBarclays;
	
	
	@Column(name="nibBim")
	private String nibBim;
	
	@Column(name="nibBci")
	private String nibBci;
	
	@Column(name="nibBarclays")
	private String nibBarclays;
	
	@Column(name="av_rua")
	private String av_rua;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="quarteirao_andar")
	private String quarteirao_andar;
	
	@Column(name="isAdmar")
	private boolean isAdmar;
	
	@Column(name="isEntidadePropria")
	private boolean isEntidadePropria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instituicao_id", insertable = true, updatable = true)
	private Instituicao instituicao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provincia_id", insertable = true, updatable = true)
	private Provincia provincia;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "delegacao")
	private List<Conta> contas;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "delegacao")
	private List<Utente> utentes;*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public boolean isAdmar() {
		return isAdmar;
	}

	public void setAdmar(boolean isAdmar) {
		this.isAdmar = isAdmar;
	}

	public boolean isEntidadePropria() {
		return isEntidadePropria;
	}

	public void setEntidadePropria(boolean isEntidadePropria) {
		this.isEntidadePropria = isEntidadePropria;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNuit() {
		return nuit;
	}

	public void setNuit(String nuit) {
		this.nuit = nuit;
	}

	public String getNrContaBim() {
		return nrContaBim;
	}

	public void setNrContaBim(String nrContaBim) {
		this.nrContaBim = nrContaBim;
	}

	public String getNrContaBci() {
		return nrContaBci;
	}

	public void setNrContaBci(String nrContaBci) {
		this.nrContaBci = nrContaBci;
	}

	public String getNrContaBarclays() {
		return nrContaBarclays;
	}

	public void setNrContaBarclays(String nrContaBarclays) {
		this.nrContaBarclays = nrContaBarclays;
	}

	public String getNibBim() {
		return nibBim;
	}

	public void setNibBim(String nibBim) {
		this.nibBim = nibBim;
	}

	public String getNibBci() {
		return nibBci;
	}

	public void setNibBci(String nibBci) {
		this.nibBci = nibBci;
	}

	public String getNibBarclays() {
		return nibBarclays;
	}

	public void setNibBarclays(String nibBarclays) {
		this.nibBarclays = nibBarclays;
	}

	public String getAv_rua() {
		return av_rua;
	}

	public void setAv_rua(String av_rua) {
		this.av_rua = av_rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getQuarteirao_andar() {
		return quarteirao_andar;
	}

	public void setQuarteirao_andar(String quarteirao_andar) {
		this.quarteirao_andar = quarteirao_andar;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
}
