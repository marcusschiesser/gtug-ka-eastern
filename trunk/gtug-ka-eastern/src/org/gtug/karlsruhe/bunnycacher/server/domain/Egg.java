package org.gtug.karlsruhe.bunnycacher.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.gtug.karlsruhe.bunnycacher.common.domain.EggDto;

import com.google.appengine.api.datastore.Key;

@Entity
public class Egg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

    private double latitude;
    private double longitude;

    @Lob
    private String hint;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(nullable = false)
    private String creatorId;

    @OneToOne
    @Column(nullable = false)
    private Eid eid;


    /**
     * Empty constructor for JPA
     */
    public Egg() {
    }

    public Egg(EggDto eggDto) {
        this.latitude = eggDto.getLatitude();
        this.longitude = eggDto.getLongitude();
        this.hint = eggDto.getHint();
    }

    public Egg(double latitude, double longitude, String hint) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.hint = hint;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public Eid getEid() {
        return eid;
    }

    public void setEid(Eid eid) {
        this.eid = eid;
    }

}
