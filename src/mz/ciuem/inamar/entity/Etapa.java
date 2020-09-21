package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_etapa")
public class Etapa extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etapa")
	private List<EtapaFluxo> etapaFluxo;

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

	public List<EtapaFluxo> getEtapaFluxo() {
		return etapaFluxo;
	}

	public void setEtapaFluxo(List<EtapaFluxo> etapaFluxo) {
		this.etapaFluxo = etapaFluxo;
	}
	
	

}
