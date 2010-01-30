package org.gtug.karlsruhe.bunnycacher.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.service.EggService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.Transactional;

/**
 * The server side implementation of the RPC service.
 */
@Singleton
public class EggServiceImpl implements EggService {

	private static final Logger logger = Logger.getLogger(EggServiceImpl.class
			.getName());

	@Inject
	EntityManager entityManager;

	@Transactional
	public void createEgg(EggDto egg) {
		egg.setCreated(new Date());
		entityManager.persist(egg);
		logger.fine("Saved entity!");
	}

	@Override
	public List<EggDto> getEggsWithin(double latitude, double longitude) {
		return new ArrayList<EggDto>();
	}

}
