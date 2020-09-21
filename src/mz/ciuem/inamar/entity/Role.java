package mz.ciuem.inamar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "organ_roles")
public class Role extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "designation", unique = true)
	private String designation;

	public Role(String designation) {
		this.designation = designation;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(designation);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Role that = (Role) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(designation, that.designation);
		return eb.isEquals();
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
