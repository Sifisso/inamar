package mz.ciuem.inamar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_entrada")
public class Entrada extends IdEntity{
 
	@Column(name="dataEntrada")
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	
	@Column(name="dataSaida")
	@Temporal(TemporalType.DATE)
	private Date dataSaida;
	
	
	@Column(name="portoEntrada")
	private String portoEntrada;
	
	@Column(name="cais")
	private String cais;
	
	@Column(name="agencia")
	private String agencia;
	
	@Column(name="nrOrdem")
	private int nrOrdem;
	
	@Column(name="horaEntrada")
	@Temporal(TemporalType.TIME)
	private Date horaEntrada;
	
	@Column(name="livrePratico")
	@Temporal(TemporalType.TIME)
	private Date livrePratico;
	
	@Column(name="horaSaida")
	@Temporal(TemporalType.TIME)
	private Date horaSaida;
	
	@Column(name="calado")
	private String calado;
	
	@Column(name="caladoSaida")
	private String caladoSaida;
	
	
	@Column(name="funServEntrada")
	private String funServEntrada;
	
	@Column(name="funServSaida")
	private String funServSaida;
	
	@Column(name = "motivo")
	private String motivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "embarcacao_id", insertable = true, updatable = true)
	private Embarcacao embarcacao;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id", insertable = true, updatable = true)
	private Pais pais;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destino_id", insertable = true, updatable = true)
	private Pais destino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userLoggado_id", insertable = true, updatable = true)
	private User userLoggado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "porto_id", insertable = true, updatable = true)
	private Porto porto;

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getPortoEntrada() {
		return portoEntrada;
	}

	public void setPortoEntrada(String portoEntrada) {
		this.portoEntrada = portoEntrada;
	}

	public String getCais() {
		return cais;
	}

	public void setCais(String cais) {
		this.cais = cais;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public int getNrOrdem() {
		return nrOrdem;
	}

	public void setNrOrdem(int nrOrdem) {
		this.nrOrdem = nrOrdem;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getCalado() {
		return calado;
	}

	public void setCalado(String calado) {
		this.calado = calado;
	}


	public String getFunServEntrada() {
		return funServEntrada;
	}

	public void setFunServEntrada(String funServEntrada) {
		this.funServEntrada = funServEntrada;
	}

	public Embarcacao getEmbarcacao() {
		return embarcacao;
	}

	public void setEmbarcacao(Embarcacao embarcacao) {
		this.embarcacao = embarcacao;
	}
	
	
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Date getLivrePratico() {
		return livrePratico;
	}

	public void setLivrePratico(Date livrePratico) {
		this.livrePratico = livrePratico;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getCaladoSaida() {
		return caladoSaida;
	}

	public void setCaladoSaida(String caladoSaida) {
		this.caladoSaida = caladoSaida;
	}

	public String getFunServSaida() {
		return funServSaida;
	}

	public void setFunServSaida(String funServSaida) {
		this.funServSaida = funServSaida;
	}

	public Pais getDestino() {
		return destino;
	}

	public void setDestino(Pais destino) {
		this.destino = destino;
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

	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}
}
