package br.com.shooter.model.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "duel")
public class Duel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_duel")
	private Date startDuel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_duel")
	private Date endDuel;

	@ManyToOne
	@JoinColumn(name = "id_player_one", nullable = false)
	private Player playerOne;

	@ManyToOne
	@JoinColumn(name = "id_player_two", nullable = false)
	private Player playerTwo;

	@OneToMany(mappedBy = "duel")
	private List<DuelResult> duelResultList;

	@OneToMany(mappedBy = "duel")
	private List<TurnDuel> turnDuelList;

	@OneToMany(mappedBy = "duel")
	private List<TurnResult> turnResultList;

	public Duel() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEndDuel() {
		return this.endDuel;
	}

	public void setEndDuel(Date endDuel) {
		this.endDuel = endDuel;
	}

	public Date getStartDuel() {
		return this.startDuel;
	}

	public void setStartDuel(Date startDuel) {
		this.startDuel = startDuel;
	}

	public Player getPlayerOne() {
		return this.playerOne;
	}

	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo() {
		return this.playerTwo;
	}

	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}

	public List<DuelResult> getDuelResultList() {
		return duelResultList;
	}

	public void setDuelResultList(List<DuelResult> duelResultList) {
		this.duelResultList = duelResultList;
	}

	public List<TurnDuel> getTurnDuelList() {
		return turnDuelList;
	}

	public void setTurnDuelList(List<TurnDuel> turnDuelList) {
		this.turnDuelList = turnDuelList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDuel == null) ? 0 : endDuel.hashCode());
		result = prime * result + ((playerOne == null) ? 0 : playerOne.hashCode());
		result = prime * result + ((playerTwo == null) ? 0 : playerTwo.hashCode());
		result = prime * result + ((startDuel == null) ? 0 : startDuel.hashCode());
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
		Duel other = (Duel) obj;
		if (endDuel == null) {
			if (other.endDuel != null)
				return false;
		} else if (!endDuel.equals(other.endDuel))
			return false;
		if (playerOne == null) {
			if (other.playerOne != null)
				return false;
		} else if (!playerOne.equals(other.playerOne))
			return false;
		if (playerTwo == null) {
			if (other.playerTwo != null)
				return false;
		} else if (!playerTwo.equals(other.playerTwo))
			return false;
		if (startDuel == null) {
			if (other.startDuel != null)
				return false;
		} else if (!startDuel.equals(other.startDuel))
			return false;
		return true;
	}

}