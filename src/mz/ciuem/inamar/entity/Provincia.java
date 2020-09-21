package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "param_provincia")
@Access(AccessType.FIELD)
public class Provincia extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "prov_desiganacao")
	private String designacao;


	@OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
	private List<Distrito> distritos;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id")
	private Pais pais;


	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	
	

}
