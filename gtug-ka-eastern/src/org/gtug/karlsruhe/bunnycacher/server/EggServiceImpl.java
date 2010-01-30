package org.gtug.karlsruhe.bunnycacher.server;

import javax.persistence.EntityManager;

import org.gtug.karlsruhe.bunnycacher.client.EggService;
import org.gtug.karlsruhe.bunnycacher.common.Egg;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
public class EggServiceImpl implements EggService {

	@Inject
	Provider<EntityManager> em;

	public void createEgg(Egg egg) {

	}

}
