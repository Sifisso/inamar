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
@Table(name="classe_maritimo")
public class ClasseMaritimo extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupoMaritimo_id", insertable = true, updatable = true)
	private GrupoMaritimo grupoMaritimo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classeMaritimo")
	private List<CategoriaMaritimo> CategoriasMaritimas;

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

	public GrupoMaritimo getGrupoMaritimo() {
		return grupoMaritimo;
	}

	public void setGrupoMaritimo(GrupoMaritimo grupoMaritimo) {
		this.grupoMaritimo = grupoMaritimo;
	}

	public List<CategoriaMaritimo> getCategoriasMaritimas() {
		return CategoriasMaritimas;
	}

	public void setCategoriasMaritimas(List<CategoriaMaritimo> categoriasMaritimas) {
		CategoriasMaritimas = categoriasMaritimas;
	}
	
}
