package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_departamento")
public class Departamento extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="nome_dep")
	private String nome;
	
	@Column(name="sigla_dep")
	private String silgla;
	
	@Column(name="isAdmar")
	private boolean isAdmar;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento")
	private List<DelegacaoDepartamento> delegacaoDepartamentos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSilgla() {
		return silgla;
	}

	public void setSilgla(String silgla) {
		this.silgla = silgla;
	}

	public boolean isAdmar() {
		return isAdmar;
	}

	public void setAdmar(boolean isAdmar) {
		this.isAdmar = isAdmar;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public List<DelegacaoDepartamento> getDelegacaoDepartamentos() {
		return delegacaoDepartamentos;
	}

	public void setDelegacaoDepartamentos(
			List<DelegacaoDepartamento> delegacaoDepartamentos) {
		this.delegacaoDepartamentos = delegacaoDepartamentos;
	}

	@Override
	public String toString() {
		return ""+ nome ;
	}
	
	

}
