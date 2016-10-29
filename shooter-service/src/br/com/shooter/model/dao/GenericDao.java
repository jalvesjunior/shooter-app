package br.com.shooter.model.dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;

import br.com.shooter.constant.Order;
import br.com.shooter.model.domain.BaseEntity;

public abstract class GenericDao<T extends BaseEntity<PK>, PK extends Serializable> {
	
	
	public enum MatchMode { START, END, EXACT, ANYWHERE }

	@Inject
	private EntityManager entityManager;
	
	private Class<T> clazz;
	
	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T save(T entity) {
		if(entity == null) {
			throw new IllegalArgumentException("Entidade nao pode ser nula");
		}
		entityManager.persist(entity);
		return entity;
	}


	public T update(T entity) {
		if(entity == null) {
			throw new IllegalArgumentException("Entidade nao pode ser nula");
		}
		entity = entityManager.merge(entity);
		entityManager.persist(entity);
		return entity;
	}

	public void delete(T t) {
		if(t == null) {
			throw new IllegalArgumentException("Entidade nao pode ser nula");
		}

		T entity = find(t);
		
		if (entity != null) {
			entityManager.remove(entity);
		} else {
			throw new NotFoundException();
		}
	}

	public T find(T t) {
		if(t == null) {
			throw new IllegalArgumentException("Entidade pode ser nula");
		}
		return entityManager.find(clazz, t.getId());
	}

	public List<T> findByProperty(String propertyName, Object value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.where(cb.equal(root.get(propertyName), value));
		return entityManager.createQuery(cq).getResultList();
	}
	
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
	
	public List<T> findAll(Class<T> clazz) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		cq.from(clazz);
		return entityManager.createQuery(cq).getResultList();
	}

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