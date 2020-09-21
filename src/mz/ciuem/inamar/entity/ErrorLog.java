package mz.ciuem.inamar.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "errors")
@Access(AccessType.FIELD)
public class ErrorLog extends IdEntity {

	private static final long serialVersionUID = -6844551539389622053L;

	private String type;

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	private boolean status;

	public ErrorLog() {

		this.status = false;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(type);
		hcb.append(description);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ErrorLog)) {
			return false;
		}
		ErrorLog that = (ErrorLog) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(type, that.type);
		eb.append(description, that.description);
		return eb.isEquals();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
