package mz.ciuem.inamar.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="peticao_categoria_maritimo")
public class PeticaoCategoriaMaritimo extends IdEntity{
	
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "peticaoMaritimo_id", insertable = true, updatable = true, nullable=true)
	private PeticaoMaritimo peticaoMaritimo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaMaritimo_id", insertable = true, updatable = true, nullable=true)
	private CategoriaMaritimo categoriaMaritimo;

	public PeticaoMaritimo getPeticaoMaritimo() {
		return peticaoMaritimo;
	}

	public void setPeticaoMaritimo(PeticaoMaritimo peticaoMaritimo) {
		this.peticaoMaritimo = peticaoMaritimo;
	}

	public CategoriaMaritimo getCategoriaMaritimo() {
		return categoriaMaritimo;
	}

	public void setCategoriaMaritimo(CategoriaMaritimo categoriaMaritimo) {
		this.categoriaMaritimo = categoriaMaritimo;
	}
	
	

}
