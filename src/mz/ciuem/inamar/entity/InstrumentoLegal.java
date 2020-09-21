package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="instruemnto_legal")
public class InstrumentoLegal  extends IdEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instrumentoLegal")
	private List<PedidoEtapa> pedidosEtapas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

	public List<PedidoEtapa> getPedidosEtapas() {
		return pedidosEtapas;
	}

	public void setPedidosEtapas(List<PedidoEtapa> pedidosEtapas) {
		this.pedidosEtapas = pedidosEtapas;
	}
	
}
