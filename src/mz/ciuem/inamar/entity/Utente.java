package mz.ciuem.inamar.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="param_utente")
public class Utente extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "habilitacaoLiteraria")
	private String habilitacaoLiteraria;
	
	@Column(name = "isEmpresa")
	private boolean isEmpresa;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "tipoDocumento")
	private String tipoDocumento;

	@Column(name = "apelido")
	private String apelido;

	@Column(name = "nuit")
	private String nuit;

	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "nomePai")
	private String nomePai;

	@Column(name = "nomeMae")
	private String nomeMae;

	@Column(name = "genero")
	private String genero;

	@Column(name = "estadoCivil")
	private String estadoCivil;

	@Column(name = "numeroDocumento")
	private String numeroDocumento;

	@Column(name = "email")
	private String email;

	@Column(name = "celular")
	private String celular;
	
	@Column(name = "celularAlternativo")
	private String celularAlternativo;

	@Column(name = "foto")
	private String foto;

	@Column(name = "anoConclusao")
	private String anoConclusao;

	@Column(name = "provincia_nascimento")
	private String provinciaNascimento;

	@Column(name = "provincia_residencia")
	private String provinciaResidencia;

	@Column(name = "distrito_nascimento")
	private String distritoNascimento;

	@Column(name = "distrito_residencia")
	private String distritoResidencia;

	@Column(name = "pai_nascimento")
	private String paisNascimento;
	
	@Column(name = "bairro")
	private String bairro;

	@Column(name = "rua")
	private String rua;

	@Column(name = "nrCasa")
	private String nrCasa;
	
	@Column(name = "quarteirao")
	private String quarteirao;

	@Column(name = "validade")
	@Temporal(TemporalType.DATE)
	private Date validade;

	@Column(name = "local_emissao")
	private String localEmissao;

	@Column(name = "ocupacao")
	private String ocupacao;
	
	@Column(name = "tipo")
	private String tipo;
	
	//Tipo Empresa
	@Column(name = "fixoFax")
	private String fixoFax;
	
	@Column(name = "certidao_comercial")
	private String certidao_comercial;
	
	@Column(name = "registo_notarial")
	private String registo_notarial;
	
	@Column(name = "objecto_social")
	private String objecto_social;
	
	@Column(name = "nomeProprietario")
	private String nomeProprietario;
	
	@Column(name = "nomeRepresentante")
	private String nomeRepresentante;

	@Column(name = "gerencia")
	private String gerencia;
	
	@Column(name = "capital_social")
	private String capitalSocial;
	
	@Column(name = "celular_representante")
	private String celularRepresentante;

	@Column(name = "celular_representante2")
	private String celularRepresentante2;
	
	@Column(name = "email_representante")
	private String emailRepresentante;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "user_id")
	private User userLogin;
	
	//Tipo Maritimo
	private String grupo_maritimo;
	private String classe_maritimo;
	private boolean maritimo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_maritimo_id", insertable = true, updatable = true)
	private CategoriaMaritimo categoria_maritimo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userLoggado_id", insertable = true, updatable = true)
	private User userLoggado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;
	
	public String getGrupo_maritimo() {
		return grupo_maritimo;
	}

	public boolean isMaritimo() {
		return maritimo;
	}

	public void setMaritimo(boolean maritimo) {
		this.maritimo = maritimo;
	}

	public void setGrupo_maritimo(String grupo_maritimo) {
		this.grupo_maritimo = grupo_maritimo;
	}

	public String getClasse_maritimo() {
		return classe_maritimo;
	}

	public void setClasse_maritimo(String classe_maritimo) {
		this.classe_maritimo = classe_maritimo;
	}

	public CategoriaMaritimo getCategoria_maritimo() {
		return categoria_maritimo;
	}

	public void setCategoria_maritimo(CategoriaMaritimo categoria_maritimo) {
		this.categoria_maritimo = categoria_maritimo;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getNomeRepresentante() {
		return nomeRepresentante;
	}

	public void setNomeRepresentante(String nomeRepresentante) {
		this.nomeRepresentante = nomeRepresentante;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(String capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public String getCelularRepresentante() {
		return celularRepresentante;
	}

	public void setCelularRepresentante(String celularRepresentante) {
		this.celularRepresentante = celularRepresentante;
	}

	public String getCelularRepresentante2() {
		return celularRepresentante2;
	}

	public void setCelularRepresentante2(String celularRepresentante2) {
		this.celularRepresentante2 = celularRepresentante2;
	}

	public String getEmailRepresentante() {
		return emailRepresentante;
	}

	public void setEmailRepresentante(String emailRepresentante) {
		this.emailRepresentante = emailRepresentante;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getHabilitacaoLiteraria() {
		return habilitacaoLiteraria;
	}

	public void setHabilitacaoLiteraria(String habilitacaoLiteraria) {
		this.habilitacaoLiteraria = habilitacaoLiteraria;
	}

	public boolean isEmpresa() {
		return isEmpresa;
	}

	public void setEmpresa(boolean isEmpresa) {
		this.isEmpresa = isEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getNuit() {
		return nuit;
	}

	public void setNuit(String nuit) {
		this.nuit = nuit;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public String getCelularAlternativo() {
		return celularAlternativo;
	}

	public void setCelularAlternativo(String celularAlternativo) {
		this.celularAlternativo = celularAlternativo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getAnoConclusao() {
		return anoConclusao;
	}

	public void setAnoConclusao(String anoConclusao) {
		this.anoConclusao = anoConclusao;
	}

	public String getProvinciaNascimento() {
		return provinciaNascimento;
	}

	public void setProvinciaNascimento(String provinciaNascimento) {
		this.provinciaNascimento = provinciaNascimento;
	}

	public String getProvinciaResidencia() {
		return provinciaResidencia;
	}

	public void setProvinciaResidencia(String provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	public String getDistritoNascimento() {
		return distritoNascimento;
	}

	public void setDistritoNascimento(String distritoNascimento) {
		this.distritoNascimento = distritoNascimento;
	}

	public String getDistritoResidencia() {
		return distritoResidencia;
	}

	public void setDistritoResidencia(String distritoResidencia) {
		this.distritoResidencia = distritoResidencia;
	}

	public String getPaisNascimento() {
		return paisNascimento;
	}

	public void setPaisNascimento(String paisNascimento) {
		this.paisNascimento = paisNascimento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNrCasa() {
		return nrCasa;
	}

	public void setNrCasa(String nrCasa) {
		this.nrCasa = nrCasa;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getLocalEmissao() {
		return localEmissao;
	}

	public void setLocalEmissao(String localEmissao) {
		this.localEmissao = localEmissao;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getQuarteirao() {
		return quarteirao;
	}

	public void setQuarteirao(String quarteirao) {
		this.quarteirao = quarteirao;
	}

	public User getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	public String getFixoFax() {
		return fixoFax;
	}

	public void setFixoFax(String fixoFax) {
		this.fixoFax = fixoFax;
	}

	public String getCertidao_comercial() {
		return certidao_comercial;
	}

	public void setCertidao_comercial(String certidao_comercial) {
		this.certidao_comercial = certidao_comercial;
	}

	public String getRegisto_notarial() {
		return registo_notarial;
	}

	public void setRegisto_notarial(String registo_notarial) {
		this.registo_notarial = registo_notarial;
	}

	public String getObjecto_social() {
		return objecto_social;
	}

	public void setObjecto_social(String objecto_social) {
		this.objecto_social = objecto_social;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public User getUserLoggado() {
		return userLoggado;
	}

	public void setUserLoggado(User userLoggado) {
		this.userLoggado = userLoggado;
	}

	public Delegacao getDelegacao() {
		return delegacao;
	}

	public void setDelegacao(Delegacao delegacao) {
		this.delegacao = delegacao;
	}

}
