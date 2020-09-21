package mz.ciuem.inamar.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "param_distrito")
@Access(AccessType.FIELD)
public class Distrito extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "dest_designacao")
	private String designacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "distrito")
//	private List<Candidato> candidatos;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

//	public List<Candidato> getCandidatos() {
//		return candidatos;
//	}
//
//	public void setCandidatos(List<Candidato> candidatos) {
//		this.candidatos = candidatos;
//	}
	
	

}
