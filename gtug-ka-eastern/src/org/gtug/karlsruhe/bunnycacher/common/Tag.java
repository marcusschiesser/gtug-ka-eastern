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
public class Tag implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Egg egg;

    public Tag() {
    }

	public Long getId() {
		return id;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Egg getEgg() {
		return egg;
	}

	public void setEgg(Egg egg) {
		this.egg = egg;
	}

}
