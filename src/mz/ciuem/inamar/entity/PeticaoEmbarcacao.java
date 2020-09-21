package mz.ciuem.inamar.entity;

import java.util.List;

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
@Table(name="peticao_embarcacao")
public class PeticaoEmbarcacao extends IdEntity{
	
private static final long serialVersionUID = 1L;
	
	@Column (name = "referencia")
	private String referencia;
	
	@Column(name="entidade")
	private String entidade;
	
	@Column(name="valor")
	private double valor;
	private boolean ehEmbarcacao;
	
	//Geral
	private String descricao;
	private String observacao;
	
	//Embarcacao Importada
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id", insertable = true, updatable = true)
	private Pais pais;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classe_embarcacao_id", insertable = true, updatable = true)
	private ClasseEmbarcacao classeEmbarcacao;
	
	private String denominacao;
	private String servico;
	private boolean ehTemporario;
	// Fim 
	
	
	//Embarcacao Importada
	private double comprimento;
	private double pontal;
	private double boca;
	//Fim
	
	//REgisto de uma embarcacao acabada de construir
    private String localConstrucao;	
	//Fim
    
    //CAncelamento de uma embarcacao por ter sido vendida
    private String numero_registo;
    private double precoVenda;
    private String nome_novo_dono;
    private String moeda;
    //Fim
    
    //REgisto de Embarcacao acabada de comprar
    private double preco_aquisicao;
    private String antiga_denominacao;
    private String local_registo;
    private String nome_antigo_dono;
    private String nova_denominacao;
    //Fim
    
    //Titulo de Propriedade
    private String motivo;
    //Fim
    
    
	
	
	//Remover
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servico_id", insertable = true, updatable = true)
	private ServicoDestino servicoDestino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "peticao_id")
	private Peticao peticao;

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isEhEmbarcacao() {
		return ehEmbarcacao;
	}

	public void setEhEmbarcacao(boolean ehEmbarcacao) {
		this.ehEmbarcacao = ehEmbarcacao;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public ClasseEmbarcacao getClasseEmbarcacao() {
		return classeEmbarcacao;
	}

	public void setClasseEmbarcacao(ClasseEmbarcacao classeEmbarcacao) {
		this.classeEmbarcacao = classeEmbarcacao;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public boolean isEhTemporario() {
		return ehTemporario;
	}

	public void setEhTemporario(boolean ehTemporario) {
		this.ehTemporario = ehTemporario;
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


	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}

	public ServicoDestino getServicoDestino() {
		return servicoDestino;
	}

	public void setServicoDestino(ServicoDestino servicoDestino) {
		this.servicoDestino = servicoDestino;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getPontal() {
		return pontal;
	}

	public void setPontal(double pontal) {
		this.pontal = pontal;
	}

	public double getBoca() {
		return boca;
	}

	public void setBoca(double boca) {
		this.boca = boca;
	}

	public String getLocalConstrucao() {
		return localConstrucao;
	}

	public void setLocalConstrucao(String localConstrucao) {
		this.localConstrucao = localConstrucao;
	}

	public String getNumero_registo() {
		return numero_registo;
	}

	public void setNumero_registo(String numero_registo) {
		this.numero_registo = numero_registo;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getNome_novo_dono() {
		return nome_novo_dono;
	}

	public void setNome_novo_dono(String nome_novo_dono) {
		this.nome_novo_dono = nome_novo_dono;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public double getPreco_aquisicao() {
		return preco_aquisicao;
	}

	public void setPreco_aquisicao(double preco_aquisicao) {
		this.preco_aquisicao = preco_aquisicao;
	}

	public String getAntiga_denominacao() {
		return antiga_denominacao;
	}

	public void setAntiga_denominacao(String antiga_denominacao) {
		this.antiga_denominacao = antiga_denominacao;
	}

	public String getLocal_registo() {
		return local_registo;
	}

	public void setLocal_registo(String local_registo) {
		this.local_registo = local_registo;
	}

	public String getNome_antigo_dono() {
		return nome_antigo_dono;
	}

	public void setNome_antigo_dono(String nome_antigo_dono) {
		this.nome_antigo_dono = nome_antigo_dono;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getNova_denominacao() {
		return nova_denominacao;
	}

	public void setNova_denominacao(String nova_denominacao) {
		this.nova_denominacao = nova_denominacao;
	}

	
	
}
