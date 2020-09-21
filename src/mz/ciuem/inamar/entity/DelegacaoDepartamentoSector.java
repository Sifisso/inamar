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
@Table(name="param_delegacao_departamento_sector")
public class DelegacaoDepartamentoSector  extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="isActivol")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacaoDep_id", insertable = true, updatable = true)
	private DelegacaoDepartamento delegacaoDepartamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sectorr_id", insertable = true, updatable = true)
	private Sectorr sectorr;
	
	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public DelegacaoDepartamento getDelegacaoDepartamento() {
		return delegacaoDepartamento;
	}

	public void setDelegacaoDepartamento(DelegacaoDepartamento delegacaoDepartamento) {
		this.delegacaoDepartamento = delegacaoDepartamento;
	}

	public Sectorr getSectorr() {
		return sectorr;
	}

	public void setSectorr(Sectorr sectorr) {
		this.sectorr = sectorr;
	}
	
	

}
