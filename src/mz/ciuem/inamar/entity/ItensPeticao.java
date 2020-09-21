package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itens_peticao")
public class ItensPeticao extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="custo")
	private double custo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="peticao_id", insertable = true, updatable = true)
	private Peticao peticao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public Peticao getPeticao() {
		return peticao;
	}

	public void setPeticao(Peticao peticao) {
		this.peticao = peticao;
	}
}
