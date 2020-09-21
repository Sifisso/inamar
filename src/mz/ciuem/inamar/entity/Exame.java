package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="exame")
public class Exame extends IdEntity{

private static final long serialVersionUID = 1L;
	
	@Column(name="descricao")
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exame")
	private List<PeticaoMaritimo> peticaoMaritimo;
	
	

	public List<PeticaoMaritimo> getPeticaoMaritimo() {
		return peticaoMaritimo;
	}

	public void setPeticaoMaritimo(List<PeticaoMaritimo> peticaoMaritimo) {
		this.peticaoMaritimo = peticaoMaritimo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
