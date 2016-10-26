package br.com.shooter.model.domain;

import java.io.Serializable;
import javax.persistence.*;

import br.com.shooter.constant.TypeResultEnum;

@Entity
@Table(name = "duel_result")
public class DuelResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_duel", nullable = false)
	private Duel duel;

	@Column(name = "id_player", nullable = false)
	private Player player;

	@Enumerated(EnumType.STRING)
	private TypeResultEnum type;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duel == null) ? 0 : duel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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