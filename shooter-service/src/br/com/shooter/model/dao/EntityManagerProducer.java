package br.com.shooter.model.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

	@Produces
	@PersistenceContext( unitName = "puShooterApp")
	private EntityManager entityManager;
}