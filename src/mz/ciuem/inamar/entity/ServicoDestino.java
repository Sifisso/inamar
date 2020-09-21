package mz.ciuem.inamar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="param_servico_destino")
public class ServicoDestino extends IdEntity{
	

	private static final long serialVersionUID = 1L;
	
	@Column (name = "nome")
	private String nome;
	
	@Column(name="isActivo")
	private boolean isActivo;
	
	//Relacoes
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servicoDestino")
	private List<PeticaoEmbarcacao> peticoesEmbarcacao;

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

	public List<PeticaoEmbarcacao> getPeticoesEmbarcacao() {
		return peticoesEmbarcacao;
	}

	public void setPeticoesEmbarcacao(List<PeticaoEmbarcacao> peticoesEmbarcacao) {
		this.peticoesEmbarcacao = peticoesEmbarcacao;
	}
}
