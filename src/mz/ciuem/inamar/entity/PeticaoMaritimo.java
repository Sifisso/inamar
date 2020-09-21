package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="peticao_maritimo")
public class PeticaoMaritimo extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column (name = "referencia")
	private String referencia;
	
	@Column(name="entidade")
	private String entidade;
	
	@Column(name="valor")
	private double valor;
	private boolean ehMaritimo;
	
	//Geral
	private String descricao;
	private String observacao;
	
	//Emissao Cedula Maritima
	private String corOlhos;
	private double altura;
	// Fim 
	
	//Averbamento de Cedula maritima
	private String nrInscricao;
	private String nrLivro;
	private String nrFolhas;
	//Fim
	
	//InscricaoMaritima
	private String categoriamaritimo;
	private String classemaritimo;
	private String grupomaritimo;
	//Fim
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exame_id", insertable = true, updatable = true)
	private Exame exame;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "peticaoMaritimo")
	private List<PeticaoCategoriaMaritimo> peticoesCategoriaMaritimo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "peticao_id")
	private Peticao peticao;
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getCorOlhos() {
		return corOlhos;
	}

	public void setCorOlhos(String corOlhos) {
		this.corOlhos = corOlhos;
	}

	public List<PeticaoCategoriaMaritimo> getPeticoesCategoriaMaritimo() {
		return peticoesCategoriaMaritimo;
	}

	public boolean isEhMaritimo() {
		return ehMaritimo;
	}

	public void setEhMaritimo(boolean ehMaritimo) {
		this.ehMaritimo = ehMaritimo;
	}


	public void setPeticoesCategoriaMaritimo(
			List<PeticaoCategoriaMaritimo> peticoesCategoriaMaritimo) {
		this.peticoesCategoriaMaritimo = peticoesCategoriaMaritimo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNrInscricao() {
		return nrInscricao;
	}

	public void setNrInscricao(String nrInscricao) {
		this.nrInscricao = nrInscricao;
	}

	public String getNrLivro() {
		return nrLivro;
	}

	public void setNrLivro(String nrLivro) {
		this.nrLivro = nrLivro;
	}

	public String getNrFolhas() {
		return nrFolhas;
	}

	public void setNrFolhas(String nrFolhas) {
		this.nrFolhas = nrFolhas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCategoriamaritimo() {
		return categoriamaritimo;
	}

	public void setCategoriamaritimo(String categoriamaritimo) {
		this.categoriamaritimo = categoriamaritimo;
	}

	public String getClassemaritimo() {
		return classemaritimo;
	}

	public void setClassemaritimo(String classemaritimo) {
		this.classemaritimo = classemaritimo;
	}

	public String getGrupomaritimo() {
		return grupomaritimo;
	}

	public void setGrupomaritimo(String grupomaritimo) {
		this.grupomaritimo = grupomaritimo;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
	
}