package br.com.shooter.model.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "body_part")
public class BodyPart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(optional = false)
	@JoinColumn(name = "id", unique = true, nullable = false, updatable = false)
	private BodyPartHit bodyPartHit;

	private String description;

	public BodyPart() {
	}

	public BodyPartHit getBodyPartHit() {
		return bodyPartHit;
	}

	public void setBodyPartHit(BodyPartHit bodyPartHit) {
		this.bodyPartHit = bodyPartHit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyPartHit == null) ? 0 : bodyPartHit.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BodyPart other = (BodyPart) obj;
		if (bodyPartHit == null) {
			if (other.bodyPartHit != null)
				return false;
		} else if (!bodyPartHit.equals(other.bodyPartHit))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}