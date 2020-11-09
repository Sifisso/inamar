package mz.ciuem.inamar.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import mz.ciuem.inamar.service.TaxaPedidoService;
import mz.ciuem.inamar.tesouraria.controller.PeticaoMaritmaTaxaPedidoCtrl;

import org.bouncycastle.mail.smime.handlers.pkcs7_mime;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="param_peticao")
public class Peticao extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column (name = "referencia")
	private String referencia;
	
	@Column(name="entidade")
	private String entidade;
	
	@Column(name="nrFactura")
	private String nrFactura;
	
	//Nao definido
	@Column(name="nrExpediente")
	private String nrExpediente;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="utente")
	private String utente;
	
	@Column(name="localizacao")
	private String localizacao;
	
	@Column(name="descricao")
	private String descricao;
	
	@Transient
	private double valorTaxa;
	
	private String observacao;
	
	private String parecer;
	
	private String observacaoChefeSecretaria;
	
	private String parecerChefeSecretaria;
	
	private String motivoRecusa;
	
	private String parecer2;
	
	//Valores
	@Column(name="valor")
	private double valor;
	
	@Transient
	private double valorTotal;
	private double taxa;
	
	//Estados
	private boolean pago;
	
	@Column(name="isValidado")
	private boolean isValidado; //1
	
	@Column(name="prevalidado")
	private boolean isPreValidado; //1
	
	@Column(name="isRecusado")
	private boolean isRecusado;
	
	@Column(name="naoMostraPago")
	private boolean naoMostraPago; // Usada para o control de visualizacao de pago
	
	@Column(name="nao_visivel")
	private boolean naoVisivel; //Se for true, a petição não será visível, NB: ao registar sempre será false
	
	private boolean pedeParecer; //1
	
	private boolean temResposta; // Fica True quando chefe de secretaria responde a um pedido de parecer do Administrador maritmo
	
	private boolean temRespostaSTecnica;  // Fica True quando a seccao tecnica responde a um pedido de parecer do Administrador maritmo
	
	private boolean autorizada; //1
	
	public boolean isFacturada() {
		return facturada;
	}

	public void setFacturada(boolean facturada) {
		this.facturada = facturada;
	}

	private boolean facturada; // O atributo fica true quando a tesouraria emite uma factura
	
	private boolean tratada;
	
	private boolean realizada;
	
	private boolean terminada;
	
	//Visibilidade
	private boolean secretaria;
	private boolean secretaria2;
	private boolean secretariaParecer;
	private boolean chefeSecretaria;
	private boolean chefeSecretaria2;
	private boolean chefeSecretariaParecer;
	private boolean tesouraria;
	private boolean tesouraria2;
	private boolean tesourariaParecer;
	private boolean admMaritima;
	private boolean admMaritima2;
	private boolean admMaritima3;
	private boolean admMaritimaParecer;
	private boolean seccaoTecnica;
	private boolean seccaoTecnica2;
	private boolean seccaoTecnicaParecer;
	private boolean utente2;
	private boolean utenteParecer;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id", insertable = true, updatable = true)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userLoggado_id", insertable = true, updatable = true)
	private User userLoggado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "peticao1Maritimo_id")
	private PeticaoMaritimo peticaoMaritimo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "peticaoEmbarcacao_id")
	private PeticaoEmbarcacao peticaoEmbarcacao;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticao")
	private List<PeticaoPedidoRequisito> peticoesPedidoRequisito;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "peticao")
	private List<PeticaoMaritimoTaxaPedido> peticoesTaxaPedido;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticao")
	private List<PeticaoEtapa> peticoesEtapas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticao")
	private List<PeticaoPedidoEtapaInstrumentoLegal> peticoesInstrumentoLegal;
	
	public List<PeticaoPedidoEtapaInstrumentoLegal> getPeticoesInstrumentoLegal() {
		return peticoesInstrumentoLegal;
	}

	public void setPeticoesInstrumentoLegal(
			List<PeticaoPedidoEtapaInstrumentoLegal> peticoesInstrumentoLegal) {
		this.peticoesInstrumentoLegal = peticoesInstrumentoLegal;
	}

	public PeticaoEmbarcacao getPeticaoEmbarcacao() {
		return peticaoEmbarcacao;
	}

	public List<PeticaoPedidoRequisito> getPeticoesPedidoRequisito() {
		return peticoesPedidoRequisito;
	}

	public void setPeticoesPedidoRequisito(
			List<PeticaoPedidoRequisito> peticoesPedidoRequisito) {
		this.peticoesPedidoRequisito = peticoesPedidoRequisito;
	}

	public List<PeticaoMaritimoTaxaPedido> getPeticoesTaxaPedido() {
		return peticoesTaxaPedido;
	}

	public void setPeticoesTaxaPedido(
			List<PeticaoMaritimoTaxaPedido> peticoesTaxaPedido) {
		this.peticoesTaxaPedido = peticoesTaxaPedido;
	}

	public void setPeticaoEmbarcacao(PeticaoEmbarcacao peticaoEmbarcacao) {
		this.peticaoEmbarcacao = peticaoEmbarcacao;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	

	public void setValor(double valor) {
		this.valor = valor;
	}
	

	public double getValor() {
		return valor;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}


	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public PeticaoMaritimo getPeticaoMaritimo() {
		return peticaoMaritimo;
	}

	public void setPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo) {
		this.peticaoMaritimo = peticaoMaritimo;
	}

	public boolean isValidado() {
		return isValidado;
	}

	public void setValidado(boolean isValidado) {
		this.isValidado = isValidado;
	}

	public boolean isRecusado() {
		return isRecusado;
	}

	public void setRecusado(boolean isRecusado) {
		this.isRecusado = isRecusado;
	}

	public String getNrFactura() {
		return nrFactura;
	}

	public void setNrFactura(String nrFactura) {
		this.nrFactura = nrFactura;
	}

	public String getNrExpediente() {
		return nrExpediente;
	}

	public void setNrExpediente(String nrExpediente) {
		this.nrExpediente = nrExpediente;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public boolean isPedeParecer() {
		return pedeParecer;
	}

	public void setPedeParecer(boolean pedeParecer) {
		this.pedeParecer = pedeParecer;
	}

	public boolean isTemResposta() {
		return temResposta;
	}

	public void setTemResposta(boolean temResposta) {
		this.temResposta = temResposta;
	}

	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}

	public boolean isTratada() {
		return tratada;
	}

	public void setTratada(boolean tratada) {
		this.tratada = tratada;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

	public boolean isTerminada() {
		return terminada;
	}

	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}

	public boolean isSecretaria() {
		return secretaria;
	}

	public void setSecretaria(boolean secretaria) {
		this.secretaria = secretaria;
	}

	public boolean isSecretaria2() {
		return secretaria2;
	}

	public void setSecretaria2(boolean secretaria2) {
		this.secretaria2 = secretaria2;
	}

	public boolean isTesouraria() {
		return tesouraria;
	}

	public void setTesouraria(boolean tesouraria) {
		this.tesouraria = tesouraria;
	}

	public boolean isAdmMaritima() {
		return admMaritima;
	}

	public void setAdmMaritima(boolean admMaritima) {
		this.admMaritima = admMaritima;
	}

	public boolean isAdmMaritima2() {
		return admMaritima2;
	}

	public void setAdmMaritima2(boolean admMaritima2) {
		this.admMaritima2 = admMaritima2;
	}

	public boolean isAdmMaritima3() {
		return admMaritima3;
	}

	public void setAdmMaritima3(boolean admMaritima3) {
		this.admMaritima3 = admMaritima3;
	}

	public boolean isSeccaoTecnica() {
		return seccaoTecnica;
	}

	public void setSeccaoTecnica(boolean seccaoTecnica) {
		this.seccaoTecnica = seccaoTecnica;
	}

	public boolean isSeccaoTecnica2() {
		return seccaoTecnica2;
	}

	public void setSeccaoTecnica2(boolean seccaoTecnica2) {
		this.seccaoTecnica2 = seccaoTecnica2;
	}

	public boolean isUtente2() {
		return utente2;
	}

	public void setUtente2(boolean utente2) {
		this.utente2 = utente2;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getParecer2() {
		return parecer2;
	}

	public void setParecer2(String parecer2) {
		this.parecer2 = parecer2;
	}

	public boolean isPreValidado() {
		return isPreValidado;
	}

	public void setPreValidado(boolean isPreValidado) {
		this.isPreValidado = isPreValidado;
	}

	public boolean isChefeSecretaria() {
		return chefeSecretaria;
	}

	public void setChefeSecretaria(boolean chefeSecretaria) {
		this.chefeSecretaria = chefeSecretaria;
	}

	public String getObservacaoChefeSecretaria() {
		return observacaoChefeSecretaria;
	}

	public void setObservacaoChefeSecretaria(String observacaoChefeSecretaria) {
		this.observacaoChefeSecretaria = observacaoChefeSecretaria;
	}

	public String getParecerChefeSecretaria() {
		return parecerChefeSecretaria;
	}

	public void setParecerChefeSecretaria(String parecerChefeSecretaria) {
		this.parecerChefeSecretaria = parecerChefeSecretaria;
	}

	public List<PeticaoEtapa> getPeticoesEtapas() {
		return peticoesEtapas;
	}

	public void setPeticoesEtapas(List<PeticaoEtapa> peticoesEtapas) {
		this.peticoesEtapas = peticoesEtapas;
	}

	public boolean isSecretariaParecer() {
		return secretariaParecer;
	}

	public void setSecretariaParecer(boolean secretariaParecer) {
		this.secretariaParecer = secretariaParecer;
	}

	public boolean isChefeSecretaria2() {
		return chefeSecretaria2;
	}

	public void setChefeSecretaria2(boolean chefeSecretaria2) {
		this.chefeSecretaria2 = chefeSecretaria2;
	}

	public boolean isChefeSecretariaParecer() {
		return chefeSecretariaParecer;
	}

	public void setChefeSecretariaParecer(boolean chefeSecretariaParecer) {
		this.chefeSecretariaParecer = chefeSecretariaParecer;
	}

	public boolean isTesouraria2() {
		return tesouraria2;
	}

	public void setTesouraria2(boolean tesouraria2) {
		this.tesouraria2 = tesouraria2;
	}

	public boolean isTesourariaParecer() {
		return tesourariaParecer;
	}

	public void setTesourariaParecer(boolean tesourariaParecer) {
		this.tesourariaParecer = tesourariaParecer;
	}

	public boolean isAdmMaritimaParecer() {
		return admMaritimaParecer;
	}

	public void setAdmMaritimaParecer(boolean admMaritimaParecer) {
		this.admMaritimaParecer = admMaritimaParecer;
	}

	public boolean isSeccaoTecnicaParecer() {
		return seccaoTecnicaParecer;
	}

	public void setSeccaoTecnicaParecer(boolean seccaoTecnicaParecer) {
		this.seccaoTecnicaParecer = seccaoTecnicaParecer;
	}

	public boolean isUtenteParecer() {
		return utenteParecer;
	}

	public void setUtenteParecer(boolean utenteParecer) {
		this.utenteParecer = utenteParecer;
	}

	public boolean isTemRespostaSTecnica() {
		return temRespostaSTecnica;
	}

	public void setTemRespostaSTecnica(boolean temRespostaSTecnica) {
		this.temRespostaSTecnica = temRespostaSTecnica;
	}

	

	public double getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(double valorTaxa) {
		this.valorTaxa = valorTaxa;
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

	public String getMotivoRecusa() {
		return motivoRecusa;
	}

	public void setMotivoRecusa(String motivoRecusa) {
		this.motivoRecusa = motivoRecusa;
	}
	

	public double getValorTotal() {
		valorTotal=0;
		for(PeticaoMaritimoTaxaPedido pmtp:peticoesTaxaPedido){
			valorTotal=valorTotal+pmtp.getTaxaPedido().getTaxa().getValor()+pmtp.getTaxaPedido().getTaxa().getEmolumento();
		}
		return valorTotal;
	}

	public boolean isNaoMostraPago() {
		return naoMostraPago;
	}

	public void setNaoMostraPago(boolean naoMostraPago) {
		this.naoMostraPago = naoMostraPago;
	}

	public boolean isNaoVisivel() {
		return naoVisivel;
	}

	public void setNaoVisivel(boolean naoVisivel) {
		this.naoVisivel = naoVisivel;
	}

}
