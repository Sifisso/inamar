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
@Table(name="tbl_embarcacao")
public class Embarcacao extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "propreitario")
	private String propreitario;

	@Column(name = "isActivo")
	private boolean isActivo;
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "tonelagem")
	private String tonelagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id", insertable = true, updatable = true)
	private Pais nacionalidade;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "embarcacao")
	private List<Entrada> entradas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "embarcacao")
	private List<EmbarcacaoAcontecimento>acontecimentos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userLoggado_id", insertable = true, updatable = true)
	private User userLoggado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isActivo() {
		return isActivo;
	}
	
	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTonelagem() {
		return tonelagem;
	}

	public void setTonelagem(String tonelagem) {
		this.tonelagem = tonelagem;
	}

	public Pais getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Pais nacionalidade) {
		this.nacionalidade = nacionalidade;
	}



	public String getPropreitario() {
		return propreitario;
	}

	public void setPropreitario(String propreitario) {
		this.propreitario = propreitario;
	}


	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public List<EmbarcacaoAcontecimento> getAcontecimentos() {
		return acontecimentos;
	}

	public void setAcontecimentos(List<EmbarcacaoAcontecimento> acontecimentos) {
		this.acontecimentos = acontecimentos;
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
