package br.com.shooter.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "turn_result")
public class TurnResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_duel", nullable = false)
	private Duel duel;

	@Column(name = "id_player_one_demage_receive", nullable = false)
	private Player PlayerOneReceiverDamage;

	@Column(name = "id_player_two_demage_receive", nullable = false)
	private Player PlayerTwoReceiverDamage;

	private Integer turn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Duel getDuel() {
		return duel;
	}

	public void setDuel(Duel duel) {
		this.duel = duel;
	}

	public Player getPlayerOneReceiverDamage() {
		return PlayerOneReceiverDamage;
	}

	public void setPlayerOneReceiverDamage(Player playerOneReceiverDamage) {
		PlayerOneReceiverDamage = playerOneReceiverDamage;
	}

	public Player getPlayerTwoReceiverDamage() {
		return PlayerTwoReceiverDamage;
	}

	public void setPlayerTwoReceiverDamage(Player playerTwoReceiverDamage) {
		PlayerTwoReceiverDamage = playerTwoReceiverDamage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PlayerOneReceiverDamage == null) ? 0 : PlayerOneReceiverDamage.hashCode());
		result = prime * result + ((PlayerTwoReceiverDamage == null) ? 0 : PlayerTwoReceiverDamage.hashCode());
		result = prime * result + ((duel == null) ? 0 : duel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
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
		TurnResult other = (TurnResult) obj;
		if (PlayerOneReceiverDamage == null) {
			if (other.PlayerOneReceiverDamage != null)
				return false;
		} else if (!PlayerOneReceiverDamage.equals(other.PlayerOneReceiverDamage))
			return false;
		if (PlayerTwoReceiverDamage == null) {
			if (other.PlayerTwoReceiverDamage != null)
				return false;
		} else if (!PlayerTwoReceiverDamage.equals(other.PlayerTwoReceiverDamage))
			return false;
		if (duel == null) {
			if (other.duel != null)
				return false;
		} else if (!duel.equals(other.duel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (turn == null) {
			if (other.turn != null)
				return false;
		} else if (!turn.equals(other.turn))
			return false;
		return true;
	}

}