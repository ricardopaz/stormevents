/*
 * Copyright Motorola 2014. 
 * 
 * Description: Basic model implemeting every db operations which are common to all models
 * 
 * Author: jgabriel@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Descrition  									 					 Date 
 * =================         ====================================================        	  ===========
 * jgabriel		 		     creation														  Dec 17, 2014
 * eliasq 					 Removing printStackTrace lines from the code.                    Dec 22, 2014
 */

package com.stormevents.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.stormevents.exceptions.ModelException;
import com.stormevents.util.StormEventsLogger;

/**
 * Base class with common methods to every model
 * @author ricardo
 *
 * @param <V>
 */
public abstract class BasicModel<V> {

	private StormEventsPersistenceManager persistenceManager;
	private Class<V> clazz;
	private StormEventsLogger logger;

	public BasicModel() {
		logger = StormEventsLogger.getLogger(BasicModel.class);
		this.clazz = (Class<V>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		persistenceManager = StormEventsPersistenceManager.getInstance();
	}

	/**
	 * Generic method to save or update an entity on DB
	 * 
	 * @param entity
	 */
	public void save(V entity) throws ModelException {
		EntityManager em = persistenceManager.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new ModelException(e);
		} finally {
//			em.close();
		}
	}

	/**
	 * Generic method to delete an entity from DB
	 * 
	 * @param entity
	 */
	public void delete(V entity) throws ModelException {
		EntityManager em = persistenceManager.getEntityManager();
		try {
			em.getTransaction().begin();
			//entity = em.merge(entity);
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getStackTrace());
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new ModelException(e);
		} 
	}

	/**
	 * Generic method to get all records of a given entity from DB
	 * 
	 * @return all records of a given entity from DB
	 */
	public List<V> getAll() throws ModelException {
		EntityManager em = persistenceManager.getEntityManager();
		List<V> list = new ArrayList<V>();
		try {
			CriteriaQuery<V> query = em.getCriteriaBuilder().createQuery(clazz);
			query.select(query.from(clazz));
			list = em.createQuery(query).getResultList();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new ModelException(e);
		} 
		return list;
	}

	/**
	 * Generic method to get a record from DB by its id
	 * 
	 * @param id
	 * @return the entity that has the given id
	 */
	public V getByID(long id) throws ModelException {
		EntityManager em = persistenceManager.getEntityManager();
		V result = null;
		try {
			result = em.find(clazz, id);
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new ModelException(e);
		} 
		return result;
	}

	/**
	 * Generic method that deals with all common operations to make a query on
	 * DB
	 * 
	 * @param queryString
	 * @param parameters
	 * @param clazz
	 * @return the result of the given query
	 */
	protected List<V> query(String queryString, Map<String, Object> parameters) throws ModelException {
		EntityManager em = persistenceManager.getEntityManager();
		List<V> result = null;
		try {
			TypedQuery<V> query = em.createQuery(queryString, clazz);
			if (parameters != null) {
				for (String key : parameters.keySet()) {
					query.setParameter(key, parameters.get(key));
				}
			}
			result = query.getResultList();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new ModelException(e);
		} 
		return result;
	}
}
