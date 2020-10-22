package mz.ciuem.inamar.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="param_funcionario")
public class Funcionario extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sector_id", insertable = true, updatable = true)
	private DelegacaoDepartamentoSector sector;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcioanrio_roles", joinColumns = @JoinColumn(name = "funcionario_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
	private Set<UserRole> roles = new HashSet<UserRole>();

	@Column(name = "agente_apelido")
	private String apelido;

	@Column(name = "agente_nome")
	private String nome;

	@Column(name = "agente_sexo")
	private String sexo;

	@Column(name = "agente_email")
	private String email;

	@Column(name = "agente_telefone")
	private long telefone;

	@Column(name = "agente_nuit")
	private long nuit;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User userLogin;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "userLoggado_id", insertable = true, updatable = true)
	 * private User userLoggado;
	 */

	public DelegacaoDepartamentoSector getSector() {
		return sector;
	}

	public void setSector(DelegacaoDepartamentoSector sector) {
		this.sector = sector;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public long getNuit() {
		return nuit;
	}

	public void setNuit(long nuit) {
		this.nuit = nuit;
	}

	public User getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	
	

}
