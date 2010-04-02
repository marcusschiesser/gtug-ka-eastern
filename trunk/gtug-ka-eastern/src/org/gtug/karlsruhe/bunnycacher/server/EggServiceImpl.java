package org.gtug.karlsruhe.bunnycacher.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.common.domain.TagDto;
import org.gtug.karlsruhe.bunnycacher.common.service.EggService;
import org.gtug.karlsruhe.bunnycacher.server.domain.Egg;
import org.gtug.karlsruhe.bunnycacher.server.domain.Eid;
import org.gtug.karlsruhe.bunnycacher.server.domain.Geohash;
import org.gtug.karlsruhe.bunnycacher.server.domain.Tag;
import org.gtug.karlsruhe.bunnycacher.server.util.GaeUtils;
import org.gtug.karlsruhe.bunnycacher.server.util.GeohashUtils;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;

public class EggServiceImpl implements EggService {

    private static final Logger logger = Logger.getLogger(EggServiceImpl.class
            .getName());

    private Provider<EntityManager> entityManager;

    @Inject
    public EggServiceImpl(Provider<EntityManager> entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void createEgg(EggDto eggDto) {
        // Check if egg id exists
        findEid(eggDto.getEid());

        Egg egg = new Egg(eggDto);
        egg.setEid(eggDto.getEid());
        egg.setCreated(new Date());
        egg.setCreatorId(getUserId());
        entityManager.get().persist(egg);
        logger.fine("Saved entity!");
    }

    private Eid findEid(long eid) {
        Query query = entityManager.get().createQuery("SELECT eid FROM Eid eid WHERE id = :eid");
        query.setParameter("eid", eid);
        return (Eid) query.getSingleResult();
    }

    private String getUserId() {
        if (GaeUtils.isDevelopmentMode()) {
            return "42";
        }

        User user = UserServiceFactory.getUserService().getCurrentUser();
        if (user == null) {
            throw new RuntimeException("User not authenticated!");
        }
        return user.getNickname();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EggDto> getEggsWithin(double minLat, double maxLat, double minLng, double maxLng) {
        Query query = entityManager.get().createQuery("SELECT egg FROM Egg egg WHERE geohash >= :geohash_min AND geohash <= :geohash_max");
        query.setParameter("geohash_min", GeohashUtils.encode(minLat, minLng));
        query.setParameter("geohash_max", GeohashUtils.encode(maxLat, maxLng));

        List<EggDto> eggDtos = new ArrayList<EggDto>();
        for (Egg egg : (List<Egg>) query.getResultList()) {
            Geohash geohash = egg.getGeohash();
            eggDtos.add(new EggDto(egg.getEid(), geohash.getLatitude(), geohash.getLongitude(), egg.getHint()));
        }
        return eggDtos;
    }

    @Override
    @Transactional
    public Long reserveEid() {
        Eid eid = new Eid();
        // XXX This only works for max. 1000 entries
        Query query = entityManager.get().createQuery("SELECT eid FROM Eid eid");
        eid.setId(query.getResultList().size() + 1);
        entityManager.get().persist(eid);
        return eid.getId();
    }

    @Override
    @Transactional
    public void createTag(long eid, String message) {
        // Check if corresponding egg exists
        findEggForEid(eid);

        Tag tag = new Tag();
        tag.setEid(eid);
        tag.setTimestamp(new Date());
        tag.setUserId(getUserId());
        tag.setMessage(message);
        entityManager.get().persist(tag);
    }

    private Egg findEggForEid(long eid) {
        Query query = entityManager.get().createQuery("SELECT egg FROM Egg egg WHERE eid = :eid");
        query.setParameter("eid", eid);
        if (query.getResultList().isEmpty()) {
            throw new RuntimeException(String.format("No corresponding egg found for eid %d!", eid));
        }
        return (Egg) query.getResultList().get(0);
    }

    @Override
    @Transactional
    public void foundEgg(EggDto egg, String message) {
        createTag(egg.getEid(), message);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TagDto> getTags(EggDto eggDto) {
        Query query = entityManager.get().createQuery("SELECT tag FROM Tag tag WHERE eid = :eid");
        query.setParameter("eid", eggDto.getEid());
        
        List<TagDto> tagDtos = new ArrayList<TagDto>();
        for (Tag tag : (List<Tag>) query.getResultList()) {
            tagDtos.add(new TagDto(tag.getMessage(), tag.getTimestamp(), tag.getUserId(), tag.getEid()));
        }
        return tagDtos;
    }

}
