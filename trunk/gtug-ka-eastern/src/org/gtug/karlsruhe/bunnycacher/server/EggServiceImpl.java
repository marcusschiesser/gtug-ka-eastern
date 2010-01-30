package org.gtug.karlsruhe.bunnycacher.server;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.gtug.karlsruhe.bunnycacher.common.domain.Egg;
import org.gtug.karlsruhe.bunnycacher.common.service.EggService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
public class EggServiceImpl implements EggService {

	private static final Logger logger = Logger.getLogger(EggServiceImpl.class
			.getName());

	@Inject
	EntityManager entityManager;

	public void createEgg(Egg egg) {
		entityManager.persist(egg);
		logger.fine("Saved entity " + egg.getId());
	}

}
