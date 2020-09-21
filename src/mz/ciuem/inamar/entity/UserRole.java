package mz.ciuem.inamar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class UserRole extends IdEntity implements Serializable,
		GrantedAuthority {

	private static final long serialVersionUID = 6874667425302308430L;

	@NotNull(message = "{error.roles.role.null}")
	@NotEmpty(message = "{error.roles.role.empty}")
	@Size(max = 50, message = "{error.roles.role.max}")
	@Column(name = "rolename", length = 50, unique=false,updatable=false)
	private String rolename;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false))
	private Set<User> userRoles = new HashSet<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "permission_id", nullable = false, updatable = false))
	private Set<Permission> permissions = new HashSet<Permission>();

	private String type;

	public UserRole() {
		this.type = "Normal";
	}
	

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<User> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return this.rolename;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(rolename);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserRole)) {
			return false;
		}
		UserRole that = (UserRole) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(rolename, that.rolename);
		return eb.isEquals();
	}

	@Override
	public String getAuthority() {
		return getRolename();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
