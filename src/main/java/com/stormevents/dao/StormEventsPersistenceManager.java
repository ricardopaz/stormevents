/*
 * Copyright Motorola 2014. 
 * 
 * Description: Model class that handles information about users of motoplay application.
 * 
 * Author: eliasq@motorola.com
 * 
 * Revision History
 * Author (CoreID)           Descrition  									  Date 
 * =================         ============================================     ===========
 * eliasq       			 Initial Creation								  Dez 15, 2014
 */
package com.stormevents.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.stormevents.util.StormEventsLogger;


/**
 * Database connection manager class. It is responsible to open connection with
 * MySQL database.
 * 
 * @author ricardo
 * 
 */
public class StormEventsPersistenceManager {

	StormEventsLogger logger = StormEventsLogger
			.getLogger(StormEventsPersistenceManager.class);
	private static StormEventsPersistenceManager instance;
	private EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> localEntityManager = new ThreadLocal<EntityManager>();

	static {
		instance = new StormEventsPersistenceManager();
	}

	private StormEventsPersistenceManager() {
		String persistenceUnitName = "stormeventsdb";

		logger.debug("Creating the Entity Manager Factory");
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		logger.debug("Entity Manager Factory created");
	}

	/**
	 * Returns a instance of MotoplayPersistenceManager.
	 * 
	 * @return
	 */
	public static StormEventsPersistenceManager getInstance() {
		return instance;
	}

	/**
	 * Creates an instance of entity manager and added it thread context. It
	 * means only endpoint thread is able to handle an unique entity manager
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		EntityManager em = localEntityManager.get();
		if (em == null) {
			em = emf.createEntityManager();
			localEntityManager.set(em);
		}
		return em;
	}

	/**
	 * Close the entity manager used for all transactions during a unique
	 * endpoint request.
	 */
	public void closeEntityManager() {
		EntityManager em = localEntityManager.get();
		if (em != null) {
			em.close();
			localEntityManager.set(null);
		}
	}
}
