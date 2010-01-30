package org.gtug.karlsruhe.bunnycacher.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Egg implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private double latitude;
	private double longitude;
	
	@Lob
	private String hint;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@ManyToOne
	private User creator;

    
    public Egg() {
    }
    
	public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
		this.id = id;
	}

    public Egg(double latitude, double longitude, String hint) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.hint = hint;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}
