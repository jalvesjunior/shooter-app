package br.com.shooter.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.shooter.constant.TypeResultEnum;

@Entity
@Table(name = "duel_result")
@XmlRootElement(name = "duelResult")
public class DuelResult extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_duel", nullable = false)
	private Duel duel;

	@Column(name = "id_player", nullable = false)
	private Player player;

	@Enumerated(EnumType.STRING)
	private TypeResultEnum type;

	public TypeResultEnum getType() {
		return type;
	}

	public void setType(TypeResultEnum type) {
		this.type = type;
	}

	public Duel getDuel() {
		return duel;
	}

	public void setDuel(Duel duel) {
		this.duel = duel;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duel == null) ? 0 : duel.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		DuelResult other = (DuelResult) obj;
		if (duel == null) {
			if (other.duel != null)
				return false;
		} else if (!duel.equals(other.duel))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}