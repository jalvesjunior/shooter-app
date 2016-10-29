package br.com.shooter.model.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "body_part_hit")
public class BodyPartHit extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Column(name = "perc_damage_hit")
	private BigDecimal percentDamageHit;

	@OneToOne(optional = false, mappedBy = "bodyPartHit")
	private BodyPart bodyPart;

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
		result = prime * result + ((percentDamageHit == null) ? 0 : percentDamageHit.hashCode());
		return result;
	}
}