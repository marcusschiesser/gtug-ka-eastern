package org.gtug.karlsruhe.bunnycacher.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.service.EggService;
import org.gtug.karlsruhe.bunnycacher.server.domain.Egg;

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
	public void createEgg(EggDto eggDto) {
		Egg egg = new Egg(eggDto);
		egg.setCreated(new Date());
		entityManager.persist(egg);
		logger.fine("Saved entity!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EggDto> getEggsWithin(double latitude, double longitude) {
		Query query = entityManager.createQuery("SELECT egg FROM Egg egg");
		List<EggDto> eggDtos = new ArrayList<EggDto>();
		for (Egg egg : (List<Egg>) query.getResultList()) {
			eggDtos.add(new EggDto(egg.getLatitude(), egg.getLongitude(), egg.getHint()));
		}
		return eggDtos;
	}

}