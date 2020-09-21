package mz.ciuem.inamar.entity;

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
@Table(name = "permissions")
public class Permission extends IdEntity implements GrantedAuthority {

	private static final long serialVersionUID = -5404269148967698143L;

	@NotNull(message = "{error.permission.permissionname.null}")
	@NotEmpty(message = "{error.permission.permissionname.empty}")
	@Size(max = 50, message = "{permission.permissionname.role.max}")
	@Column(name = "permissionname", length = 50, unique = true)
	private String permissionname;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "permission_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
	private Set<UserRole> permRoles;

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname.toUpperCase();
	}

	@Override
	public String getAuthority() {
		return permissionname;
	}

	public Set<UserRole> getPermRoles() {
		return permRoles;
	}

	public void setPermRoles(Set<UserRole> permRoles) {
		this.permRoles = permRoles;
	}

	@Override
	public String toString() {
		return this.permissionname;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(permissionname);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Permission)) {
			return false;
		}
		Permission that = (Permission) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(permissionname, that.permissionname);
		return eb.isEquals();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}