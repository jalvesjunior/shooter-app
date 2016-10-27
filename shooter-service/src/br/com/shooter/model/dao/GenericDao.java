package br.com.shooter.model.dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;

import br.com.shooter.constant.Order;

/**
 * A generic dao implementation based solely on JPA.
 * 
 * @author Diego baseado na implementacao de Rodrigo Uchôa (http://rodrigouchoa.wordpress.com)
 *
 */
@Stateless
public class GenericDao<T extends BaseEntity<PK>, PK extends Serializable> {
	
	/*
	 * A "copy" of the Hibernate's API as this doesn't exist
	 * in JPA.
	 */
	public enum MatchMode { START, END, EXACT, ANYWHERE }

	
	@Inject
	private EntityManager entityManager;

	public T save(T entity) {
		if(entity == null) {
			throw new IllegalArgumentException("Entidade nao pode er nula");
		}
		entityManager.persist(entity);
		return entity;
	}


	public T update(T entity) {
		if(entity == null) {
			throw new IllegalArgumentException("Entidade nao pode er nula");
		}
		entityManager.merge(entity);
		return save(entity);
	}

	/**
	 * Deletes tne entity.
	 * 
	 * @param clazz
	 * @param id
	 * @throws NotFoundException if the id does not exist.
	 */
	public void delete(Class<T> clazz, T t) {
		if(t == null) {
			throw new IllegalArgumentException("Entidade nao pode er nula");
		}
		// seguranca extra para evitar outra excecao
		T entity = find(clazz, t);
		if (entity != null) {
			entityManager.remove(entity);
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * Find an entity by its identifier.
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public T find(Class<T> clazz, T t) {
		if(t == null) {
			throw new IllegalArgumentException("Entidade pode er nula");
		}
		return entityManager.find(clazz, t.getId());
	}

	/**
	 * Finds an entity by one of its properties.
	 * 
	 * 
	 * @param clazz the entity class.
	 * @param propertyName the property name.
	 * @param value the value by which to find.
	 * @return
	 */
	public List<T> findByProperty(Class<T> clazz, String propertyName, Object value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.where(cb.equal(root.get(propertyName), value));
		return entityManager.createQuery(cq).getResultList();
	}
	
	/**
	 * Finds entities by a String property specifying a MatchMode. This search 
	 * is case insensitive.
	 * 
	 * @param clazz the entity class.
	 * @param propertyName the property name.
	 * @param value the value to check against.
	 * @param matchMode the match mode: EXACT, START, END, ANYWHERE.
	 * @return
	 */
	public List<T> findByProperty(Class<T> clazz, String propertyName, String value, MatchMode matchMode) {

		value = value.toLowerCase();
		if (MatchMode.START.equals(matchMode)) {
			value = value + "%";
		} else if (MatchMode.END.equals(matchMode)) {
			value = "%" + value;
		} else if (MatchMode.ANYWHERE.equals(matchMode)) {
			value = "%" + value + "%";
		}
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.where(cb.like(cb.lower(root.<String>get(propertyName)), value));
		
		return entityManager.createQuery(cq).getResultList();
	}
	
	

	/**
	 * Finds all objects of an entity class.
	 * 
	 * @param clazz the entity class.
	 * @return
	 */
	public List<T> findAll(Class<T> clazz) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		cq.from(clazz);
		return entityManager.createQuery(cq).getResultList();
	}

	/**
	 * Finds all objects of a class by the specified order.
	 * 
	 * @param clazz the entity class.
	 * @param order the order: ASC or DESC.
	 * @param propertiesOrder the properties on which to apply the ordering.
	 * 
	 * @return
	 */
	public List<T> findAll(Class<T> clazz, Order order, String... propertiesOrder) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		for (String propertyOrder : propertiesOrder) {
			if (order.isAscOrder()) {
				orders.add(cb.asc(root.get(propertyOrder)));
			} else {
				orders.add(cb.desc(root.get(propertyOrder)));
			}
		}
		cq.orderBy(orders);

		return entityManager.createQuery(cq).getResultList();
	}

}