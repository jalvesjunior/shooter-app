package br.com.shooter.model.dao;

import javax.ejb.Stateless;

import br.com.shooter.model.domain.Player;

@Stateless
public class PlayerDao extends GenericDao<Player, Integer>{

	public PlayerDao() {
		super(Player.class);
	}

}
