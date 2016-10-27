package br.com.shooter.model.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.shooter.model.dao.BaseEntity;

@Entity
@Table(name = "body_part_hit")
public class BodyPartHit extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;

	@Column(name = "perc_damage_hit")
	private BigDecimal percentDamageHit;

	@OneToOne(optional = false, mappedBy = "bodyPartHit")
	private BodyPart bodyPart;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentDamageHit() {
		return this.percentDamageHit;
	}

	public void setPercentDamageHit(BigDecimal percentDamageHit) {
		this.percentDamageHit = percentDamageHit;
	}

	public BodyPart getBodyPart() {
		return this.bodyPart;
	}

	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyPart == null) ? 0 : bodyPart.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((percentDamageHit == null) ? 0 : percentDamageHit.hashCode());
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
		BodyPartHit other = (BodyPartHit) obj;
		if (bodyPart == null) {
			if (other.bodyPart != null)
				return false;
		} else if (!bodyPart.equals(other.bodyPart))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (percentDamageHit == null) {
			if (other.percentDamageHit != null)
				return false;
		} else if (!percentDamageHit.equals(other.percentDamageHit))
			return false;
		return true;
	}

}