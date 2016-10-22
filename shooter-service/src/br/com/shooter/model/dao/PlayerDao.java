package br.com.shooter.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.shooter.model.domain.Jogador;

public class PlayerDao {
	
	@PersistenceContext(unitName="puShooterApp")
	private EntityManager entityManager;
	
	public void salvar(Jogador jogador) {
		entityManager.persist(jogador);
	}
	
	public void atualizar(Jogador jogador) {
		Jogador merge = entityManager.merge(jogador);
		entityManager.persist(merge);
	}

	public void excluir(Integer id) {
		Jogador merge = entityManager.merge(new Jogador(id));
		entityManager.remove(merge);
	}

	public List<Jogador> buscarTodos() {
		return entityManager.createQuery("from Jogador", Jogador.class).getResultList();
	}

	public Jogador buscarPorId(Integer id) {
		TypedQuery<Jogador> query = entityManager.createQuery("from Jogador j where j.id = :id", Jogador.class);
		query.setParameter("id",id);
		return query.getSingleResult();
	}

}
