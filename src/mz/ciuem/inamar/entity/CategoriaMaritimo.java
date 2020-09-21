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
@Table(name="param_categoria_maritimo")
public class CategoriaMaritimo extends IdEntity{
	
private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classeMaritimo_id", insertable = true, updatable = true)
	private ClasseMaritimo classeMaritimo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaMaritimo")
	private List<CategoriaMaritimoMaritimo> CategoriasMaritimas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaMaritimo")
	private List<PeticaoCategoriaMaritimo> peticoesCategoriaMaritimo;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaMaritimo")
	private List<PeticaoCategoriaMaritimo> peticoesCategoriaMaritimo;*/

	public List<PeticaoCategoriaMaritimo> getPeticoesCategoriaMaritimo() {
		return peticoesCategoriaMaritimo;
	}

	public void setPeticoesCategoriaMaritimo(
			List<PeticaoCategoriaMaritimo> peticoesCategoriaMaritimo) {
		this.peticoesCategoriaMaritimo = peticoesCategoriaMaritimo;
	}

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

	public ClasseMaritimo getClasseMaritimo() {
		return classeMaritimo;
	}

	public void setClasseMaritimo(ClasseMaritimo classeMaritimo) {
		this.classeMaritimo = classeMaritimo;
	}

	public List<CategoriaMaritimoMaritimo> getCategoriasMaritimas() {
		return CategoriasMaritimas;
	}

	public void setCategoriasMaritimas(
			List<CategoriaMaritimoMaritimo> categoriasMaritimas) {
		CategoriasMaritimas = categoriasMaritimas;
	}
	
	
}
