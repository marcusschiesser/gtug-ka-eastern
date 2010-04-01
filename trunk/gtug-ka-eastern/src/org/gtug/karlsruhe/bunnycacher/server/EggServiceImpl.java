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
import org.gtug.karlsruhe.bunnycacher.server.domain.Tag;
import org.gtug.karlsruhe.bunnycacher.server.util.GaeUtils;

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
        Egg egg = new Egg(eggDto);
        egg.setEid(findEid(eggDto.getEid()));
        egg.setCreated(new Date());
        egg.setCreatorId(getUserId());
        entityManager.get().persist(egg);
        logger.fine("Saved entity!");
    }

    private Eid findEid(long eid) {
        Eid eidObj = entityManager.get().find(Eid.class, eid);
        if (eidObj == null) {
            throw new RuntimeException(String.format("No eid %d found!", eid));
        }
        return eidObj;
    }

    private String getUserId() {
        if (GaeUtils.isDevelopmentMode()) {
            return "42";
        }

        User user = UserServiceFactory.getUserService().getCurrentUser();
        if (user == null) {
            throw new RuntimeException("User not authenticated!");
        }
        return user.getUserId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EggDto> getEggsWithin(double minLat, double maxLat, double minLng, double maxLng) {
        /*Query query = entityManager.get().createQuery("SELECT egg FROM Egg egg WHERE egg.latitude > :latitudelow AND egg.latitude < :latitudehigh AND egg.longitude > :longitudelow AND egg.longitude < :longitudehigh");
        query.setParameter("latitudelow", latitude - 0.001);
        query.setParameter("latitudehigh", latitude + 0.001);
        query.setParameter("longitudelow", longitude - 0.001);
        query.setParameter("longitudehigh", longitude + 0.001);*/
        Query query = entityManager.get().createQuery("SELECT egg FROM Egg egg");

        List<EggDto> eggDtos = new ArrayList<EggDto>();
        for (Egg egg : (List<Egg>) query.getResultList()) {
            eggDtos.add(new EggDto(egg.getKey().getId(), egg.getLatitude(), egg.getLongitude(), egg.getHint()));
        }
        return eggDtos;
    }

    @Override
    @Transactional
    public long reserveEid() {
        Eid eid = new Eid();
        Query query = entityManager.get().createQuery("SELECT COUNT(key) FROM Eid eid");
        eid.setId((Long) query.getSingleResult() + 1);
        entityManager.get().persist(eid);
        return eid.getId();
    }

    @Override
    @Transactional
    public void createTag(long eid, String message) {
        Tag tag = new Tag();
        tag.setEgg(findEggForEid(eid));
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
    public void foundEgg(EggDto egg, String message) {
        createTag(egg.getEid(), message);
    }

    @Override
    public List<TagDto> getTags(EggDto egg) {
        // TODO Auto-generated method stub
        return null;
    }

}
