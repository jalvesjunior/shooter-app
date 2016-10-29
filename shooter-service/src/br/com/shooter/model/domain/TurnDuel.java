package br.com.shooter.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "turn_duel")
public class TurnDuel extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_duel", nullable = false)
	private Duel duel;

	@Column(name = "id_player_turn", nullable = false)
	private Player playerTurn;

	@ManyToOne
	@JoinColumn(name = "id_body_part_attack", nullable = false)
	private BodyPart bodyPartAttack;

	@ManyToOne
	@JoinColumn(name = "id_body_part_defense", nullable = false)
	private BodyPart bodyPartDefense;

	private Integer turn;

	public Duel getDuel() {
		return duel;
	}

	public void setDuel(Duel duel) {
		this.duel = duel;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}

	public BodyPart getBodyPartAttack() {
		return bodyPartAttack;
	}

	public void setBodyPartAttack(BodyPart bodyPartAttack) {
		this.bodyPartAttack = bodyPartAttack;
	}

	public BodyPart getBodyPartDefense() {
		return bodyPartDefense;
	}

	public void setBodyPartDefense(BodyPart bodyPartDefense) {
		this.bodyPartDefense = bodyPartDefense;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyPartAttack == null) ? 0 : bodyPartAttack.hashCode());
		result = prime * result + ((bodyPartDefense == null) ? 0 : bodyPartDefense.hashCode());
		result = prime * result + ((duel == null) ? 0 : duel.hashCode());
		result = prime * result + ((playerTurn == null) ? 0 : playerTurn.hashCode());
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
		TurnDuel other = (TurnDuel) obj;
		if (bodyPartAttack == null) {
			if (other.bodyPartAttack != null)
				return false;
		} else if (!bodyPartAttack.equals(other.bodyPartAttack))
			return false;
		if (bodyPartDefense == null) {
			if (other.bodyPartDefense != null)
				return false;
		} else if (!bodyPartDefense.equals(other.bodyPartDefense))
			return false;
		if (duel == null) {
			if (other.duel != null)
				return false;
		} else if (!duel.equals(other.duel))
			return false;
		if (playerTurn == null) {
			if (other.playerTurn != null)
				return false;
		} else if (!playerTurn.equals(other.playerTurn))
			return false;
		if (turn == null) {
			if (other.turn != null)
				return false;
		} else if (!turn.equals(other.turn))
			return false;
		return true;
	}

}