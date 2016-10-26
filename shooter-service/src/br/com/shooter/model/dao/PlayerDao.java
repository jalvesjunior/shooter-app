package br.com.shooter.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.shooter.model.domain.Player;

public class PlayerDao {
	
	@PersistenceContext(unitName="puShooterApp")
	private EntityManager entityManager;
	
	public void salvar(Player jogador) {
		entityManager.persist(jogador);
	}
	
	public void atualizar(Player jogador) {
		Player merge = entityManager.merge(jogador);
		entityManager.persist(merge);
	}

	public void excluir(Integer id) {
		Player merge = entityManager.merge(new Player(id));
		entityManager.remove(merge);
	}

	public List<Player> buscarTodos() {
		return entityManager.createQuery("from Player", Player.class).getResultList();
	}

	public Player buscarPorId(Integer id) {
		TypedQuery<Player> query = entityManager.createQuery("from Player j where j.id = :id", Player.class);
		query.setParameter("id",id);
		return query.getSingleResult();
	}

}
