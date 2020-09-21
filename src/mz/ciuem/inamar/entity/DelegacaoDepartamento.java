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
@Table(name="para_delegacao_departamento")
public class DelegacaoDepartamento extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="isActivol")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id", insertable = true, updatable = true)
	private Departamento departamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegacao_id", insertable = true, updatable = true)
	private Delegacao delegacao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "delegacaoDepartamento")
	private List<DelegacaoDepartamentoSector> delegDepSectores;

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Delegacao getDelegacao() {
		return delegacao;
	}

	public void setDelegacao(Delegacao delegacao) {
		this.delegacao = delegacao;
	}

	public List<DelegacaoDepartamentoSector> getDelegDepSectores() {
		return delegDepSectores;
	}

	public void setDelegDepSectores(
			List<DelegacaoDepartamentoSector> delegDepSectores) {
		this.delegDepSectores = delegDepSectores;
	}
	
    
}
