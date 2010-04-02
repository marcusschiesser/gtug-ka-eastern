package org.gtug.karlsruhe.bunnycacher.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;
import org.gtug.karlsruhe.bunnycacher.server.util.GeohashUtils;

import com.google.appengine.api.datastore.Key;

@Entity
public class Egg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

    private String geohash;

    @Lob
    private String hint;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created;

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    private long eid;


    /**
     * Empty constructor for JPA
     */
    public Egg() {
    }

    public Egg(double latitude, double longitude, String hint) {
        this();
        this.geohash = GeohashUtils.encode(latitude, longitude);
        this.hint = hint;
    }

    public Egg(EggDto eggDto) {
        this(eggDto.getLatitude(), eggDto.getLongitude(), eggDto.getHint());
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Geohash getGeohash() {
        double[][] geohash = GeohashUtils.decode(this.geohash);
        return new Geohash(geohash[0][2], geohash[1][2]);
    }

    public void setGeohash(Geohash geohash) {
        this.geohash = GeohashUtils.encode(geohash.getLatitude(), geohash.getLongitude());
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

}
