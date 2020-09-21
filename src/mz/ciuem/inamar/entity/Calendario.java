package mz.ciuem.inamar.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "calendario")
@Access(AccessType.FIELD)
public class Calendario extends IdEntity{

	
	private static final long serialVersionUID = 1L;

	@Column(name = "provincia")
	private String provincia;
	
	@Column(name = "cod_candidato")
	private String codCandidato;
	
	@Column(name = "nome_candidato")
	private String nomeCandidato;
	
	@Column(name = "disciplina")
	private String disciplina;
	
	@Column(name = "local")
	private String local;
	
	@Column(name = "sala")
	private String sala;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "hora")
	private String hora;
	
	@Column(name = "dia_semana")
	private String diaSemana;
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodCandidato() {
		return codCandidato;
	}

	public void setCodCandidato(String codCandidato) {
		this.codCandidato = codCandidato;
	}

	public String getNomeCandidato() {
		return nomeCandidato;
	}

	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

}
