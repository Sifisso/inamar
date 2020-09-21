package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zkoss.zk.ui.util.GenericForwardComposer;

@Entity
@Table(name="param_instituicao")
@Access(AccessType.FIELD)
public class Instituicao extends IdEntity{


	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="entidade")
	private String entidade;
	
	@Column(name="provincia")
	private String provincia;
	
	@Column(name="av_rua")
	private String av_rua;
	
	@Column (name = "codigo")
	private String codigo;
	
	@Column(name="nuit")
	private String nuit;
	
	@Column(name="email")
	private String email;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="quarteirao_andar")
	private String quarteirao_andar;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicao")
	private List<Delegacao> delegacoes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicao")
	private List<Conta> contas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}


	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getAv_rua() {
		return av_rua;
	}

	public void setAv_rua(String av_rua) {
		this.av_rua = av_rua;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNuit() {
		return nuit;
	}

	public void setNuit(String nuit) {
		this.nuit = nuit;
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

	public List<Delegacao> getDelegacoes() {
		return delegacoes;
	}

	public void setDelegacoes(List<Delegacao> delegacoes) {
		this.delegacoes = delegacoes;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}
